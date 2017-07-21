import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class findElementsTest {
    WebDriver driver;

    @BeforeTest
    void setitUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/user/IdeaProjects/AutotestTestNG/ChromeDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
            void launch() throws InterruptedException {
        // Navigate to the URL
        driver.get("http://www.techbeamers.com");

        // Sleep for 5 seconds
        Thread.sleep(5000);

        // Here, the code below will select all rows matching the given XPath.

        List<WebElement> rows = driver.findElements(By.xpath("//table/tr"));

        // print the total number of elements
        System.out.println("Total selected rows are " + rows.size());

        // Now using Iterator we will iterate all elements
        Iterator<WebElement> iter = rows.iterator();

        // this will check whether list has some element or not
        while (iter.hasNext()) {

            // Iterate one by one
            WebElement item = iter.next();

            // get the text
            String label = item.getText();

            // print the text
            System.out.println("Row label is " + label);
        }
    }
}