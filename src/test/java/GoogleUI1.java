import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 7/17/17.
 */
public class GoogleUI1 {

    WebDriver driver;

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    void PerformStep(String inputField, String input, String button) {
        driver.findElement(By.cssSelector(inputField))
                .sendKeys(input);
        driver.findElement(By.cssSelector(button))
                .click();
        mySleep(3);
    }


    @Test ()
    void SuccesfulLoginFirstStep() {
        driver.get("https://accounts.google.com");
        PerformStep("input[type='email']", "safonov.sergi@gmail.com", "div[role='button']");
        Assert.assertTrue(doesElementExists("input[type='password']"));
    }

    @Test ()
    void SuccessfullLoginSecondStep() {
        PerformStep("input[name='password']", "notminepassword", "div#passwordNext");
        Assert.assertFalse(doesElementExists("input[type='password']"));
    }
    @Test ()
    void BadLoginFirstStep() {
        PerformStep("input[type='email']", "bafds32rfdspohka@gmail.com", "div[role='button']");

        Assert.assertFalse(doesElementExists("input[type='password']"));
    }

    @Test ()
    void BadLoginSecondStep() {
        PerformStep("input[name='password']", "123123123", "div#passwordNext");

        Assert.assertFalse(doesElementExists("input[type='password']"));
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



}
