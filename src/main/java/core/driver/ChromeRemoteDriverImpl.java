package core.driver;

import core.RemoteWebDriverFactory;
import java.util.Optional;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeRemoteDriverImpl implements RemoteDriver {
    private final RemoteWebDriverFactory factory;

    public ChromeRemoteDriverImpl() {
        String gridUrl = Optional.ofNullable(System.getProperty("prop.selenium.grid.url")).orElseThrow(()-> new IllegalArgumentException("SeleniumGrid URL is not provided in VM options"));
        DesiredCapabilities caps = new DesiredCapabilities("chrome", "80.0.3987.106", Platform.LINUX);
        factory = new RemoteWebDriverFactory.Builder().serverURL(gridUrl).capabilities(caps).build();
    }

    @Override
    public RemoteWebDriver getDriver() {
        return factory.getDriver();
    }
}
