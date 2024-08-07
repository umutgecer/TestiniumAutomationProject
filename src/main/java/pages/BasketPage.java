package pages;

import base.BaseStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.Selectors.*;

public class BasketPage extends BaseStep {

    public BasketPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void checkPriceBasketPage() {
        waitElement(PRODUCT_DETAIL_PRICE);
        checkPriceBasketPage(PRODUCT_DETAIL_PRICE);

    }

    public void addedItemProduct() {
        clickToElement(NUMBER_OF_PRODUCT);
        clickToElement(PRODUCT_ITEM_SELECT);
    }

    public void checkProductItem() {
        checkElementValue(PRODUCT_ITEM_SELECT_AREA_VALUE, ACTUAL_PRODUCT_ITEM_SELECT_TEXT);
    }

    public void removeProductBasket() {
        clickToElement(BASKET_DELETE_BUTTON);
    }

    public void checkBasketIsItNull() {
        waitElement(EMPTY_BASKET_MESSAGE_AREA);
        checkIfTheTextsAreEqual(EMPTY_BASKET_MESSAGE_AREA, CHECK_EMPTY_BASKET_MESSAGE);
    }

}