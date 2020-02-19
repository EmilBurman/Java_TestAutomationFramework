package framework.frontend;

import framework.AbstractTestcase;
import framework.frontend.WebdriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractFrontendTestcase extends AbstractTestcase {

    @BeforeAll
    public static void startFrontendSuite(){
        WebdriverFactory.getWebdriverInstance();
    }

    @AfterAll
    public static void endFrontendSuite(){
        WebdriverFactory.closeWebdriverInstance();
    }
}
