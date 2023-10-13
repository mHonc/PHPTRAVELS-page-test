package seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;

import java.util.List;


public class HotelSearchTest extends BaseTest{

    @Test
    public void searchHotelTest(){
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount();
        hotelSearchPage.performSearch();

        List<WebElement> elements = driver.findElements(By.xpath("//h4/a/b"));
        elements.forEach(el -> System.out.println(el.getText()));
        Assert.assertEquals(elements.size(), 4);

        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for(WebElement el : elementsCities){
            Assert.assertEquals(el.getText(), "dubai");
        }
    }

}
