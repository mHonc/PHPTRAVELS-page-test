package seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedUserPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    WebElement welcomeHeader;

    public LoggedUserPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeHeader(){
        return welcomeHeader.getText();
    }
}
