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
 * Created by nataliia.gaidar on 4/3/2017.
 */
public class LiteCartLeftMenu {
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
        driver1.manage().window().maximize();
    }
    @Test
    public void MenuItemsClick() {

        List<WebElement> menuItems = driver1.findElement(By.id("box-apps-menu")).findElements(By.id("app-"));

        for(int i =0; i< menuItems.size(); i++){
            driver1.findElement(By.id("box-apps-menu")).findElements(By.id("app-")).get(i).click();
            if(isElementPresent(driver1, By.className("docs"))) {
                List<WebElement> sumMenuItems = driver1.findElement(By.id("box-apps-menu")).findElement(By.className("selected")).findElement(By.className("docs")).findElements(By.tagName("li"));
                if (sumMenuItems.size() > 1) {
                    for (int j = 1; j < sumMenuItems.size(); j++) {

                        driver1.findElement(By.id("box-apps-menu")).findElement(By.className("selected")).findElement(By.className("docs")).findElements(By.tagName("li")).get(j).click();
                        if(driver1.findElement(By.id("box-apps-menu")).findElement(By.className("selected")).findElement(By.className("docs")).findElements(By.tagName("li")).get(j).getText() != "Modules")
                        Assert.assertEquals(driver1.findElement(By.id("box-apps-menu")).findElement(By.className("selected")).findElement(By.className("docs")).findElements(By.tagName("li")).get(j).getText(), driver1.findElement(By.id("content")).findElement(By.tagName("h1")).getText());
                    }
                }
            }
            else Assert.assertEquals(driver1.findElement(By.id("box-apps-menu")).findElements(By.id("app-")).
                    get(i).getText(), driver1.findElement(By.id("content")).findElement(By.tagName("h1")).getText());
        }

    }

    public boolean isElementPresent(WebDriver driver, By by) {
        boolean isPresent = true;
        //search for elements and check if list is empty
        if (driver.findElements(by).isEmpty()) {
            isPresent = false;
        }
        //rise back implicitly wait time
        return isPresent;
    }
    @After
    public void stop() {
        driver1.quit();
        driver1 = null;

    }

}
