package extensions;

import java.lang.reflect.Field;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteWebDriverExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {

    private static final Logger log = LoggerFactory.getLogger(RemoteWebDriverExtension.class);


    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("Before All method started");
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("Before Each method started");
    }

    @SneakyThrows
    @Override
    public void afterEach(ExtensionContext context) {
        log.info("After each method started");
        Field field = context.getRequiredTestClass().getDeclaredField("driver");
        field.setAccessible(true);
        RemoteWebDriver driver = (RemoteWebDriver) field.get(context.getRequiredTestInstance());
        log.info("closing driver " + driver.toString());
        driver.quit();
        log.info("driver closed");


    }

    @Override
    public void afterAll(ExtensionContext context) {
        log.info("After All method started");
    }

}