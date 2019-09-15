package com.telesens.rozetka.page;

import com.telesens.framework.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    Actions actions = new Actions(driver);
    @FindBy(css = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a")
    private WebElement commonMenu;

    @FindBy(partialLinkText = "Мониторы")
    private WebElement monitors;

    @FindBy(partialLinkText = "Одежда, обувь и украшения")
    private WebElement clothersBootsJew;

    @FindBy(partialLinkText = "Блузки и рубашки")
    private WebElement tShirts;

    @FindBy(xpath = "/html/body/div/div/div[1]/div[2]/button/span")
    private WebElement supportWindowClose;

    @FindBy(xpath = "//*[@id=\"webWidget\"]")
    public WebElement supportHeadNameWindow;
    public String supportHeadNameWindowLocator = "//*[@id=\"webWidget\"]";

    @FindBy(css = "a[class=\"header-topline__user-link link-dashed\"]")
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

    public TShirtPage selectTShirts(){
        closeSupportWindow();
        actions.moveToElement(clothersBootsJew).perform();
        tShirts.click();
        return new TShirtPage(driver);
    }

    public void closeSupportWindow(){
        if(tryFindElement(By.xpath(supportHeadNameWindowLocator))){
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(supportHeadNameWindow));
            driver.switchTo().frame(supportHeadNameWindow);
            supportWindowClose.click();
            driver.switchTo().defaultContent();
        }
    }


    public MonitorPage selectMonitors(){
        closeSupportWindow();
        actions.moveToElement(commonMenu).perform();
        monitors.click();
        return new MonitorPage(driver);
    }

    public EnterToAccounPage clickOnAccount() {
        ownAccount.click();
        waitingExpectedElement(new EnterToAccounPage(driver).mainWindowEnterAccount, 10);
        return new EnterToAccounPage(driver);
    }

    public AccountDropListPage openAccountDropList(){
        actions.moveToElement(ownAccount).perform();
        return new AccountDropListPage(driver);
    }



    //CHECKING
    public HomePage checAcountName(String name) {
        checkTextFromLocator(ownAccount, name);
        return this;
    }





}
