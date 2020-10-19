package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.util.List;

public class FitnessBlenderTest extends BaseTest {
    @Test
    public void fitnessBlenderSearchTest() throws InterruptedException {
        FitnessBlenderSearchFunctionality search = new FitnessBlenderSearchFunctionality(driver);
        search.fitnessBlenderSearchFunctionality();

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("listing")));
        WebElement searchResults = driver.findElement(By.className("listing"));
        js.executeScript("arguments[0].scrollIntoView();", searchResults);

        Assert.assertTrue("results do not contain searched term", searchResults.getText().contains("lower body workout"));

        List<WebElement> resultList = searchResults.findElements(By.className("listing-row"));
        resultList.removeIf((i) -> i.getText().isEmpty());

        System.out.println("List size is:" + resultList.size());
        System.out.println("The seventh article in the list is:" + resultList.get(6).getText());

        Thread.sleep(3000); // sleep ostavljen zbog vizuelne konfirmacije
    }

    @Test
    public void fitnessBlenderVideoPlayTest() throws InterruptedException {
        FitnessBlenderChooseFromWorkoutsAndProgramsMenu workout = new FitnessBlenderChooseFromWorkoutsAndProgramsMenu(driver);
        workout.chooseFromWorkoutDropdownMenu(0);

        FitnessBlenderChooseFilters checkFilters = new FitnessBlenderChooseFilters(driver);
        checkFilters.checkingFilters();

        FitnessBlenderGetWorkoutDetails details = new FitnessBlenderGetWorkoutDetails(driver);
        details.makeListOfWorkoutDetails();

        String durationOfWorkout = details.getListOfWorkoutDetails().get(0);
        durationOfWorkout = durationOfWorkout.replace("Minutes", "").trim();
        Integer workoutTime = Integer.parseInt(durationOfWorkout);

        Assert.assertTrue("Time of workout is not adequate", (workoutTime > 30) && (workoutTime < 45));

        boolean elementsExists = true;
        for (String element : checkFilters.getListOfChosenFilters()) {
            if (!details.getListOfWorkoutDetails().contains(element)) {
                elementsExists = false;
                break;
            }
        }

        if (elementsExists) {
            System.out.println("Video matches to all chosen filters");
        } else {
            System.out.println("Video does not match to chosen filters");
        }

        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div")));
        WebElement frame = driver.findElement(By.xpath("//iframe[contains(@src,'https://www.youtube.com/embed/OUo0QofYx28?rel=0&origin=https://www.fitnessblender.com')]"));
        driver.switchTo().frame(frame);

        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("ytp-large-play-button-bg")));
        WebElement playButton = driver.findElement(By.className("ytp-large-play-button-bg"));
        Assert.assertTrue("The video can not be played", playButton.isEnabled());
        driver.switchTo().defaultContent();

        Thread.sleep(3000); // sleep ostavljen zbog vizuelne konfirmacije
    }

    @Test
    public void fitnessBlenderAddToCartTest() throws InterruptedException {
        FitnessBlenderChooseFromWorkoutsAndProgramsMenu workout = new FitnessBlenderChooseFromWorkoutsAndProgramsMenu(driver);
        workout.chooseFromWorkoutDropdownMenu(1);

        FitnessBlenderAddWorkoutProgramsToCart cart = new FitnessBlenderAddWorkoutProgramsToCart(driver);
        cart.addingToCart();

        Assert.assertEquals("Lists are not equal.", cart.getListOfNamesOrderedPrograms(), cart.getListOfNamesOrderedProgramsCart());

        Thread.sleep(3000); // sleep ostavljen zbog vizuelne konfirmacije
    }
}
