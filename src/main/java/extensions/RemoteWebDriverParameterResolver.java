package extensions;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import core.driver.ChromeRemoteDriverImpl;
import core.driver.FirefoxRemoteDriverImpl;
import core.driver.RemoteDriver;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWebDriverParameterResolver implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(invocationContext(new ChromeRemoteDriverImpl()), invocationContext(new FirefoxRemoteDriverImpl()));
    }

    private TestTemplateInvocationContext invocationContext(RemoteDriver remoteDriver) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return remoteDriver.getClass().getSimpleName();
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        return parameterContext.getParameter().getType().equals(RemoteWebDriver.class);
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        return remoteDriver.getDriver();
                    }
                });
            }
        };
    }
}
