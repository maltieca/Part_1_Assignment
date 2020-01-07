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

    @When("^I log in using invalid credentials$")
    public void i_log_in_using_invalid_credentials() {
        driver.findElement(By.id("email")).sendKeys("wrongemail@gmail.com");
        driver.findElement(By.id("password")).sendKeys("wrongpassword");
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", loginBtn);


    }

    @Then("^I should not be logged in$")
    public void i_should_not_be_logged_in() {
        String title = driver.getTitle();
        Assert.assertEquals("Klikk Computer Store Malta - Login", title);
    }


    ///////////////////////////////////////////////////////////////////

    @Given("^I am a logged in user on the website$")
    public void i_am_a_logged_in_user_on_the_website() {
        i_am_a_user_on_the_website();
        i_log_in_using_valid_credentials();
        i_should_be_logged_in();

    }

    @When("^I search for a product$")
    public void i_search_for_a_product() {
        driver.findElement(By.xpath("//input[@placeholder='Start a new search...']")).sendKeys("Apple Watch");
        driver.findElement(By.xpath("//input[@placeholder='Start a new search...']")).submit();
        sleep(3);
    }

    @And("^I select the first product in the list$")
    public void i_select_the_first_product_in_the_list(){
        driver.findElement(By.xpath("//div[contains(@class, 'product-title')]/p")).click();
        sleep(3);
    }

    @Then("^I should see the product details$")
    public void i_should_see_the_product_details() {
        String title = driver.getTitle();
        Assert.assertEquals("Klikk Computer Store Malta - Apple Watch Nike+ Series 4 40mm GPS Smartwatch Silver", title);
    }

    /////////////////////////////////////////////////////////////

    @Given("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {
        driver.findElement(By.xpath("//span[contains(@class, 'glyphicon glyphicon-shopping-cart')]")).click();
        WebElement statsElement = driver.findElement(By.xpath("//html/body/div[2]/div/main/section/div/div/div[2]"));
        String statsText = statsElement.getText();

        Assert.assertTrue(statsText, Boolean.parseBoolean("Cart empty!"));
    }

    @When("I view the details of a product")
    public void i_view_the_details_of_a_product() {
        i_search_for_a_product();
        i_select_the_first_product_in_the_list();
    }

    @When("I choose to buy the product")
    public void i_choose_to_buy_the_product() {
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-cart btn-lg']")).click();
    }

    @Then("my shopping cart should contain {int} item")
    public void my_shopping_cart_should_contain_item(Integer int1) {
        driver.findElement(By.xpath("//span[contains(@class, 'glyphicon glyphicon-shopping-cart')]")).click();
        sleep(3);
        driver.navigate().refresh();
    }


}
