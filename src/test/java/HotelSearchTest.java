import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class HotelSearchTest extends BaseTest{

    @Test
    public void searchHotelTest(){
        driver.findElement(By.id("s2id_autogen8")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        //first method
        driver.findElement(By.xpath("//input[@class='form input-lg dpd1']")).sendKeys("05/12/2023");
        driver.findElement(By.xpath("//input[@class='form input-lg dpd2']")).sendKeys("12/12/2023");
        //second method
        //driver.findElement(By.xpath("//input[@class='form input-lg dpd2']")).click();
        //driver.findElements(By.xpath("//td[@class='day ' and text()='16']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultInput")).clear();
        driver.findElement(By.id("adultInput")).sendKeys("4");
        driver.findElement(By.id("childInput")).clear();
        driver.findElement(By.id("childInput")).sendKeys("2");
        driver.findElement(By.xpath("//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")).click();

        List<WebElement> elements = driver.findElements(By.xpath("//h4/a/b"));
        elements.forEach(el -> System.out.println(el.getText()));
        Assert.assertEquals(elements.size(), 4);

        List<WebElement> elementsCities = driver.findElements(By.xpath("//a[@class='go-right ellipsisFIX go-text-right mob-fs14']"));
        for(WebElement el : elementsCities){
            Assert.assertEquals(el.getText(), "dubai");
        }
    }

}
