package extensions;

import core.driver.ChromeRemoteDriverImpl;
import core.driver.FirefoxRemoteDriverImpl;
import core.driver.RemoteDriver;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteWebDriverExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback, TestTemplateInvocationContextProvider {

    private static final Logger log = LoggerFactory.getLogger(RemoteWebDriverExtension.class);
    private RemoteWebDriver driver;

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

                    @SneakyThrows
                    @Override
                    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        return driver = remoteDriver.getDriver();
                    }
                });
            }
        };
    }


    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("BeforeAll method started");
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("BeforeEach method started");
    }

    @SneakyThrows
    @Override
    public void afterEach(ExtensionContext context) {
        log.info("AfterEach method started");
        log.info("closing driver");
        driver.quit();
        log.info("driver closed");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        log.info("AfterAll method started");
    }
}