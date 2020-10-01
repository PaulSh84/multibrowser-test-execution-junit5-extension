package core;

import core.driver.ChromeRemoteDriverImpl;
import core.driver.FirefoxRemoteDriverImpl;
import core.driver.RemoteDriver;

public enum DriverType {
    CHROME(new ChromeRemoteDriverImpl()), FIREFOX(new FirefoxRemoteDriverImpl());

    private final RemoteDriver driver;

    DriverType(RemoteDriver remoteDriver) {
        this.driver = remoteDriver;
    }

    public RemoteDriver driver() {
        return driver;
    }
}
