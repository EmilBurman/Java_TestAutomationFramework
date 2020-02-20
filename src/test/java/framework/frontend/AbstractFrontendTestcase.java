package framework.frontend;

import framework.AbstractTestcase;
import framework.frontend.WebdriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Instant;
import java.util.Date;

public abstract class AbstractFrontendTestcase extends AbstractTestcase {

    @BeforeAll
    public static void startFrontendSuite(){
        WebdriverFactory.getWebdriverInstance();
    }

    @AfterAll
    public static void endFrontendSuite(){
        WebdriverFactory.closeWebdriverInstance();
    }

    @BeforeEach
    public void startNewTest(){
        System.out.println("~~~ STARTING NEW FRONTEND TEST ~~~");
        System.out.println(Date.from(Instant.now()));
    }

    @AfterEach
    public void endTest(){
        System.out.println(Date.from(Instant.now()));
        System.out.println("~~~ ENDING FRONTEND TEST ~~~");
    }
}
