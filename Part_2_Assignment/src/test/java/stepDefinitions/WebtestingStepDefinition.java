package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;


public class WebtestingStepDefinition {

    WebDriver driver;

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
        }
    }


    @Given("^I am a user on the website$")
    public void i_am_a_user_on_the_website() {

        System.setProperty("webdriver.chrome.driver", "/Bachelor/Year 3/Sem 1/Software Testing/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.klikk.com.mt/login");
    }

    @When("^I log in using valid credentials$")
    public void i_log_in_using_valid_credentials() {
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("maltieca@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("259!bgBG259!");
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        sleep(3);
    }

    @Then("^I should be logged in$")
    public void i_should_be_logged_in() {
        String title = driver.getTitle();
        Assert.assertEquals("Klikk Computer Store Malta - Home", title);
        sleep(3);
    }

}
