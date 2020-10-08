package pages;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SportsDirectSignInPage extends BaseHelper
{
    @FindBy (id = "Login_EmailAddress")
    WebElement emailBox;
    @FindBy (id = "Login_Password")
    WebElement passwordBox;
    @FindBy (id = "LoginButton")
    WebElement signInButton;

    WebDriver driver;

    public SportsDirectSignInPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }

    public void signInProcedure (String email, String password)
    {
        wdWait.until(ExpectedConditions.visibilityOf(emailBox));
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        click(signInButton);
    }


}
