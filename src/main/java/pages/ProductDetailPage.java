package pages;

import base.BaseStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.Selectors.*;
public class ProductDetailPage extends BaseStep {

    public ProductDetailPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }


    public void writePriceToFile() {
        writeTextToFile(PRODUCT_PRICE);

    }

    public void addBasket() {
        clickToElement(SELECT_SIZE);
        clickToElement(ADD_BASKET);
        clickToElement(POPUP_BASKET_BUTTON);
    }

}