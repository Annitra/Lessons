import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by user on 7/19/17.
 */
public class FileUpload {
    WebDriver driver;
    Robot r = new Robot();
    String FileName = "0-02-05-5895786b570f7f4b89f37944f23038dd1fe8b155de938d7041c4a9babf241c9a_full.mp4";

    public FileUpload() throws AWTException {
    }

    void mouseClick(Integer x, Integer y) {
        r.mouseMove(x,y);
        r.mousePress(InputEvent.BUTTON1_MASK );
        r.mouseRelease(InputEvent.BUTTON1_MASK );
    }

    @BeforeTest
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/user/IdeaProjects/AutotestTestNG/ChromeDriver/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.box.com/");
    }
    @Test
    void Login() {

        driver.findElement(By.cssSelector("a.user-menu_log-in")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("rvalek@intersog.de");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("welcome2hillel");
        driver.findElement(By.cssSelector("button[type='submit']")).submit();
    }
    @Test (dependsOnMethods = "Login")
    void uploadFile() throws InterruptedException, AWTException {
        driver.findElement(By.cssSelector("a.upload-menu-btn")).click();
        driver.findElement(By.cssSelector("li.upload-file-handler-target")).click();
        if ( OS.isFamilyMac() ) {
            mouseClick(350, 330);
            mouseClick(570, 140);
            mouseClick(960, 450);
        }
        Thread.sleep(10000);

        System.out.println("i found 0th element from all elements: " +
                driver.findElements(By.cssSelector("a.item-name-link")).get(0)
                .getAttribute("innerHTML") + "\n");

        // example from http://www.techbeamers.com/findelement-and-findelements-commands-examples/
        // print the total number of elements
        // Now using Iterator we will iterate all elements
        // this will check whether list has some element or not
        List<WebElement> rowsList = driver.findElements(By.cssSelector("a.item-name-link"));
        System.out.println("Total selected rows are " + rowsList.size());
        Iterator<WebElement> iter = rowsList.iterator();

        while (iter.hasNext()) {
            // Iterate one by one
            // get the text
            // print the text
            WebElement checkItem = iter.next();
            String label = checkItem.getText();
            //System.out.println("Row label is " + label + " and it is " + label.contentEquals(FileName));
                if (label.contentEquals(FileName)) {
                    Assert.assertTrue(true);
                    System.out.println( "i found that row label and it is " + label + " and it is fully the same - " +
                            "i've checked it with equals: " + label.contentEquals(FileName) );
                    break;
                } else
                        continue;
        }
        // так как другие могут заливать файл и мой отобразится не первым в списке - отказываюсь от поиска по первому
        // найденному элементу
        //WebElement BoxContent = driver.findElement(By.cssSelector("a.item-name-link"));
        //String Txt = BoxContent.getText();
        //System.out.println("i found such text: " + Txt);
        //System.out.print("i found innerHTML:  " + BoxContent.getAttribute("innerHTML"));
        //Assert.assertTrue(FileName.equals(Txt));
    }

    @AfterTest
    void quitDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }





}
