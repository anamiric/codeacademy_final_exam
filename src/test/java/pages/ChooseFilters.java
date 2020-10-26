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

public class ChooseFilters extends BaseHelper {
    @FindBy(className = "reduce")
    WebElement filterOptions;

    List<String> listOfChosenFilters = new ArrayList<>();

    WebDriver driver;

    public ChooseFilters(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void openFilters() {
        wdWait.until(ExpectedConditions.visibilityOf(filterOptions));
        click(filterOptions);

    }

    private void sendLocatorAndTime(WebElement element, String locator, String time) {
        WebElement timeElement = element.findElement(By.name(locator));
        timeElement.sendKeys(time);

    }

    public void chooseFilterOptions() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-expander")));
        WebElement filterOptionsMenu = driver.findElement(By.id("search-expander"));
        js.executeScript("arguments[0].scrollIntoView();", filterOptionsMenu);

        sendLocatorAndTime(filterOptionsMenu, "minlength", "30");
        sendLocatorAndTime(filterOptionsMenu, "maxlength", "45");


        WebElement freeVideos = filterOptionsMenu.findElement(By.id("exclusive0"));
        click(freeVideos);
        WebElement difficulty = filterOptionsMenu.findElement(By.xpath("//*[@id=\"search-expander\"]/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/ul/li[5]/label/div/span[1]"));
        String difficultyText = difficulty.getText() + "/5";
        click(difficulty);
        WebElement bodyFocus = filterOptionsMenu.findElement(By.xpath("//*[@id=\"search-expander\"]/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/ul/li[4]/label/div/span[1]"));
        String bodyFocusText = bodyFocus.getText();
        if (bodyFocusText.equals("Total")) {
            bodyFocusText += " Body";
        }
        click(bodyFocus);
        WebElement trainingType1 = filterOptionsMenu.findElement(By.id("trainingtype7")).findElement(By.xpath("/html/body/main/div/section[3]/div/div/div[2]/div[2]/div/div[2]/ul/li[1]/label/div/span[1]"));
        String trainingType1Text = trainingType1.getText();
        click(trainingType1);
        WebElement trainingType2 = filterOptionsMenu.findElement(By.id("trainingtype8")).findElement(By.xpath("/html/body/main/div/section[3]/div/div/div[2]/div[2]/div/div[2]/ul/li[4]/label/div/span[1]"));
        String trainingType2Text = trainingType2.getText();
        click(trainingType2);
        WebElement equipment = filterOptionsMenu.findElement(By.xpath("//*[@id=\"search-expander\"]/div/div/div[2]/div[3]/div/div[2]/ul/li[1]/label/div/span[1]"));
        String equipmentText = equipment.getText();
        click(equipment);

        listOfChosenFilters.add(difficultyText);
        listOfChosenFilters.add(bodyFocusText);
        listOfChosenFilters.add(trainingType1Text);
        listOfChosenFilters.add(trainingType2Text);
        listOfChosenFilters.add(equipmentText);

    }

    private void clickOnVideo() {
        WebElement video = driver.findElement(By.id("video-696"));
        click(video);
    }

    public boolean isPlayButtonEnabled() {

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div")));
        WebElement frame = driver.findElement(By.xpath("//iframe[contains(@src,'https://www.youtube.com/embed/OUo0QofYx28?rel=0&origin=https://www.fitnessblender.com')]"));
        driver.switchTo().frame(frame);
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("ytp-large-play-button-bg")));
        WebElement playButton = driver.findElement(By.className("ytp-large-play-button-bg"));
        return playButton.isEnabled();
    }

    public void checkFiltersAndChooseVideo() {
        openFilters();
        chooseFilterOptions();
        clickOnVideo();
    }

    public List<String> getListOfChosenFilters() {
        return listOfChosenFilters;
    }
}
