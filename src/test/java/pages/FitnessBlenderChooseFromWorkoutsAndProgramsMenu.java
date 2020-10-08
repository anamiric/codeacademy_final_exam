package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FitnessBlenderChooseFromWorkoutsAndProgramsMenu extends BaseHelper
{
    @FindBy (className = "workouts")
    WebElement workoutsAndPrograms;

    WebDriver driver;

    public FitnessBlenderChooseFromWorkoutsAndProgramsMenu(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }

    private void navigateToURL ()
    {
        driver.get("https://www.fitnessblender.com/");
    }

    public void workoutDropdownMenu (Integer index)
    {
        wdWait.until(ExpectedConditions.visibilityOf(workoutsAndPrograms));
        Actions actions = new Actions(driver);
        actions.moveToElement(workoutsAndPrograms).perform();
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("workout-dropdown")));
        WebElement dropdownMenu = driver.findElement(By.id("workout-dropdown"));
        List<WebElement> dropdownMenuList = dropdownMenu.findElements(By.className("menu-icon"));
        WebElement workoutVideos = dropdownMenuList.get(index);
        click(workoutVideos);

    }

    public void chooseFromWorkoutDropdownMenu (Integer index)
    {
        navigateToURL();
        workoutDropdownMenu(index);

    }
}
