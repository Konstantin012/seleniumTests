package com.telesens.framework.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected WebDriver driver;
    protected String chromeDriverP;
    protected String firefoxDriverP;
    protected Properties propSel;
        private static final String SELENIUM_PATH_ = "src/main/resources/selenium.properties";

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    protected void setUp(@Optional("chrome") String browser) throws Exception {
        String seleniumPaths = System.getProperty("browProp");
        if(seleniumPaths==null)
            seleniumPaths=SELENIUM_PATH_;

        propSel = new Properties();
        propSel.load(new FileReader(seleniumPaths));
        chromeDriverP = propSel.getProperty("driver.chrome");
        firefoxDriverP = propSel.getProperty("driver.firefox");

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverP);
            driver = new ChromeDriver();
        }
        else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxDriverP);
            driver = new FirefoxDriver();
        }


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    protected void tearDown() throws Exception {
        driver.quit();
    }
}
