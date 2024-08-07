package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BaseStep {
    private static final String FILE_PATH = "src/test/resources/productInfo.txt";
    private static Logger logger = Logger.getLogger(BaseStep.class);

    public WebDriver driver;
    public WebDriverWait wait;

    public BaseStep(WebDriver webDriver, WebDriverWait wait) {
        this.driver = webDriver;
        this.wait = wait;
    }

    public <T> void waitElement(T elementAttr) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
        logger.info(elementAttr + " Element Bulundu.");
        Assert.assertTrue(true, "Element Bulunamadı!!! " + elementAttr);

    }

    public <T> void clickToElement(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
        logger.info(elementAttr + " Element Tiklandi.");
        Assert.assertTrue(true, "Element Tiklanamadi!!! " + elementAttr);
    }

    public <T> void sendKeys(T elementAttr, String text) {
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
        logger.info(text + " Element Yazildi.");
    }

    public <T> String getElementText(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText().trim();
        }
    }

    public <T> void sendEnterKey(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver.findElement((By) elementAttr).sendKeys(Keys.ENTER);
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(Keys.ENTER);
        }
    }

    public void checkPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Sayfa Basligi Eslesmiyor.");
        logger.info("Sayfa Basligi Eslesti: " + actualTitle);
    }

    public <T> void clickRandomElement(T elementAttr) {
        waitElement(elementAttr);
        List<WebElement> elements;

        if (elementAttr instanceof By) {
            elements = driver.findElements((By) elementAttr);
        } else if (elementAttr instanceof WebElement) {
            elements = ((WebElement) elementAttr).findElements(By.xpath(".//*"));
        } else {
            throw new IllegalArgumentException("Geçersiz Element Türü: " + elementAttr.getClass().getName());
        }
        if (elements.isEmpty()) {
            throw new IllegalStateException("Element Listesi Boş.");
        }
        Random random = new Random();
        WebElement randomElement = elements.get(random.nextInt(elements.size()));
        randomElement.click();
        logger.info("Tiklanan Element: " + randomElement.toString());
    }

    public <T> void checkPriceBasketPage(T elementAttr) {
        waitElement(elementAttr);
        String expectedText = readTextFromFile();
        String elementText = getElementText(elementAttr);
        if (elementText != null) {
            String cleanedElementText = cleanPriceText(elementText);
            Assert.assertEquals(cleanedElementText, expectedText, "Metinler Esleşmedi. Beklenen: " + expectedText + ", Gerceklesen: " + cleanedElementText);
            logger.info("Metinler eşleşti: " + cleanedElementText);
        } else {
            logger.error("Element Metni Yok");
        }
    }

    private String cleanPriceText(String priceText) {
        return priceText.replaceAll(",00 TL", " TL").trim();
    }

    private String readTextFromFile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.error("Dosya Okunamadı: " + e.getMessage());
        }
        return text.toString().trim();
    }

    public <T> void writeTextToFile(T elementAttr) {
        waitElement(elementAttr);
        String elementText;
        if (elementAttr instanceof By) {
            WebElement element = driver.findElement((By) elementAttr);
            elementText = element.getText();
        } else if (elementAttr instanceof WebElement) {
            elementText = ((WebElement) elementAttr).getText();
        } else {
            throw new IllegalArgumentException("Geçersiz element türü: " + elementAttr.getClass().getName());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(elementText);
            logger.info("Metin dosyaya başarıyla yazıldı: " + elementText);
        } catch (IOException e) {
            logger.error("Metin dosyaya yazılamadı: ", e);
        }
    }

    public <T> void checkIfTheTextsAreEqual(T elementAttr, String expText) {
        waitElement(elementAttr);
        String elementText = getElementText(elementAttr);
        Assert.assertEquals(elementText, expText, "MEtinler Eslesmedi. Beklenen: " + expText + ", Gercekleşen: " + elementText);
        logger.info("Metinler Eslesti: " + elementText);
    }

    public <T> void checkElementValue(T elementAttr, String expectedValue) {
        waitElement(elementAttr);
        String ariaLabelValue;

        if (elementAttr instanceof By) {
            ariaLabelValue = driver.findElement((By) elementAttr).getAttribute("aria-label");
        } else if (elementAttr instanceof WebElement) {
            ariaLabelValue = ((WebElement) elementAttr).getAttribute("aria-label");
        } else {
            throw new IllegalArgumentException("Gecersiz Element: " + elementAttr.getClass().getName());
        }

        Assert.assertEquals(ariaLabelValue, expectedValue, "Deger Eslesmedi. Beklenen: " + expectedValue + ", Gerçekleşen: " + ariaLabelValue);
        logger.info("Deger Eslesti: " + ariaLabelValue);
    }

}