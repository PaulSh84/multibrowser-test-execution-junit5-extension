package core.driver;

import core.RemoteWebDriverFactory;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxRemoteDriverImpl implements RemoteDriver {
    private final RemoteWebDriverFactory factory;

    @RegisterExtension
    SeleniumJupiter jupiter = new SeleniumJupiter();

    public FirefoxRemoteDriverImpl() {
        DesiredCapabilities caps = new DesiredCapabilities("firefox", "73.0", Platform.LINUX);
        factory = new RemoteWebDriverFactory.Builder().serverURL(jupiter.getConfig().getSeleniumServerUrl()).capabilities(caps).build();
    }

    @Override
    public RemoteWebDriver getDriver() {
        return factory.getDriver();
    }
}
