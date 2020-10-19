package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FitnessBlenderGetWorkoutDetails extends BaseHelper {

    @FindBy(className = "stats")
    WebElement videoDetails;

    List<String> listOfWorkoutDetails = new ArrayList<>();

    WebDriver driver;

    public FitnessBlenderGetWorkoutDetails(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void makeListOfWorkoutDetails() {
        List<WebElement> videoDetailsList = videoDetails.findElements(By.className("detail-value"));
        for (WebElement info : videoDetailsList) {
            String[] elements = splitByCommaAndReturn(info);
            for (String element : elements){
                listOfWorkoutDetails.add(element.trim());
            }
        }

        WebElement bodyPart = videoDetails.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div/div/div/div/span[2]"));
        String[] elements = splitByCommaAndReturn(bodyPart);
        for (String element : elements){
            listOfWorkoutDetails.add(element.trim());
        }

        System.out.println(listOfWorkoutDetails);
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

    public List<String> getListOfWorkoutDetails() {
        return listOfWorkoutDetails;
    }
}
