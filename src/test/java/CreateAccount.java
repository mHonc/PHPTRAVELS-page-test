import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CreateAccount {
    @Test
    public void signUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://www.kurs-selenium.pl/demo/m-hotels");

        driver.findElements(By.id("li_myaccount")).stream().
                filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text() = '  Sign Up']")).get(1).click();


        String firstName = "Kamil";
        String lastName = "Max";
        //dla unikanego adresu email
        int min = 100_000_000;
        int max = 999_999_999;
        Random random = new Random();
        int randomNineDigitNumber = random.nextInt(max - min + 1) + min;
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("999999999");
        driver.findElement(By.name("email")).sendKeys(String.valueOf(randomNineDigitNumber)+"@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Kamil123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Kamil123");
        driver.findElement(By.xpath("//button[@class='signupbtn btn_full btn btn-action btn-block btn-lg']")).click();


        Thread.sleep(10000);
        System.out.println(driver.getCurrentUrl());
        String result = driver.findElement(By.xpath("//h3[@class='RTL']")).getText();
        Assert.assertEquals("Hi, " + firstName + " " + lastName, result);
        driver.quit();
    }

}
