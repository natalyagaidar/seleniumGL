import org.apache.bcel.util.ClassLoader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nataliia.gaidar on 4/20/2017.
 */
public class Task6 {

    private WebDriver driver1;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");

        driver1 = new ChromeDriver();

        driver1.get("http://localhost/litecart/admin/");
        driver1.findElement(By.name("username")).sendKeys("admin");
        driver1.findElement(By.name("password")).sendKeys("admin");
        driver1.findElement(By.name("remember_me")).isEnabled();
        driver1.findElement(By.name("login")).click();
        driver1.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    @Test
    public void VerifyAddingNewItem() {
    String productName = "Duck666";
        By menuId = By.id("box-apps-menu");
        By itemId = By.id("app-");
        By tabsContainer = By.className("tabs");

        List<WebElement> menuItems =  driver1.findElement(menuId).findElements(itemId);
        menuItems.get(1).click();
        driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver1.findElement(By.id("content")).findElements(By.cssSelector("div a[class=button")).get(1).click();
        driver1.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        List<WebElement> radioStatus = driver1.findElements(By.cssSelector("input[type=radio]"));
        List<WebElement> textFields = driver1.findElements(By.cssSelector("input[type=text]"));

        radioStatus.get(0).click();
        textFields.get(0).sendKeys(productName);
        textFields.get(1).sendKeys("987654");

        java.lang.ClassLoader classLoader = getClass().getClassLoader();
        File imageToUpload = new File(classLoader.getResource("pug.jpg").getFile());
        driver1.findElement(By.cssSelector("input[type=file]")).sendKeys(imageToUpload.getAbsolutePath());
        driver1.findElement(By.cssSelector("button[type=submit]")).click();
        List<WebElement> products = driver1.findElement(By.className("dataTable")).findElements(By.className("row"));
        int index = products.size();
        String expected = products.get(products.size()-2).getText();
        Assert.assertEquals(productName, expected);


    }


    @After
    public void stop() {
       //driver1.quit();
       //driver1 = null;

    }
}
