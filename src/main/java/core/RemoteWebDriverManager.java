package core;

import java.net.URL;
import javax.validation.constraints.NotEmpty;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class RemoteWebDriverManager {
    public static class Builder {
        @NotEmpty
        private String serverUrl;
        @NonNull
        private Capabilities capabilities;

       public Builder serverURL(String serverUrl) {
            this.serverUrl = serverUrl; return this;
        }

        public Builder capabilities(Capabilities capabilities) {
           this.capabilities = capabilities; return this;
        }

        @SneakyThrows
        public RemoteWebDriver build() {
          return new RemoteWebDriver(new URL(serverUrl), capabilities);
        }
    }
}
