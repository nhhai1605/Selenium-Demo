/**
 * This class will allow you to separate the webdriver / browser choice from the test classes
 */

package au.edu.rmit.ct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SeleniumDriverFactory
{

    SeleniumDriverFactory()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }
    WebDriver getDriver()
    {
        return new ChromeDriver();
    }


}
