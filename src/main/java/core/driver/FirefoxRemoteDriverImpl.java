package core.driver;

import core.RemoteWebDriverManager;
import java.util.Optional;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class FirefoxRemoteDriverImpl implements RemoteDriver {
    private final RemoteWebDriver driver;

    public FirefoxRemoteDriverImpl() {
        String gridUrl = Optional.ofNullable(System.getProperty("prop.selenium.grid.url"))
            .orElseThrow(()-> new IllegalArgumentException("SeleniumGrid URL is not provided in VM options"));
        DesiredCapabilities caps = new DesiredCapabilities("firefox", "73.0", Platform.LINUX);
        driver = new RemoteWebDriverManager.Builder().serverURL(gridUrl).capabilities(caps).build();
    }

    @Override
    public RemoteWebDriver getDriver() {
        return driver;
    }
}
