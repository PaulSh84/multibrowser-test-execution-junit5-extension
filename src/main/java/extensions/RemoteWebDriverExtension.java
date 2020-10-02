package extensions;

import annotations.MultiBrowserTest;
import core.driver.RemoteDriver;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteWebDriverExtension implements BeforeEachCallback, AfterEachCallback, TestTemplateInvocationContextProvider {

    private static final Logger log = LoggerFactory.getLogger(RemoteWebDriverExtension.class);
    private RemoteWebDriver driver;

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        MultiBrowserTest annotation = context.getRequiredTestMethod().getAnnotation(MultiBrowserTest.class);
        return Arrays.stream(annotation.drivers()).map(driverType -> invocationContext(driverType.driver()));
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

}