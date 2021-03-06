
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 7/17/17.
 */
public class GoogleUI3dependency {

    WebDriver driver;

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://accounts.google.com");

    }

    void PerformStep(String inputField, String input, String button) {
        WebElement field = driver.findElement(By.cssSelector(inputField));
        field.clear();
        field.sendKeys(input);
        driver.findElement(By.cssSelector(button))
                .click();
        mySleep(3);
    }

    void FirstStep(String input) {
        PerformStep("input[type='email']", input, "div[role='button']");
    }

    void SecondStep(String input) {
        PerformStep("input[name='password']", input, "div#passwordNext");
    }


    @Test (groups = "positive", priority = 2)
    void SuccesfulLoginFirstStep() {
        FirstStep("safonov.sergi@gmail.com");
        Assert.assertTrue(doesElementExists("input[type='password']"));
    }

    @Test (groups = "positive", priority = 4, dependsOnMethods = {"SuccesfulLoginFirstStep"})
    void SuccessfullLoginSecondStep() {
        SecondStep("Notminepassword");
        Assert.assertFalse(doesElementExists("input[type='password']"));
    }
    @Test (groups = "negative", priority = 1 )
    void BadLoginFirstStep() {
        FirstStep("bafds32rfdspohka@gmail.com");
        Assert.assertFalse(doesElementExists("input[name='password']"));
    }

    @Test (groups = "negative", priority = 3, dependsOnMethods = {"SuccesfulLoginFirstStep"})
    void BadLoginSecondStep() {
        SecondStep("123123123");
        Assert.assertTrue(doesElementExists("input[type='password']"));
    }


    boolean doesElementExists(String selector) {
        try {
            driver.findElement(By.cssSelector(selector));
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    void mySleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }   catch (InterruptedException e) {

        }
    }

@AfterTest
    void finishIt() {
        driver.manage().deleteCookieNamed("GAPS");
        driver.quit();
}

}
