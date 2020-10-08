package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.SportsDirectHomePage;
import pages.SportsDirectSignInPage;

public class SportsDirectLoginTest extends BaseTest {

    @Test
    public void positiveLoginTest() throws InterruptedException {

        directHomePageTest();

        signInTest("ana.zavrsni@gmail.com", "zavrsnirad123");

        WebElement profileIcon = driver.findElement(By.id("divAccount"));
        wdWait.until(ExpectedConditions.visibilityOf(profileIcon));
        click(profileIcon);
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("accountBox")));
        WebElement accountDetails = driver.findElement(By.className("accountBox"));
        click(accountDetails);
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtEmailAddress")));

        checkUserDataFieldValue("txtEmailAddress", "ana.zavrsni@gmail.com");
        checkUserDataFieldValue("txtFirstName", "Ana");
        checkUserDataFieldValue("txtLastName", "Miric");

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("sidebar-nav")));
        WebElement accountMenu = driver.findElement(By.className("sidebar-nav"));
        Assert.assertTrue("sign out button is not displayed", accountMenu.getText().contains("Sign Out"));

        Thread.sleep(3000); // sleep ostavljen zbog vizuelne konfirmacije

    }

    @Test
    public void negativeLoginTest() throws InterruptedException {

        directHomePageTest();

        signInTest("ana.zavrsni@gmail.com", "zavrsnirad");

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("field-validation-error")));
        WebElement userMessage = driver.findElement(By.className("field-validation-error"));
        Assert.assertTrue("message is not displayed", userMessage.getText().contains("This email address or password is incorrect"));
        Thread.sleep(3000); // sleep ostavljen zbog vizuelne konfirmacije

    }

    private void checkUserDataFieldValue(String field, String value) {
        WebElement emailText = driver.findElement(By.id(field));
        Assert.assertTrue("Text is not displayed", emailText.getAttribute("value").contains(value));
    }

    public void directHomePageTest() {
        SportsDirectHomePage hp = new SportsDirectHomePage(driver);
        hp.sportsDirectHomePage();
    }

    public void signInTest(String email, String password) {
        SportsDirectSignInPage sip = new SportsDirectSignInPage(driver);
        sip.signInProcedure(email, password);
    }


}
