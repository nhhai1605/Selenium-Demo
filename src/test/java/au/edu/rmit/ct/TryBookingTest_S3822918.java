/**
 *
 * Name: John Smith (( Update with your name here ))
 * Student ID: s45045012  (( Update with your ID))
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class TryBookingTest_S3822918
{
    WebDriver myDriver;

    void print(String str)
    {
        System.out.println(str);
    }
    @Test
    @Order(1)
    @DisplayName("Enter TryBooking Website")

    void enterTryBookingWebsite() throws Exception
    {
        String url = "https://www.trybooking.com/";
        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(3000));
        myDriver.get(url);
        try
        {
            //If the title is not "Event Ticketing & Booking Platform | TryBooking Australia", it means we entered the wrong site.
            wait.until(ExpectedConditions.titleIs("Event Ticketing & Booking Platform | TryBooking Australia"));
        } catch (Exception e)
        {
            fail("Enter TryBooking Website failed");
        }
        print("------ Checking for entering the TryBooking website passed -------");
    }
    @Test
    @Order(2)
    @RepeatedTest(10) //Repeat 10 times here because of the randomness of each time loading the page.
    @DisplayName("Check there are 12 unique events near me")
    void checkUniqueEvents() throws Exception
    {
        String url = "https://www.trybooking.com/book/search";
        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(3000));
        myDriver.get(url);
        try
        {
            //If the title is not "Search Events | TryBooking Australia", it means we entered the wrong site.
            wait.until(ExpectedConditions.titleIs("Search Events | TryBooking Australia"));
        } catch (Exception e)
        {
            fail("Enter Search Events site failed");
        }
        int count = 0;

        try
        {
            //Because there are multiple events with images, so we need to wait for all elements to fully load.
            List<WebElement> webElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='nearby-events-container']/div")));
            List<String> eventDatesAndLocations = new ArrayList<>();
            print("Total Events: " + webElements.size());
            for(WebElement webElement : webElements)
            {
                String eventName = webElement.getAttribute("data-event-name");
                String eventDate = webElement.findElements(By.xpath(".//span")).get(0).getText();
                String eventLocation = webElement.findElements(By.xpath(".//span")).get(1).getText();
                String eventDateAndLocation = eventDate + " " + eventLocation;
                eventDateAndLocation = eventDateAndLocation.replaceAll("[^a-zA-Z0-9\\s]", "");
                if (!eventDatesAndLocations.contains(eventDateAndLocation))
                {
                    //Based on my observation, most of the duplicate events may have completely different name, but the date and location are the same.
                    //So, I will only base on the date and location to determine if the event is duplicate or not.
                    count++;
                    eventDatesAndLocations.add(eventDateAndLocation);
                }

            }
            print("Unique Event Number: " + count);
            assertTrue(count >= 12);
            print("------ Checking for 12 unique events passed -------");

        }
        catch (Exception e)
        {
            fail("Check there are 12 unique events near me failed");
        }


    }

    @Test
    @Order(3)
    @DisplayName("Check booking into an event")
    void checkBookingAnEvent() throws Exception
    {
        String url = "https://www.trybooking.com/CDKGX";
        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(3000));
        myDriver.get(url);
        try
        {
            //If the title is not "RMIT Golden Doughnut Award Ceremony [RMIT Testing Only] Tickets | TryBooking Australia",
            //it means we entered the wrong site.
            wait.until(ExpectedConditions.titleIs("RMIT Golden Doughnut Award Ceremony [RMIT Testing Only] Tickets | TryBooking Australia"));
        } catch (Exception e)
        {
            fail("Enter RMIT Golden Doughnut Award Ceremony site failed");
        }

        try
        {
            WebElement webElement =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='book-button-top']/div/div/button")));
            webElement.click();

            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Platypi')]")));
            webElement = webElement.findElement(By.xpath("./../.."));
            webElement = webElement.findElement(By.xpath(".//a"));
            webElement.click();

            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='step plus']")));
            webElement.click();
            Thread.sleep(1000); //Sleep for checking the input before the automation continue to next page.
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tickets-next-button")));
            webElement.click();


            WebElement idInputText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Student ID is required.']")));
            idInputText.sendKeys("s3822918");
            webElement = myDriver.findElement(By.xpath("//div[contains(@class, 'date-day')]"));
            webElement.click();
            Thread.sleep(1000); //Sleep here so that the dropdown is fully loaded.
            webElement = webElement.findElement(By.xpath("//*[@class='ss-option' and text()='21']"));
            webElement.click();


            webElement = myDriver.findElement(By.xpath("//div[contains(@class, 'date-month')]"));
            webElement.click();
            Thread.sleep(1000); //Sleep here so that the dropdown is fully loaded.
            webElement = webElement.findElement(By.xpath("//*[@class='ss-option' and text()='Oct']"));
            webElement.click();

            webElement = myDriver.findElement(By.xpath("//div[contains(@class, 'date-year')]"));
            webElement.click();
            Thread.sleep(1000); //Sleep here so that the dropdown is fully loaded.
            webElement = webElement.findElement(By.xpath("//*[@class='ss-option' and text()='2022']"));
            webElement.click();


            webElement = myDriver.findElement(By.xpath(".//select[@title='Is this your last semester at RMIT? is required.']"));
            webElement = webElement.findElement(By.xpath("./.."));
            webElement = webElement.findElement(By.xpath(".//div[contains(@class, 'ss-main')]"));
            webElement.click();
            Thread.sleep(1000); //Sleep here so that the dropdown is fully loaded.
            webElement = webElement.findElement(By.xpath("//*[@class='ss-option' and text()='Yes']"));
            webElement.click();

            Thread.sleep(1000); //Sleep for checking the input before the automation continue to next page.
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='data-entry-next-button']")));
            webElement.click();

            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtFirstName']")));
            webElement.sendKeys("Hai");
            webElement = myDriver.findElement(By.xpath("//input[@id='txtLastName']"));
            webElement.sendKeys("Nguyen");

            webElement = myDriver.findElement(By.xpath("//select[@id='drpCountry']"));
            webElement.click();
            Thread.sleep(1000); //Sleep here so that the dropdown is fully loaded.
            webElement = webElement.findElement(By.xpath("//option[@value='VN']"));
            webElement.click();

            webElement = myDriver.findElement(By.xpath("//input[@id='txtEmailAddress']"));
            webElement.sendKeys("nhhai1605@gmail.com");
            webElement = myDriver.findElement(By.xpath("//input[@id='txtConfirmEmailAddress']"));
            webElement.sendKeys("nhhai1605@gmail.com");
            webElement = myDriver.findElement(By.xpath("//label[contains(text(), 'Remember my booking details on this computer for next time.')]"));
            JavascriptExecutor js = (JavascriptExecutor) myDriver;
            js.executeScript("arguments[0].scrollIntoView();", webElement); //I have to scroll down to the checkbox to tick it.
            wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
            webElement = myDriver.findElement(By.xpath("//button[@id='btn-purchase-sm']"));
            Thread.sleep(1000); //Sleep for checking the input before the automation continue to next page.
            webElement.click();

            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            fail("Check booking into an event failed");
        }

        try
        {
            //If the title is not "Confirmation | TryBooking Australia", it means that the booking is not successful.
            wait.until(ExpectedConditions.titleIs("Confirmation | TryBooking Australia"));
            print("------ Checking for booking into an event passed -------");

        } catch (Exception e)
        {
            fail("Check booking into an event failed");
        }

    }

    @BeforeEach
    void setUp()
    {
        SeleniumDriverFactory sdf = new SeleniumDriverFactory();
        this.myDriver = sdf.getDriver();
    }

    @AfterEach
    void tearDown()
    {
        myDriver.quit();
    }
}