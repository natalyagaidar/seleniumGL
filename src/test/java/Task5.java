import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nataliia.gaidar on 4/19/2017.
 */
public class Task5 {
    private WebDriver driver1;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");

        driver1 = new ChromeDriver();

        driver1.get("http://localhost/litecart/admin/");
        driver1.get("http://localhost/litecart/admin/");
        driver1.findElement(By.name("username")).sendKeys("admin");
        driver1.findElement(By.name("password")).sendKeys("admin");
        driver1.findElement(By.name("remember_me")).isEnabled();
        driver1.findElement(By.name("login")).click();
        driver1.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    @Test
    public void VerifyCampaignsItem() {
        driver1.get("http://localhost/litecart/");
        WebElement CampaignsSection = driver1.findElement(By.id("box-campaigns"));
        List<WebElement> CampaignItems = CampaignsSection.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        String itemName = CampaignItems.get(0).findElement(By.className("name")).getText();

        //item's name,  prices and text styles from the Litecart page
        String regularPrice = CampaignItems.get(0).findElement(By.className("price-wrapper")).findElement(By.className("regular-price")).getText();
        String campaignPrice = CampaignItems.get(0).findElement(By.className("price-wrapper")).findElement(By.className("campaign-price")).getText();
        String regularPriceFontStyle = CampaignItems.get(0).findElement(By.className("price-wrapper")).findElement(By.className("regular-price")).getCssValue("text-decoration");
        String campaignPriceFontStyle = CampaignItems.get(0).findElement(By.className("price-wrapper")).findElement(By.className("campaign-price")).getCssValue("font-weight");
        String campaignPriceFontColor = CampaignItems.get(0).findElement(By.className("price-wrapper")).findElement(By.className("campaign-price")).getCssValue("color");
        CampaignItems.get(0).click();
        WebElement itemSection = driver1.findElement(By.id("box-product"));
        String itemNameExpected = itemSection.findElement(By.className("title")).getText();

        //item's name,  prices and text styles from the product page
        String regularPriceExpected = itemSection.findElement(By.className("price-wrapper")).findElement(By.className("regular-price")).getText();
        String campaignPriceExpected = itemSection.findElement(By.className("price-wrapper")).findElement(By.className("campaign-price")).getText();
        String regularPriceFontStyleExpected =  itemSection.findElement(By.className("price-wrapper")).findElement(By.className("regular-price")).getCssValue("text-decoration");
        String campaignPriceFontStyleExpected = itemSection.findElement(By.className("price-wrapper")).findElement(By.className("campaign-price")).getCssValue("font-weight");
        String campaignPriceFontColorExpected = itemSection.findElement(By.className("price-wrapper")).findElement(By.className("campaign-price")).getCssValue("color");

        //Verification
                Assert.assertEquals(itemName, itemNameExpected);
        Assert.assertEquals(regularPrice, regularPriceExpected);
        Assert.assertEquals(campaignPrice, campaignPriceExpected);
        Assert.assertEquals(regularPriceFontStyle, regularPriceFontStyleExpected);
        Assert.assertEquals(campaignPriceFontStyle, campaignPriceFontStyleExpected);
        Assert.assertEquals(campaignPriceFontColor, campaignPriceFontColorExpected);

    }



    @After
    public void stop() {
        driver1.quit();
        driver1 = null;

    }
}
