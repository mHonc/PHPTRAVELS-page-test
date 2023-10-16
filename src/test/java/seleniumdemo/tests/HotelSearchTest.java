package seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.ResultsPage;

import java.util.List;


public class HotelSearchTest extends BaseTest{

    @Test
    public void searchHotelTest(){
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        List<String> hotels = hotelSearchPage.setCity("Dubai")
                .setDate("05/12/2023", "12/12/2023")
                .setTravelersCount(4, 2)
                .performSearch()
                .getHotelList();

        hotels.forEach(el -> System.out.println(el));
        Assert.assertEquals(hotels.size(), 4);

        //test
        System.out.println("test");
        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for(WebElement el : elementsCities){
            Assert.assertEquals(el.getText(), "dubai");
        }
    }

    @Test
    public void searchHotelTestWithNoCity(){
        //first method
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount(2, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.getResultHeading(), "No Results Found");
    }

}
