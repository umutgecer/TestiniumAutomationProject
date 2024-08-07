package utils;

import org.openqa.selenium.By;

public class Selectors {

    public static final String PAGE_TAB_TITLE = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";
    public static final String ACTUAL_PRODUCT_ITEM_SELECT_TEXT = "2 adet";
    public static final String CHECK_EMPTY_BASKET_MESSAGE = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";

    public static final By HOMEPAGE_ACCEPT_COOKIES = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    public static final By HOMEPAGE_SELECT_GENDER = By.id("genderManButton");
    public static final By HOMEPAGE_HESABIM = By.xpath("(//div[@class='o-header__userInfo'])//a[1]");
    public static final By HOMEPAGE_SEPET = By.xpath("(//div[@class='o-header__userInfo'])//a[3]");
    public static final By HOMEPAGE_NAVBAR = By.xpath("(.//div[@class='container -wide'])[2]");
    public static final By SEARCHBOX = By.xpath("(//input[@class='o-header__search--input'])[1]");
    public static final By SEARCHBOX_INPUT = By.xpath("(//input[@class='o-header__search--input'])[2]");
    public static final By SEARCHBOX_INPUT_DELETE_BUTTON = By.xpath("//button[@class='o-header__search--close -hasButton']");
    public static final By PRODUCT_LIST = By.xpath("//div[@id='productList']//div[@data-page='1']");
    public static final By PRODUCT_PRICE = By.xpath("//div[@class='m-price__list']//ins");
    public static final By SELECT_SIZE = By.xpath("//span[@class='m-variation__item']");
    public static final By ADD_BASKET = By.xpath("//button[@id='addBasket']");
    public static final By POPUP_BASKET_BUTTON = By.xpath("//button[@class='m-notification__button btn']");
    public static final By PRODUCT_DETAIL_PRICE = By.xpath("//li[@class='m-orderSummary__item -grandTotal']//span[@class='m-orderSummary__value']");

    public static final By NUMBER_OF_PRODUCT = By.xpath("//select[@id='quantitySelect0-key-0']");
    public static final By PRODUCT_ITEM_SELECT_AREA_VALUE = By.xpath("//select[@aria-label='2 adet']");
    public static final By PRODUCT_ITEM_SELECT = By.xpath("//select[@id='quantitySelect0-key-0']//option[@value='2']");
    public static final By BASKET_DELETE_BUTTON = By.id("removeCartItemBtn0-key-0");
    public static final By EMPTY_BASKET_MESSAGE_AREA = By.xpath("//div[@id='emtyCart']/div/strong");

}