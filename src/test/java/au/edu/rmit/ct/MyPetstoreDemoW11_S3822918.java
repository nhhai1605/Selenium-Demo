/**
 *
 * Name: John Smith (( Update with your name here ))
 * Student ID: s45045012  (( Update with your ID))
 *
 * [OPTIONAL: add any notes or comments here about the code]
 */
package au.edu.rmit.ct;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Update this class name by replacing S3214321 with your student ID
class MyPetstoreDemoW11_S3822918
{
    WebDriver myDriver;
    void print(String str)
    {
        System.out.println(str);
    }
    @Test
    @Order(1)
    @DisplayName("Enter PetStore website")
    void enterPetStoreWebsite()
    {
        String petStoreURL = "https://petstore.octoperf.com/";
        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(3000));
        myDriver.get(petStoreURL);
        wait.until(ExpectedConditions.urlToBe(petStoreURL));
        assertEquals("JPetStore Demo", myDriver.getTitle());
        print("------ Checking for entering the pet store website passed -------");

    }

    //Assume that the stock here can be updated ==>
    //So, we still can place an order with "Back ordered." and "<number> in stock." but only if <number> is larger than 0.
    //If <number> is 0, we will throw an exception.
    void checkStock() throws Exception
    {
        String backOrdered = "Back ordered.";
        String inStock = "in stock.";
        String wrongStockFormat = "0 in stock.";
        WebElement webElement = myDriver.findElement(By.xpath(String.format("//td[contains(text(), '%s')]|//td[contains(text(), '%s')]", inStock, backOrdered)));
        if(webElement.getText().equals(wrongStockFormat))
        {
            throw new Exception("Test Failed: The stock format cannot be '0 in stock.'");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Check the pet name, price and check if there is stock for one pet of your choice.")
    void checkAnotherPet() throws Exception {
        String manxURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-15";
        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(3000));
        myDriver.get(manxURL);
        wait.until(ExpectedConditions.urlToBe(manxURL));

        String itemId = "EST-15";
        String petName = "With tail Manx";
        String petBreed = "Manx";
        String petPrice = "$23.50";

        WebElement webElement;

        webElement = myDriver.findElement(By.xpath(String.format("//b[contains(text(), '%s')]", itemId)));
        assertEquals(itemId, webElement.getText());

        webElement = myDriver.findElement(By.xpath(String.format("//font[contains(text(), '%s')]", petBreed)));
        assertEquals(petName, webElement.getText());

        webElement = myDriver.findElement(By.xpath(String.format("//td[contains(text(), '%s')]", petBreed)));
        assertEquals(petBreed, webElement.getText());

        //Please read the comment above the checkStock() function for more details about this function.
        checkStock();

        webElement = myDriver.findElement(By.xpath(String.format("//td[contains(text(), '%s')]", petPrice)));
        assertEquals(petPrice, webElement.getText());


        print("------ Checking for another pet passed -------");

    }

    @Test
    @Order(3)
    @DisplayName("Start a menagerie! Select three fish, two cats and one third type of pet that's in stock. How much will it cost?")
    void startAMenagerie() throws Exception
    {

        String backOrdered = "Back ordered.";
        String inStock = "in stock.";
        String wrongStockFormat = "0 in stock.";
        String fishURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-21";
        String catURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-16";
        String dogURL = "https://petstore.octoperf.com/actions/Catalog.action?viewItem=&itemId=EST-8";
        String fishActionURL = "https://petstore.octoperf.com/actions/Cart.action?addItemToCart=&workingItemId=EST-21";
        String catActionURL = "https://petstore.octoperf.com/actions/Cart.action?addItemToCart=&workingItemId=EST-16";
        String dogActionURL = "https://petstore.octoperf.com/actions/Cart.action?addItemToCart=&workingItemId=EST-8";

        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(3000));
        WebElement webElement;
        float actualCost = 0f;

        myDriver.get(fishURL);
        wait.until(ExpectedConditions.urlToBe(fishURL));
        checkStock();
        webElement = myDriver.findElement(By.xpath("//td[contains(text(), '$5.29')]"));
        actualCost += Float.parseFloat(webElement.getText().substring(1)) * 3;

        myDriver.get(catURL);
        wait.until(ExpectedConditions.urlToBe(catURL));
        checkStock();
        webElement = myDriver.findElement(By.xpath("//td[contains(text(), '$93.50')]"));
        actualCost += Float.parseFloat(webElement.getText().substring(1)) * 2;

        myDriver.get(dogURL);
        wait.until(ExpectedConditions.urlToBe(dogURL));
        checkStock();
        webElement = myDriver.findElement(By.xpath("//td[contains(text(), '$18.50')]"));
        actualCost += Float.parseFloat(webElement.getText().substring(1)) * 1;

        for (int i = 0; i < 3; i++)
        {
            myDriver.get(fishActionURL);
            wait.until(ExpectedConditions.urlToBe(fishActionURL));
        }
        for (int i = 0; i < 2; i++)
        {
            myDriver.get(catActionURL);
            wait.until(ExpectedConditions.urlToBe(catActionURL));
        }
        for(int i = 0; i < 1; i++)
        {
            myDriver.get(dogActionURL);
            wait.until(ExpectedConditions.urlToBe(dogActionURL));
        }
        webElement = myDriver.findElement(By.xpath(String.format("//*[contains(text(), 'Sub Total')]")));
        print(String.format("Actual cost: %.2f", actualCost));
        print("Expected cost: " + webElement.getText().substring(12));
        assertEquals(String.format("Sub Total: $%.2f", actualCost), webElement.getText(), "The subtotal cost is not correct");
        print("------ Checking for starting a menagerie passed -------");
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