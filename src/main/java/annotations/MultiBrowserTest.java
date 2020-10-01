package annotations;

import core.DriverType;
import extensions.RemoteWebDriverExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(RemoteWebDriverExtension.class)
@TestTemplate
public @interface MultiBrowserTest {
    DriverType[] drivers() default DriverType.CHROME;
}
