package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.ResultsPage;
import seleniumdemo.utils.ExcelReader;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;


public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done");
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        test.log(Status.PASS, "Setting dates done");
        hotelSearchPage.setTravelersCount(4, 2);
        test.log(Status.PASS, "Setting travelers done");
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done");

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotels = resultsPage.getHotelList();
        hotels.forEach(el -> System.out.println(el));
        Assert.assertEquals(hotels.size(), 4);

        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for (WebElement el : elementsCities) {
            Assert.assertEquals(el.getText(), "dubai");
        }
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void searchHotelTestWithNoCity() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test with no city");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount(2, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals(resultsPage.getResultHeading(), "No Results Found");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test(dataProvider = "getData")
    public void searchHotelTestWithDataProvider(String city, String hotel) throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test with data provider");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        hotelSearchPage.setDate("05/12/2023", "12/12/2023");
        hotelSearchPage.setTravelersCount(4, 2);
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done");

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotels = resultsPage.getHotelList();
        hotels.forEach(el -> System.out.println(el));
        //Assert.assertEquals(hotels.size(), 4);

        test.log(Status.INFO, "Hotel list", SeleniumHelper.getScreenshot(driver));
        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for (WebElement el : elementsCities) {
            Assert.assertEquals(el.getText(), city.toLowerCase());
        }
        test.log(Status.PASS, "Assertions passed");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return ExcelReader.readExcel("TestData.xlsx");
    }

}
