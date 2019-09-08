package com.telesens.automationpractice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class AuthTests {
    private static final String DEFAULT_PATH = "src/main/resources/automationpractice.properties";
    private static final String SELENIUM_PATH_ = "src/main/resources/selenium.properties";
    private WebDriver driver;
    private String baseUrl;
    private String chromeDriverP;
    private String firefoxDriverP;
    private String login;
    private String password;
    private Properties prop;
    private Properties propSel;
    private List<String> txtDatas;




    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("firefox") String browser) throws Exception {
        String automationPracticePath = System.getProperty("cfgAP");
        String seleniumPaths = System.getProperty("browProp");
        propSel = new Properties();
        propSel.load(new FileReader(seleniumPaths));
        chromeDriverP = propSel.getProperty("driver.chrome");
        firefoxDriverP = propSel.getProperty("driver.firefox");
        prop = new Properties();
        prop.load(new FileReader(automationPracticePath));
        baseUrl = prop.getProperty("base.url");
        login = prop.getProperty("login");
        password = prop.getProperty("password");


        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", chromeDriverP);
            driver = new ChromeDriver();
        }
        else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", firefoxDriverP);
            driver = new FirefoxDriver();
        }
        if (automationPracticePath==null)
            automationPracticePath = DEFAULT_PATH;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

//    @Test()
////    @Ignore
//    public void testAuthSuccess() throws Exception {
//        driver.get(baseUrl);
//        driver.findElement(By.linkText("Sign in")).click();
//        driver.findElement(By.id("email")).click();
//        driver.findElement(By.id("email")).clear();
//        driver.findElement(By.id("email")).sendKeys(login);
//        driver.findElement(By.id("passwd")).click();
//        driver.findElement(By.id("passwd")).clear();
//        driver.findElement(By.id("passwd")).sendKeys(password);
//        driver.findElement(By.id("SubmitLogin")).click();
////        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot your password?'])[1]/following::span[1]")).click();
////        driver.findElement(By.linkText("Sign out")).click();
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
//        String text = element.getText();
//        Assert.assertEquals(text,"Konstantin Kondratiev");
//    }

    @Test(dataProvider = "testAuthSuccessProvider")
//    @Ignore
    public void testAuthSuccess(String loginn, String pasword) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(loginn);
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(pasword);
        driver.findElement(By.id("SubmitLogin")).click();
//        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forgot your password?'])[1]/following::span[1]")).click();
//        driver.findElement(By.linkText("Sign out")).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"));
        String text = element.getText();
        Assert.assertEquals(text,"Konstantin Kondratiev");
    }

    @Test
    @Ignore
    public void testAuthErrorMessage(){
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys("125tes");
        driver.findElement(By.id("SubmitLogin")).click();
        WebElement ele = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        String text = ele.getText();
        Assert.assertEquals(text,"Authentication failed.");
    }

    @Test
    @Ignore
    public void testNumber8()
    {
        readFromTxt();
        driver.get(baseUrl);
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).clear();
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.id("SubmitLogin")).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[3]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[2]/a")).click();
        enterToEditFields("firstname", txtDatas.get(0));
        enterToEditFields("lastname", txtDatas.get(1));
        enterToEditFields("address1", txtDatas.get(2));
        enterToEditFields("city", txtDatas.get(3));
        enterToEditFields("postcode", txtDatas.get(4));
        enterToEditFields("phone", txtDatas.get(5));
        enterToEditFields("alias", txtDatas.get(6));
        driver.findElement(By.id("submitAddress")).click();




    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    public void readFromTxt()
    {
        String txtProperty = prop.getProperty("txt.file");
        txtDatas = new ArrayList<>();
        try {
//            Scanner scanner = new Scanner(new FileReader(txtProperty));
            Scanner scanner = new Scanner(new FileReader("..\\data.txt"));    //относительно
//            FileReader fr = new FileReader("data.txt");     //не находит по относительному пути!
            while (scanner.hasNext()){
                String s = scanner.nextLine();
                txtDatas.add(s);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterToEditFields(String idName, String value){
        driver.findElement(By.id(idName)).click();
        driver.findElement(By.id(idName)).clear();
        driver.findElement(By.id(idName)).sendKeys(value);
    }

    @DataProvider(name="testAuthSuccessProvider")
    public Object[][] testAuthSuccessProvider(){
        return new Object[][]{
                {"kkon870@gmail.com","125test"}
        };

    }
}
