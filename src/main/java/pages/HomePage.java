package pages;

import base.BaseStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.Selectors.*;

public class HomePage extends BaseStep {

    public HomePage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void checkPageIsOpened() {
        checkPageTitle(PAGE_TAB_TITLE);
        waitElement(HOMEPAGE_HESABIM);
        waitElement(HOMEPAGE_SEPET);
        waitElement(HOMEPAGE_NAVBAR);
    }

    public void acceptPopup() {
        clickToElement(HOMEPAGE_ACCEPT_COOKIES);
        waitElement(HOMEPAGE_SELECT_GENDER);
        clickToElement(HOMEPAGE_SELECT_GENDER);
    }

    public void writeFirstTextAndDelete(String firstText) {
        waitElement(SEARCHBOX);
        clickToElement(SEARCHBOX);
        sendKeys(SEARCHBOX_INPUT, firstText);
        clickToElement(SEARCHBOX_INPUT);
        clickToElement(SEARCHBOX_INPUT_DELETE_BUTTON);
    }

    public void writeSecondText(String SecondText) {
        sendKeys(SEARCHBOX_INPUT, SecondText);
    }

    public void sendEnterKey() {
        sendEnterKey(SEARCHBOX_INPUT);
    }

    public void selectRandomProductList() {
        clickRandomElement(PRODUCT_LIST);
    }

}