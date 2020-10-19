package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FitnessBlenderChooseFilters extends BaseHelper
{
    @FindBy (className = "reduce")
    WebElement filterOptions;

    List<String> listOfChosenFilters = new ArrayList<>();

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
        WebElement difficulty = filterOptionsMenu.findElement(By.xpath("//*[@id=\"search-expander\"]/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/ul/li[5]/label/div/span[1]"));
        String difficultyText = difficulty.getText()+ "/5";
        System.out.println(difficultyText);
        click(difficulty);
        WebElement bodyFocus = filterOptionsMenu.findElement(By.xpath("//*[@id=\"search-expander\"]/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/ul/li[4]/label/div/span[1]"));
        String bodyFocusText = bodyFocus.getText();
        if (bodyFocusText.equals("Total"))
        {
            bodyFocusText+= " Body";
        }
        System.out.println(bodyFocusText);
        click(bodyFocus);
        WebElement trainingType1 = filterOptionsMenu.findElement(By.id("trainingtype7")).findElement(By.xpath("/html/body/main/div/section[3]/div/div/div[2]/div[2]/div/div[2]/ul/li[1]/label/div/span[1]"));
        String trainingType1Text = trainingType1.getText();
        System.out.println(trainingType1Text);
        click(trainingType1);
        WebElement trainingType2 = filterOptionsMenu.findElement(By.id("trainingtype8")).findElement(By.xpath("/html/body/main/div/section[3]/div/div/div[2]/div[2]/div/div[2]/ul/li[4]/label/div/span[1]"));
        String trainingType2Text = trainingType2.getText();
        System.out.println(trainingType2Text);
        click(trainingType2);
        WebElement equipment = filterOptionsMenu.findElement(By.xpath("//*[@id=\"search-expander\"]/div/div/div[2]/div[3]/div/div[2]/ul/li[1]/label/div/span[1]"));
        String equipmentText = equipment.getText();
        System.out.println(equipmentText);
        click(equipment);

        listOfChosenFilters.add(difficultyText);
        listOfChosenFilters.add(bodyFocusText);
        listOfChosenFilters.add(trainingType1Text);
        listOfChosenFilters.add(trainingType2Text);
        listOfChosenFilters.add(equipmentText);

    }

    private void clickOnVideo ()
    {
        WebElement video = driver.findElement(By.className("video-item"));
        click(video);
    }

    public void checkingFilters ()
    {
        openFilters();
        chooseFilterOptions();
        clickOnVideo();
    }

    public List<String> getListOfChosenFilters ()
    {
        return listOfChosenFilters;
    }
}
