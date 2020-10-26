package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckSearchFunctionality extends BaseHelper {
    private static final String SEARCH_BOX_ID = "searchInput";
    private static final String SEARCH_RESULTS_CLASSNAME = "listing";
    private static final String SEARCH_RESULTS_LIST_CLASSNAME = "listing-row";

    @FindBy(className = "material-icons-outlined")
    WebElement searchButton;

    WebDriver driver;

    public CheckSearchFunctionality(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void enterTerm(String term) {
        wdWait.until(ExpectedConditions.visibilityOf(searchButton));
        click(searchButton);
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_BOX_ID)));
        WebElement searchBox = driver.findElement(By.id(SEARCH_BOX_ID));
        searchBox.sendKeys(term);
        searchBox.sendKeys(Keys.ENTER);
    }

    private WebElement getSearchResults() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(SEARCH_RESULTS_CLASSNAME)));
        WebElement searchResults = driver.findElement(By.className(SEARCH_RESULTS_CLASSNAME));
        js.executeScript("arguments[0].scrollIntoView();", searchResults);
        return searchResults;
    }

    public boolean checkSearchFunctionality(String term) {
        goToHomePage();
        enterTerm(term);
        WebElement searchResults = getSearchResults();
        return searchResults.getText().contains(term);
    }

    public boolean checkResultList(String term) {
        goToHomePage();
        enterTerm(term);
        WebElement searchResults = getSearchResults();
        List<WebElement> resultList = searchResults.findElements(By.className(SEARCH_RESULTS_LIST_CLASSNAME));
        resultList.removeIf((i) -> i.getText().isEmpty());
        return resultList.isEmpty();
    }
}
