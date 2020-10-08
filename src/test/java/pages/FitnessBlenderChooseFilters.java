package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FitnessBlenderChooseFilters extends BaseHelper
{
    @FindBy (className = "reduce")
    WebElement filterOptions;

    WebDriver driver;

    public FitnessBlenderChooseFilters(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }

    private void openFilters ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(filterOptions));
        click(filterOptions);

    }

    public void chooseFilterOptions ()
    {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-expander")));
        WebElement filterOptionsMenu = driver.findElement(By.id("search-expander"));
        js.executeScript("arguments[0].scrollIntoView();",filterOptionsMenu);
        WebElement minTime = filterOptionsMenu.findElement(By.name("minlength"));
        minTime.sendKeys("30");
        WebElement maxTime = filterOptionsMenu.findElement(By.name("maxlength"));
        maxTime.sendKeys("45");
        WebElement freeVideos = filterOptionsMenu.findElement(By.id("exclusive0"));
        click(freeVideos);
        WebElement difficulty = filterOptionsMenu.findElement(By.id("difficulty5"));
        click(difficulty);
        WebElement bodyFocus = filterOptionsMenu.findElement(By.id("focus4"));
        click(bodyFocus);
        WebElement trainingType1 = filterOptionsMenu.findElement(By.id("trainingtype8"));
        click(trainingType1);
        WebElement trainingType2 = filterOptionsMenu.findElement(By.id("trainingtype7"));
        click(trainingType2);
        WebElement equipment = filterOptionsMenu.findElement(By.id("equipment26"));
        click(equipment);

    }

    public void checkingFilters ()
    {
        openFilters();
        chooseFilterOptions();
    }
}
