import annotations.MultiBrowserTest;
import core.DriverType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MultiBrowserDemoTest {


    @SneakyThrows
    @DisplayName("search in google #1")
    @MultiBrowserTest
    public void testGrid1(RemoteWebDriver driver) {
        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("JUnit5 extensions");
        search.submit();
    }

    @SneakyThrows
    @DisplayName("search in google #2")
    @MultiBrowserTest(drivers = DriverType.FIREFOX)
    public void testGrid2(RemoteWebDriver driver) {
        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("JUnit5 extensions");
        search.submit();
    }

    @SneakyThrows
    @DisplayName("search in google #3")
    @MultiBrowserTest(drivers = {DriverType.FIREFOX, DriverType.CHROME})
    public void testGrid3(RemoteWebDriver driver) {
        driver.get("https://www.google.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("JUnit5 extensions");
        search.submit();
    }

}