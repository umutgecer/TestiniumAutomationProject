package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    private static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.beymen.com/tr");
        logger.info("TEST BAŞLADI..");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
        logger.info("TEST BITTI.");
    }

}