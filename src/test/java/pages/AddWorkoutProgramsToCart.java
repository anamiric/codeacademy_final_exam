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

public class AddWorkoutProgramsToCart extends BaseHelper {
    @FindBy(className = "content-container")
    WebElement workoutPrograms;

    WebElement orderContainer;

    List<WebElement> workoutProgramsList;
    List<WebElement> cartList;

    double totalPrice = 0;

    List<String> listOfNamesOrderedPrograms = new ArrayList<>();
    List<String> listOfNamesOrderedProgramsCart = new ArrayList<>();

    WebDriver driver;

    public AddWorkoutProgramsToCart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void listOfWorkoutPrograms() {
        wdWait.until(ExpectedConditions.visibilityOf(workoutPrograms));
        js.executeScript("arguments[0].scrollIntoView();", workoutPrograms);
        workoutProgramsList = workoutPrograms.findElements(By.className("article-item"));
        workoutProgramsList.removeIf(workoutProgram -> !checkIfElementExist(workoutProgram));
    }

    private boolean checkIfElementExist(WebElement workoutProgram) {

        List<WebElement> addToBagButtonList = workoutProgram.findElements(By.className("btn-wrapper"));
        if (addToBagButtonList != null && addToBagButtonList.size() > 0) {
            return true;
        }
        return false;
    }

    private double chooseWorkoutPrograms(WebElement workoutProgram) {
        wdWait.until(ExpectedConditions.visibilityOf(workoutPrograms));
        js.executeScript("arguments[0].scrollIntoView();", workoutPrograms);
        String workOutId = workoutProgram.findElement(By.className("contents")).getAttribute("id");
        System.out.println(workOutId);

        String relativeXPathForName = "//*[@id=\"" + workOutId + "\"]/div[1]/div/div[2]/h3";

        WebElement workoutName = workoutProgram.findElement(By.xpath(relativeXPathForName));
        String workoutNameText = workoutName.getText();
        System.out.println(workoutNameText);
        WebElement addToBagButton = workoutProgram.findElement(By.className("btn-wrapper"));
        click(addToBagButton);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("centered-container")));
        orderContainer = driver.findElement(By.className("centered-container"));
        js.executeScript("arguments[0].scrollIntoView();", orderContainer);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("cart_total")));
        WebElement total = orderContainer.findElement(By.className("cart_total"));
        String totalText = total.getText();
        totalText = totalText.replace("$", "").replace("Order Total", "");
        listOfNamesOrderedPrograms.add(workoutNameText);

        return Double.parseDouble(totalText);


    }

    private void cartListPopulate() {
        WebElement cart = driver.findElement(By.className("listing"));
        cartList = cart.findElements(By.className("cart_item_title"));
        for (WebElement orderNameCart : cartList) {
            WebElement orderName = orderNameCart.findElement(By.className("no-margin")).findElement(By.className("demi"));
            String programName = orderName.getText();
            listOfNamesOrderedProgramsCart.add(programName);

        }

        List<WebElement> specialOffer = driver.findElements(By.id("coupon-no-thanks"));
        if (specialOffer.size() > 0) {
            js.executeScript("arguments[0].click();", specialOffer.get(0));
        }

        System.out.println(listOfNamesOrderedProgramsCart);
    }

    public void addingToCart() {

        double minOrderPrice = 20.00;
        int numberOfOrders = 0;

        while (totalPrice < minOrderPrice) {
            listOfWorkoutPrograms();
            double orderPrice = chooseWorkoutPrograms(workoutProgramsList.get(numberOfOrders));
            numberOfOrders++;
            totalPrice = orderPrice;
            System.out.println("Total is:" + totalPrice);

            if(totalPrice < minOrderPrice){
                WebElement continueShoppingButton = orderContainer.findElement(By.className("-large-medium-only"));
                click(continueShoppingButton);
            }

        }

        cartListPopulate();
    }

    public List<String> getListOfNamesOrderedPrograms() {
        java.util.Collections.sort(listOfNamesOrderedPrograms);
        return listOfNamesOrderedPrograms;
    }

    public List<String> getListOfNamesOrderedProgramsCart() {
        java.util.Collections.sort(listOfNamesOrderedProgramsCart);

        return listOfNamesOrderedProgramsCart;
    }


}
