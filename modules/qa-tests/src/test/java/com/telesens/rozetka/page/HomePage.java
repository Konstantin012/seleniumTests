package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    Actions actions = new Actions(driver);

    @FindBy(css = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a")
    private WebElement commonMenu;

    @FindBy(partialLinkText = "Мониторы")
    private WebElement monitors;

    @FindBy()
    private WebElement ownAccount;


    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public static HomePage startFromHome(WebDriver driver, String baseUrl){
        driver.get(baseUrl);
        return new HomePage(driver);
    }

    public MonitorPage selectMonitors(){

        actions.moveToElement(commonMenu).perform();
        monitors.click();
        return new MonitorPage(driver);
    }



}
