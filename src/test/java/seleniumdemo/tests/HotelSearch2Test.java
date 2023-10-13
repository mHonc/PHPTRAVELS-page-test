package seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HotelSearch2Test extends BaseTest {

    @Test
    public void searchHotelTest(){
        //first method
        driver.findElement(By.xpath("//input[@class='form input-lg dpd1']")).sendKeys("05/12/2023");
        driver.findElement(By.xpath("//input[@class='form input-lg dpd2']")).sendKeys("12/12/2023");
        //second method
        //driver.findElement(By.xpath("//input[@class='form input-lg dpd2']")).click();
        //driver.findElements(By.xpath("//td[@class='day ' and text()='16']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultInput")).clear();
        driver.findElement(By.id("adultInput")).sendKeys("2");
        driver.findElement(By.id("childInput")).clear();
        driver.findElement(By.id("childInput")).sendKeys("1");
        driver.findElement(By.xpath("//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='text-center']")).getText(), "No Results Found");
    }

}
