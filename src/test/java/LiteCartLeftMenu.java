import org.junit.After;
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

        for (WebElement element: menuItems) {
            element.click();
            if (element.findElement(By.className("docs")).findElements(By.tagName("li")).size() != 0) {
                List<WebElement> menuSubItems = element.findElement(By.className("docs")).findElements(By.tagName("li"));

                for (WebElement subElement : menuItems) {
                    subElement.click();
                }
            }
        }

    }

    @After
    public void stop() {
        driver1.quit();
        driver1 = null;

    }

}
