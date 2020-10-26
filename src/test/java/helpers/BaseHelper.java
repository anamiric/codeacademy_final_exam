package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {

    private static final String URL = "https://www.fitnessblender.com/";

    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wdWait = new WebDriverWait(driver, 60);
    protected static JavascriptExecutor js = (JavascriptExecutor) driver;


    protected void goToHomePage() {
        driver.get(URL);
    }

    protected static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }


    public static WebElement returnElementAttValue(String attributeName, String attributeValue) {
        String selector = "[" + attributeName + "=" + attributeValue + "]";
        WebElement element = driver.findElement(By.cssSelector(selector));
        return element;
    }

}
