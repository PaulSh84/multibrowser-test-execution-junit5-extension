package core.driver;

import core.RemoteWebDriverFactory;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeRemoteDriverImpl implements RemoteDriver {
    private final RemoteWebDriverFactory factory;

    @RegisterExtension
    SeleniumJupiter jupiter = new SeleniumJupiter();

    public ChromeRemoteDriverImpl() {
        DesiredCapabilities caps = new DesiredCapabilities("chrome", "80.0.3987.106", Platform.LINUX);
        factory = new RemoteWebDriverFactory.Builder().serverURL(jupiter.getConfig().getSeleniumServerUrl()).capabilities(caps).build();
    }

    @Override
    public RemoteWebDriver getDriver() {
        return factory.getDriver();
    }
}
