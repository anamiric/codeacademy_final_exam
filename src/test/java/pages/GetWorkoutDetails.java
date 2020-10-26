package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GetWorkoutDetails extends BaseHelper {

    @FindBy(className = "video-details")
    WebElement videoDetails;

    List<String> listOfWorkoutDetails = new ArrayList<>();

    WebDriver driver;

    public GetWorkoutDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void makeListOfWorkoutDetails() {
        List<WebElement> videoDetailsList = videoDetails.findElements(By.className("detail-value"));
        for (WebElement info : videoDetailsList) {
            String[] elements = splitByCommaAndReturn(info);
            for (String element : elements) {
                listOfWorkoutDetails.add(element.trim());
            }
        }

        WebElement bodyPart = videoDetails.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div/div/div/div/span[2]"));
        String[] elements = splitByCommaAndReturn(bodyPart);
        for (String element : elements) {
            listOfWorkoutDetails.add(element.trim());
        }

    }

    public String[] splitByCommaAndReturn(WebElement element) {
        String elementText = element.getText();

        if (elementText.contains(",")) {
            String[] checkElements = elementText.split(",");
            return checkElements;
        } else {
            return new String[]{elementText};
        }
    }

    public boolean isTimeBetweenChosenValues() {

        String durationOfWorkout = listOfWorkoutDetails.get(0);
        durationOfWorkout = durationOfWorkout.replace("Minutes", "").trim();
        int workoutTime = Integer.parseInt(durationOfWorkout);
        return (workoutTime > 30) && (workoutTime < 45);

    }

    public boolean checkChosenFilters(List<String> listOfChosenFilters) {
        boolean elementsExists = true;
        for (String element : listOfChosenFilters) {
            if (!listOfWorkoutDetails.contains(element)) {
                elementsExists = false;
                break;
            }
        }
        return elementsExists;

    }

}
