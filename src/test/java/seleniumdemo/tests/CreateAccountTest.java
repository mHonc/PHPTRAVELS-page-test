package seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumdemo.model.User;
import seleniumdemo.pages.CreateAccountPage;
import seleniumdemo.pages.HotelSearchPage;
import seleniumdemo.pages.LoggedUserPage;

import java.util.List;
import java.util.Random;


public class CreateAccountTest extends BaseTest {

    @Test
    public void signUpTest() throws InterruptedException {
        //dla unikanego adresu email
        int min = 100_000_000;
        int max = 999_999_999;
        Random random = new Random();
        int randomNineDigitNumber = random.nextInt(max - min + 1) + min;
        String firstName = "Kamil";
        String lastName = "Max";
        User user = new User(firstName, lastName,
                String.valueOf(randomNineDigitNumber) + "@gmail.com", "999999999", "Kamil123");

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpPage()
                .fillSignUpForm(user)
                .signUp();

        Thread.sleep(10000);
        String result = loggedUserPage.getWelcomeHeader();
        Assert.assertEquals("Hi, " + firstName + " " + lastName, result);
    }

    @Test
    public void signUpWithNoDataTest() {
        CreateAccountPage createAccountPage = new HotelSearchPage(driver)
                .openSignUpPage();
        createAccountPage.signUp();

        String error1 = "The Email field is required.";
        String error2 = "The Password field is required.";
        String error3 = "The First name field is required.";
        String error4 = "The Last Name field is required.";

        List<String> errors = createAccountPage.getErrors();
        Assert.assertTrue(errors.contains(error1));
        Assert.assertTrue(errors.contains(error2));
        Assert.assertTrue(errors.contains(error3));
        Assert.assertTrue(errors.contains(error4));
    }
}
