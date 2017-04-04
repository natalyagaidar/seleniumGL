import com.google.common.collect.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.TableView;
import java.util.concurrent.TimeUnit;

/**
 * Created by nataliia.gaidar on 4/3/2017.
 */
public class LiteCartLogin {
    private WebDriver driver1;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");

        driver1 = new ChromeDriver();

        driver1.get("http://localhost/litecart/admin/");
    }
    @Test
    public void LoginTest() {
        driver1.get("http://localhost/litecart/admin/");
        driver1.findElement(By.name("username")).sendKeys("admin");
        driver1.findElement(By.name("password")).sendKeys("admin");
        driver1.findElement(By.name("remember_me")).isEnabled();
        driver1.findElement(By.name("login")).click();
        driver1.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
       // wait.until(ExpectedConditions.numberOfElementsToBe(By.tagName("table"), 4));
    }

    @After
    public void stop() {
        driver1.quit();
        driver1 = null;

    }
}
