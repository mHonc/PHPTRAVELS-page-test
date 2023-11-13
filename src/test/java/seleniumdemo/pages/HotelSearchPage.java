package seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class HotelSearchPage {

    @FindBy(id = "s2id_autogen8")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//input[@class='form input-lg dpd1']")
    private WebElement checkInInput;

    @FindBy(xpath = "//input[@class='form input-lg dpd2']")
    private WebElement checkOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultInput")
    private WebElement adultInput;

    @FindBy(id = "childInput")
    private WebElement childInput;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")
    private WebElement searchButton;

    @FindBy(id = "li_myaccount")
    private List<WebElement> accountLink;

    @FindBy(xpath = "//a[text() = '  Sign Up']")
    private List<WebElement> signUpPageButton;

    private WebDriver driver;

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCity(String city) {
        logger.info("Setting city " + city);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(city);
        String hotelMatch = "//span[@class='select2-match' and text()='" + city + "']";
        driver.findElement(By.xpath(hotelMatch)).click();
        logger.info("Setting city done");
    }

    public void setDate(String checkIn, String checkOut) {
        logger.info("Setting date");
        checkInInput.sendKeys(checkIn);
        checkOutInput.sendKeys(checkOut);
        logger.info("Setting date done");
    }

    public void setTravelersCount(int adults, int childs) {
        logger.info("Setting travellers count");
        travellersInput.click();
        adultInput.clear();
        childInput.clear();
        adultInput.sendKeys(String.valueOf(adults));
        childInput.sendKeys(String.valueOf(childs));
        logger.info("Setting travellers count done");
    }

    public void performSearch() {
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing search done");
    }

    public void openSignUpPage() {
        logger.info("Opening sign up page");
        accountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpPageButton.get(1).click();
        logger.info("Opening sign up page done");
    }
}
