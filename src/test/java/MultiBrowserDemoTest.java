import annotations.TestContainerTemplate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

@TestContainerTemplate
public class MultiBrowserDemoTest {

    @SneakyThrows
    @DisplayName("search in google")
    @TestTemplate
    public void testGrid(RemoteWebDriver driver) {
        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("JUnit5 extensions");
        search.submit();
    }

}