package seleniumdemo.pages;

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

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HotelSearchPage setCity(String city) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(city);
        String hotelMatch = "//span[@class='select2-match' and text()='" + city + "']";
        driver.findElement(By.xpath(hotelMatch)).click();
        return this;
    }

    public HotelSearchPage setDate(String checkIn, String checkOut) {
        checkInInput.sendKeys(checkIn);
        checkOutInput.sendKeys(checkOut);
        return this;
    }

    public HotelSearchPage setTravelersCount(int adults, int childs) {
        travellersInput.click();
        adultInput.clear();
        childInput.clear();
        adultInput.sendKeys(String.valueOf(adults));
        childInput.sendKeys(String.valueOf(childs));
        return this;
    }

    public ResultsPage performSearch() {
        searchButton.click();
        return new ResultsPage(driver);
    }

    public CreateAccountPage openSignUpPage() {
        accountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpPageButton.get(1).click();
        return new CreateAccountPage(driver);
    }
}
