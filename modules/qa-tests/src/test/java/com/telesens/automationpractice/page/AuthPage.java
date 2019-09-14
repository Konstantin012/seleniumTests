package com.telesens.automationpractice.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailFiled;

    @FindBy(id = "passwd")
    private WebElement paswordFiled;

    @FindBy(id = "SubmitLogin")
    private WebElement submit;

    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/ol/li")
    private WebElement errorMes;


    public AuthPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AuthPage enterEmail(String loginn){
//        emailFiled.click();
//        emailFiled.clear();
//        emailFiled.sendKeys(loginn);
        inputTextField(emailFiled, loginn);
        return this;
    }

    public AuthPage enterPasswor(String pasword){
//        paswordFiled.click();
//        paswordFiled.clear();
//        paswordFiled.sendKeys(pasword);
        inputTextField(paswordFiled, pasword);
        return this;
    }

    public AuthPage pressSubmit(){
        submit.click();
        return this;
    }

    public String getErrorMessege(){
        return errorMes.getText();
    }
}
