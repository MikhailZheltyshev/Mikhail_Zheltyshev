package hw2.ex1;

import DataProviders.DataProviders;
import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class HomeWork2Exc1 extends TestBase {

    @Test(dataProvider = "checkTextsOnIndexDataProvider", dataProviderClass = DataProviders.class)
    public void checkTextUnderTheImagesOfIndex(int textNum, String textExpected) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //2 Assert that all texts under icons on Index page have proper text according to DataProvider data set
        List<WebElement> textsUnderIconsElements = driver.findElements(By.cssSelector(".benefit-txt"));
        assertEquals(textsUnderIconsElements.get(textNum).getText(),textExpected);

        driver.close();
    }
}
