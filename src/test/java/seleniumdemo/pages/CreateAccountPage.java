package seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumdemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountPage {

    private WebDriver driver;
    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpassword;

    @FindBy(xpath = "//button[@class='signupbtn btn_full btn btn-action btn-block btn-lg']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> errors;

    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void setPhone(String phone) {
        this.phone.sendKeys(phone);
    }

    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    public void setConfirmpassword(String password) {
        this.confirmpassword.sendKeys(password);
    }

    public void signUp() {
        signUpButton.click();
    }

    public List<String> getErrors() {
        List<String> errors = new ArrayList<>();
        for (WebElement el : this.errors) {
            errors.add(el.getText());
        }
        return errors;
    }

    public void fillSignUpForm(String firstName, String lastName, String email,
                               String phone, String password) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.phone.sendKeys(phone);
        this.password.sendKeys(password);
        this.confirmpassword.sendKeys(password);
    }

    public void fillSignUpForm(User user) {
        this.firstName.sendKeys(user.getFirstName());
        this.lastName.sendKeys(user.getLastName());
        this.email.sendKeys(user.getEmail());
        this.phone.sendKeys(user.getPhone());
        this.password.sendKeys(user.getPassword());
        this.confirmpassword.sendKeys(user.getPassword());
    }

}
