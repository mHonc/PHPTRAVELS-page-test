package seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.ResultsPage;
import seleniumdemo.utils.ExcelReader;

import java.io.IOException;
import java.util.List;


public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount(4, 2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotels = resultsPage.getHotelList();
        hotels.forEach(el -> System.out.println(el));
        Assert.assertEquals(hotels.size(), 4);

        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for (WebElement el : elementsCities) {
            Assert.assertEquals(el.getText(), "dubai");
        }
    }

    @Test
    public void searchHotelTestWithNoCity() {
        //first method
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount(2, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.getResultHeading(), "No Results Found");
    }

    @Test(dataProvider = "getData")
    public void searchHotelTestWithDataProvider(String city, String hotel) {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount(4, 2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotels = resultsPage.getHotelList();
        hotels.forEach(el -> System.out.println(el));
        //Assert.assertEquals(hotels.size(), 4);

        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for (WebElement el : elementsCities) {
            Assert.assertEquals(el.getText(), city.toLowerCase());
        }
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return ExcelReader.readExcel("TestData.xlsx");
    }

}
