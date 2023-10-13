package seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.pages.CreateAccountPage;
import seleniumdemo.pages.HotelSearchPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CreateAccountTest extends BaseTest{

    @Test
    public void signUpTest() throws InterruptedException {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpPage();

        //dla unikanego adresu email
        int min = 100_000_000;
        int max = 999_999_999;
        Random random = new Random();
        int randomNineDigitNumber = random.nextInt(max - min + 1) + min;
        String firstName = "Kamil";
        String lastName = "Max";

        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.setFirstName(firstName);
        createAccountPage.setLastName(lastName);
        createAccountPage.setPhone("999999999");
        createAccountPage.setEmail(String.valueOf(randomNineDigitNumber)+"@gmail.com");
        createAccountPage.setPassword("Kamil123");
        createAccountPage.setConfirmpassword("Kamil123");
        createAccountPage.signUp();

        Thread.sleep(10000);
        System.out.println(driver.getCurrentUrl());
        String result = driver.findElement(By.xpath("//h3[@class='RTL']")).getText();
        Assert.assertEquals("Hi, " + firstName + " " + lastName, result);
    }

    @Test
    public void signUpWithNoDataTest() throws InterruptedException {
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
    }
}
