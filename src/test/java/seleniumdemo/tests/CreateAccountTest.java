package seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.model.User;
import seleniumdemo.pages.CreateAccountPage;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.LoggedUserPage;
import seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;
import java.util.Random;


public class CreateAccountTest extends BaseTest {

    @Test
    public void signUpTest() throws InterruptedException, IOException {
        ExtentTest test = extentReports.createTest("Sign up Test");
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
        User user = new User(firstName, lastName,
                String.valueOf(randomNineDigitNumber) + "@gmail.com", "999999999", "Kamil123");
        createAccountPage.fillSignUpForm(user);
        test.log(Status.PASS, "Filled sign up form");
        createAccountPage.signUp();

        Thread.sleep(10000);
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        String result = loggedUserPage.getWelcomeHeader();
        Assert.assertEquals("Hi, " + firstName + " " + lastName, result);
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void signUpWithNoDataTest() throws IOException {
        ExtentTest test = extentReports.createTest("Sign up with no data Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpPage();
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.signUp();
        test.log(Status.PASS, "Sign up button clicked");

        String error1 = "The Email field is required.";
        String error2 = "The Password field is required.";
        String error3 = "The First name field is required.";
        String error4 = "The Last Name field is required.";

        List<String> errors = createAccountPage.getErrors();
        Assert.assertTrue(errors.contains(error1));
        Assert.assertTrue(errors.contains(error2));
        Assert.assertTrue(errors.contains(error3));
        Assert.assertTrue(errors.contains(error4));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }
}
