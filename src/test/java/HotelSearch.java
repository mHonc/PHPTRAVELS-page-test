import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class HotelSearch {
    @Test
    public void searchHotel(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/m-hotels");

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

        //driver.quit();
    }

}
