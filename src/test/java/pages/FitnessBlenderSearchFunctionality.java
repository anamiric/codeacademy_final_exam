package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FitnessBlenderSearchFunctionality extends BaseHelper
{
    @FindBy (className = "material-icons-outlined")
    WebElement searchButton;

    WebDriver driver;

    public FitnessBlenderSearchFunctionality(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }

    private void navigateToURL ()
    {
        driver.get("https://www.fitnessblender.com/");
    }

    private void enterTerm (String term)
    {
        wdWait.until(ExpectedConditions.visibilityOf(searchButton));
        click(searchButton);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys (term);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void fitnessBlenderSearchFunctionality ()
    {
        navigateToURL();
        enterTerm("lower body workout");
    }

}
