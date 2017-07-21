import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by user on 7/17/17.
 */
public class GoogleUI {

    WebDriver driver;

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test ()
    void SuccesfulLogin() {
        driver.get("https://accounts.google.com");
        driver.findElement(By.cssSelector("input[type='email']"))
                .sendKeys("bapohka@gmail.com");
        driver.findElement(By.cssSelector("div[role='button']"))
                .click();
        mySleep(3);
        Assert.assertTrue(doesElementExists("input[type='password']"));
    }

    @Test ()
    void SuccessfullLoginSecondStep() {
        driver.findElement(By.cssSelector("input[name='password']"))
                .sendKeys("123123123");
        driver.findElement(By.cssSelector("div[role='button']"))
                .click();
        mySleep(3);
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
