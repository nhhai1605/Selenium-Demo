package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HelloSeleniumFacebook
{
    private static WebDriver myDriver;
    @BeforeAll
    static void setUpDriver() throws Exception
    {
        SeleniumDriverFactory factory = new SeleniumDriverFactory();
        myDriver = factory.getDriver();
    }

    @Test
    @Order(1)
    @DisplayName("Use Firefox Web Driver to check retrieval of Facebook Homepage")
    void checkFacebookTitle() {

        // Step 1. Set system property for either chrome driver (for Chrome browser ) or gecko driver (for Firefox browser)
//        System.setProperty("webdriver.gecko.driver","H:\\drivers\\geckodriver.exe");

        // Step 2. Instantiate the Web Driver as your driver
//        WebDriver myDriver = new FirefoxDriver();

        // Step 3. name the url you want to test.
        String baseUrl = "http://www.facebook.com";

        // Step 4. Direct your driver
        myDriver.get(baseUrl);
        System.out.println(myDriver.getTitle());
        // Step 5. use Junit assertions to make sure you have the right page
        assertEquals("Facebook – log in or sign up", myDriver.getTitle());

        // Step 6. Finally close your session.
        myDriver.close();
    }

    @Test
    @Order(2)
    @DisplayName("Use Chrome Web Driver to check email input field of Facebook page")
    void checkFacebookEmailInput(){

        // Of course G:\\drivers\\geckodriver.exe should be replaced with
        // the url of where you stored this.
        // Step 1. Set system property for either chrome driver or gecko driver (for Firefox browser)
//        System.setProperty("webdriver.chrome.driver","H:\\drivers\\chromedriver.exe");

        // Step 2. Instantiate the Web Driver as your driver

//        WebDriver myDriver = new ChromeDriver();

        // Step 3. name the url you want to test.
        String baseUrl = "http://www.facebook.com";

        // Step 4. Direct your driver
        myDriver.get(baseUrl);
        String tagName = myDriver.findElement(By.id("email")).getTagName();

        // Step 5. use Junit assertions to make sure you have the right page
        assertEquals("input", tagName);


        // Step 6. Finally close your session. But we want to pause for 3 seconds first. There are others that selenium provides to add pauses, but this is one of the most straightforward

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myDriver.close();
    }
    @Order(3)
    @Test
    @DisplayName("Trying to click on the login button")
    void checkFacebookLoginButton(){

        // Of course G:\\drivers\\geckodriver.exe should be replaced with
        // the url of where you stored this.
        // Step 1. Set system property for either chrome driver or gecko driver (for Firefox browser)
//        System.setProperty("webdriver.chrome.driver","H:\\drivers\\chromedriver.exe");

        // Step 2. Instantiate the Web Driver as your driver

//        WebDriver myDriver = new ChromeDriver();

        // Step 3. name the url you want to test.
        String baseUrl = "http://www.facebook.com";

        // Step 4. Direct your driver
        myDriver.get(baseUrl);
        WebElement aButton = myDriver.findElement(By.tagName("button"));

        // retrieving html attribute value using getAttribute() method
        String typeValue = aButton.getAttribute("name");
        System.out.println("Value of name attribute: "+typeValue);

        String autocompleteValue = aButton.getAttribute("class");
        System.out.println("Value of class attribute: "+autocompleteValue);

        String nonExistingAttributeValue = aButton.getAttribute("type");
        System.out.println("Value of type attribute: "+nonExistingAttributeValue);

        // fail("Check if aButton is the right Button before we click it");

        // This is how you click the button. But should we do more checks that it's the right button before we do this?
        aButton.click();

        // Check the title of the results page. Is this what you would expect? If it isn't, how would you fix this?
        System.out.println(myDriver.getTitle());

        fail("Use asserts to check that you get the expected webpage via title check");



        // Finally close your session.
        myDriver.close();
    }

}
