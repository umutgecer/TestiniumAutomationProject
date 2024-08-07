package uitestcases;

import base.BaseTest;
import dataprovider.ExcelDataProvider;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.HomePage;
import pages.ProductDetailPage;

public class UI_BeymenWebTestCase extends BaseTest {

    HomePage homePage;
    ProductDetailPage productDetailPage;
    BasketPage basketPage;

    @Test(dataProvider = "sendTextSearchBox", dataProviderClass = ExcelDataProvider.class)
    public void CheckPriceBySearchingForProductUI(String firstText, String SecondText) {
        homePage = new HomePage(driver, wait);
        productDetailPage = new ProductDetailPage(driver, wait);
        basketPage = new BasketPage(driver, wait);
        homePage.acceptPopup();
        homePage.checkPageIsOpened();
        homePage.writeFirstTextAndDelete(firstText);
        homePage.writeSecondText(SecondText);
        homePage.sendEnterKey();
        homePage.selectRandomProductList();
        productDetailPage.writePriceToFile();
        productDetailPage.addBasket();
        basketPage.checkPriceBasketPage();
        basketPage.addedItemProduct();
        basketPage.checkProductItem();
        basketPage.removeProductBasket();
        basketPage.checkBasketIsItNull();
    }
}