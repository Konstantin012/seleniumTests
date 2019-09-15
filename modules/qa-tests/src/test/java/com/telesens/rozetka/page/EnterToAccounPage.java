package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class EnterToAccounPage extends BasePage {

    public EnterToAccounPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //LOCATORS
    @FindBy(id = "auth_email")
    private WebElement login;

    @FindBy(id = "auth_pass")
    private WebElement password;

    @FindBy(css = "div > button > span[class=\"button-inner\"]")
    private WebElement enterButton;

    @FindBy(css = "div[class*=\"wishlist-modal js-overlay-show\"]")
    public WebElement mainWindowEnterAccount;
    public String mainWindowLocator = "div[class*=\"wishlist-modal js-overlay-show\"]";

    @FindBy(css = ".error-message_color_red")
    private WebElement errorPaasword;


    //FIELDS
    private String filledTriger = "auth-modal__form-input ng-dirty ng-valid ng-touched";

    //ACTION
    public EnterToAccounPage enterYourData(String log, String pas) {
        inputTextField(login, log);
        inputTextField(password, pas);
        return this;
    }

    public BasePage goToAccountPoli() {
        enterButton.click();
        waitingJava(1000);
        return this;
    }

    public HomePage goToAccount() {
        enterButton.click();
        waitingJava(1000);
        return new HomePage(driver);
    }

    public EnterToAccounPage goToAccountError() {
        enterButton.click();
        waitingJava(1000);
        return this;
    }


    //CHECKING

    public EnterToAccounPage checkLoginPasswordFilled() {
        getAtributeAdnCheck(login, "class", filledTriger);
        getAtributeAdnCheck(password, "class", filledTriger);
        return this;
    }

    public EnterToAccounPage checkErrorPassMessage(String expecterMessage) {
        String[] actualText = errorPaasword.getText().split("!");
        Assert.assertEquals(actualText[0], expecterMessage);
        return this;
    }

}
