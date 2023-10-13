package seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HotelSearchPage {

    @FindBy(id = "s2id_autogen8")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;

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

    public HotelSearchPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setCity(String city){
        searchHotelSpan.click();
        searchHotelInput.sendKeys(city);
        hotelMatch.click();
    }

    public void setDate(String checkIn, String checkOut){
        checkInInput.sendKeys(checkIn);
        checkOutInput.sendKeys(checkOut);
    }

    public void setTravelersCount(){
        travellersInput.click();
        adultInput.sendKeys("4");
        childInput.sendKeys("2");
    }

    public void performSearch(){
        searchButton.click();
    }
}
