package core.driver;

import core.RemoteWebDriverManager;
import java.util.Optional;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeRemoteDriverImpl implements RemoteDriver {
    private final RemoteWebDriver driver;

    public ChromeRemoteDriverImpl() {
        String gridUrl = Optional.ofNullable(System.getProperty("prop.selenium.grid.url"))
            .orElseThrow(()-> new IllegalArgumentException("SeleniumGrid URL is not provided in VM options"));
        DesiredCapabilities caps = new DesiredCapabilities("chrome", "85.0.4183.83", Platform.LINUX);
        driver = new RemoteWebDriverManager.Builder().serverURL(gridUrl).capabilities(caps).build();
    }

    @Override
    public RemoteWebDriver getDriver() {
        return driver;
    }
}
