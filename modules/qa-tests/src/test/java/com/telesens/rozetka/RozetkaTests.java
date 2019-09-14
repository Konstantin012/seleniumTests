package com.telesens.rozetka;

import com.telesens.framework.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

import static com.telesens.rozetka.page.HomePage.startFromHome;

public class RozetkaTests extends BaseTest {

    private String baseUrl = "https://rozetka.com.ua/";
    private Properties prop;


//    @BeforeClass(alwaysRun = true)
//    public void setUp() throws Exception {
//
//    }


    @Test(enabled = false)
    public void testSort() {
        List<Integer> pricesSortedActual =
                startFromHome(driver, baseUrl)
                        .selectMonitors()
                        .sortFromCheapToExpens()
                        .getAllPricesToIneger();

        List<Integer> pricesSortedExpected = new ArrayList<>(pricesSortedActual);
        Collections.sort(pricesSortedExpected);

        Assert.assertEquals(pricesSortedActual, pricesSortedExpected);
    }


    @Test(enabled = false)
    public void testSort2() {
        List<Integer> allSortedPrices = startFromHome(driver, baseUrl)
                .selectMonitors()
                .enterPrice()
//                .enterMinPrice("30000")
//                .enterMaxPrice("40000")
                .pressOkAfrerEnterPrices()
                .getAllPricesToIneger();

        Collections.sort(allSortedPrices);

        Assert.assertTrue(allSortedPrices.get(0) > 30000
                & allSortedPrices.get(allSortedPrices.size()-1) < 40000);
    }

    @Test(enabled = false)
    public void authenticationSuccess(){
        startFromHome(driver, baseUrl);
    }


//Метод написан напрямую через селениум, без Pageobject
//    @Test
//    @Ignore
//    public void testSort() {
//        driver.get(baseUrl);
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(By.cssSelector(mainMenuCSS)))
//                .perform();
//
//        driver.findElement(By.partialLinkText("Мониторы")).click();
//        List<String> prices =
//                driver.findElements(By.cssSelector(priceCSS)).stream()
//                        .map(WebElement::getText)
//                        .map(s -> s.replaceAll("[^\\d]", ""))
//                        .collect(Collectors.toList());
//        System.out.println(prices);
//
//        driver.findElement(By.cssSelector("#sort_view > a")).click();
//        driver.findElement(By.xpath("//*[@id='sort_view']/div/ul/li/a[contains(text(), 'от дешевых')]")).click();
//
//        //div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(
//                        By.xpath("//div[contains(@class, 'sort-popup') and contains(@style, 'visibility: hidden')]")
//                ));
//        List<Integer> pricesSortedActual =
//                driver.findElements(By.cssSelector(priceCSS)).stream()
//                        .map(WebElement::getText)
//                        .map(s -> s.replaceAll("[^\\d]", ""))
//                        .map(Integer::parseInt)
//                        .collect(Collectors.toList());
//
//        List<Integer> pricesSortedExpected = new ArrayList<>(pricesSortedActual);
////        pricesSortedExpected.sort(Integer::compareTo); //через компаратор
////        pricesSortedExpected.sort((s1,s2)-> {return Integer.compare(s1,s2);}); //через компаратор
//        Collections.sort(pricesSortedExpected);
//
//
//        System.out.println("pricesSortedActual: " + pricesSortedActual);
//        System.out.println("pricesSortedExpected: " + pricesSortedExpected);
//
//        Assert.assertEquals(pricesSortedActual, pricesSortedExpected);
//    }
}
