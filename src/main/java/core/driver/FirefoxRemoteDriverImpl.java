package core.driver;

import core.RemoteWebDriverFactory;
import java.util.Optional;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class FirefoxRemoteDriverImpl implements RemoteDriver {
    private final RemoteWebDriverFactory factory;

    public FirefoxRemoteDriverImpl() {
        String gridUrl = Optional.ofNullable(System.getProperty("prop.selenium.grid.url")).orElseThrow(()-> new IllegalArgumentException("SeleniumGrid URL is not provided in VM options"));
        DesiredCapabilities caps = new DesiredCapabilities("firefox", "73.0", Platform.LINUX);
        factory = new RemoteWebDriverFactory.Builder().serverURL(gridUrl).capabilities(caps).build();
    }

    @Override
    public RemoteWebDriver getDriver() {
        return factory.getDriver();
    }
}
