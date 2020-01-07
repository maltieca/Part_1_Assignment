package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver newdriver)
    {
        driver=newdriver;
        PageFactory.initElements(newdriver, this);
    }


}
