import extensions.RemoteWebDriverExtension;
import extensions.RemoteWebDriverParameterResolver;
import lombok.SneakyThrows;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

@ExtendWith(RemoteWebDriverExtension.class)
public class MultiBrowserDemoTest {

    RemoteWebDriver driver;


    @SneakyThrows
    @TestTemplate
    @ExtendWith(RemoteWebDriverParameterResolver.class)
    public void testGrid(RemoteWebDriver driver) {
        this.driver = driver;
        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("JUnit5 extensions");
        search.submit();
    }

}