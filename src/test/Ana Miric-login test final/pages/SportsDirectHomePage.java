package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SportsDirectHomePage extends BaseHelper
{
    @FindBy (id = "divSignIn")
    WebElement linkToSignIn;

    WebDriver driver;

    public SportsDirectHomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }


    private void goToURL ()
    {
        driver.get("https://rs.sportsdirect.com/");
    }

    private void closePopup () {

        List<WebElement> popup = driver.findElements(By.xpath("/html/body/div[9]/div/div/div[1]/button"));
        if (popup.size()>0) {
            click(popup.get(0));
        }
    }

    private void navigateToSignInPage ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(linkToSignIn));
        click(linkToSignIn);
    }


    public void sportsDirectHomePage ()
    {
        goToURL();
        closePopup();
        navigateToSignInPage();
    }
}
