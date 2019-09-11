package com.telesens.rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RozetkaTests {

    private  String baseUrl;
    private WebDriver driver;
    private Properties prop;
    private String mainMenuCSS = "body > app-root > div > div:nth-child(2) > app-rz-main-page > div > aside > main-page-sidebar > sidebar-fat-menu > div > ul > li:nth-child(1) > a";
    private String priceCSS = "div[name='goods_list_container']  div.g-price > div.g-price-uah";

    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws Exception {
        baseUrl = "https://rozetka.com.ua/";
        System.setProperty("webdriver.chrome.driver", "d:/distribs/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }



    @Test
    @Ignore
    public void testSort() {
        driver.get(baseUrl);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(mainMenuCSS)))
                .perform();

        driver.findElement(By.partialLinkText("Мониторы")).click();
        List<String> prices =
                driver.findElements(By.cssSelector(priceCSS)).stream()
                        .map(WebElement::getText)
                        .map(s->s.replaceAll("[^\\d]", ""))
                        .collect(Collectors.toList());
        System.out.println(prices);

        driver.findElement(By.cssSelector("#sort_view > a")).click();
        driver.findElement(By.xpath("//*[@id='sort_view']/div/ul/li/a[contains(text(), 'от дешевых')]")).click();

        //div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]")
                ));
        List<Integer> pricesSortedActual =
                driver.findElements(By.cssSelector(priceCSS)).stream()
                        .map(WebElement::getText)
                        .map(s->s.replaceAll("[^\\d]", ""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        List<Integer> pricesSortedExpected = new ArrayList<>(pricesSortedActual);
//        pricesSortedExpected.sort(Integer::compareTo); //через компаратор
//        pricesSortedExpected.sort((s1,s2)-> {return Integer.compare(s1,s2);}); //через компаратор
        Collections.sort(pricesSortedExpected);


        System.out.println("pricesSortedActual: " + pricesSortedActual);
        System.out.println("pricesSortedExpected: " + pricesSortedExpected);

        Assert.assertEquals(pricesSortedActual, pricesSortedExpected);
    }

    @Test
    public void testSort2()
    {
        driver.get(baseUrl);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(mainMenuCSS)))
                .perform();
        driver.findElement(By.partialLinkText("Мониторы")).click();

        driver.findElement(By.cssSelector("#price\\[min\\]")).click();
        driver.findElement(By.cssSelector("#price\\[min\\]")).clear();
        driver.findElement(By.cssSelector("#price\\[min\\]"))
                .sendKeys("30000");

        driver.findElement(By.cssSelector("#price\\[max\\]")).click();
//        driver.findElement(By.cssSelector("#price\\[max\\]")).clear();  //НЕ РАБОТАЕТ
        driver.findElement(By.cssSelector("#price\\[max\\]"))
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), "40000");


        driver.findElement(By.cssSelector("div#sort_price button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//form[contains(@class, 'filter-parametrs pos-fix')" +
                                "and contains(@id, 'filter_parameters_form')]")
                ));
        Integer[] integersArr = driver.findElements(By.cssSelector(priceCSS)).stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll("[^\\d]", ""))
                .map(Integer::parseInt).toArray(Integer[]::new);
        Arrays.sort(integersArr);
        Assert.assertTrue(integersArr[0]>30000
                & integersArr[integersArr.length-1]<40000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
