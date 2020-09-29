package core;

import java.net.URL;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class RemoteWebDriverFactory {
    @NonNull
    private final String serverUrl;
    @NonNull
    private final Capabilities capabilities;

    private RemoteWebDriverFactory(Builder builder) {
        serverUrl = builder.serverUrl;
        capabilities = builder.capabilities;
    }

    @SneakyThrows
    public RemoteWebDriver getDriver() {
        return new RemoteWebDriver(new URL(serverUrl), capabilities);
    }

    public static class Builder {
        private String serverUrl;
        private Capabilities capabilities;

       public Builder serverURL(String serverUrl) {
            this.serverUrl = serverUrl; return this;
        }

        public Builder capabilities(Capabilities capabilities) {
           this.capabilities = capabilities; return this;
        }

        @SneakyThrows
        public RemoteWebDriverFactory build() {
           return new RemoteWebDriverFactory(this);
        }

    }

}
