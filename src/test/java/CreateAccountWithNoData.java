import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CreateAccountWithNoData {
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
        driver.findElement(By.xpath("//button[@class='signupbtn btn_full btn btn-action btn-block btn-lg']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).isDisplayed());

        String error1 = "The Email field is required.";
        String error2 = "The Password field is required.";
        String error3 = "The First name field is required.";
        String error4 = "The Last Name field is required.";


        List<WebElement> elements = driver.findElements(By.xpath("//p"));
        List<String> errors = new ArrayList<>();
        for(WebElement el : elements){
            errors.add(el.getText());
        }
        Assert.assertTrue(errors.contains(error1));
        Assert.assertTrue(errors.contains(error2));
        Assert.assertTrue(errors.contains(error3));
        Assert.assertTrue(errors.contains(error4));
        driver.quit();
    }

}
