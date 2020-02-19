package framework.frontend;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import framework.utils.PropertyUtils;

import static com.codeborne.selenide.Browsers.*;

public class WebdriverFactory {

    public static void getWebdriverInstance(){
        createWebdriver();
    }

    public static void closeWebdriverInstance(){
        shutdownWebdriver();
    }

    private static void createWebdriver(){
        String  browser =               PropertyUtils.getPropString(    "selenide.browser",             "selenide.properties");
        Boolean headless =              PropertyUtils.getPropBoolean(   "selenide.headless",            "selenide.properties");
        String  pageLoadStrategy =      PropertyUtils.getPropString(    "selenide.pageloading.strategy","selenide.properties");
        Integer timeout =               PropertyUtils.getPropInteger(   "selenide.timeout",             "selenide.properties");
        Boolean remoteExecution =       PropertyUtils.getPropBoolean(   "selenide.remote.execution",    "selenide.properties");
        String  remoteHost =            PropertyUtils.getPropString(    "selenide.remote.host",         "selenide.properties");


        System.out.println("The selected browser is: " + browser);
        System.out.println("The selected headless state is: " + headless);
        System.out.println("The selected implicit timeout limit is: " + timeout);

        switch (browser) {
            case IE:
                Configuration.fastSetValue = true;
                Configuration.browserCapabilities.acceptInsecureCerts();
                break;
            case CHROME:
                Configuration.headless = headless;
                break;
            case FIREFOX:
                break;
            case EDGE:
                break;
            default:
                System.out.println("No previously known webbrowser is specified, please check properties file for any missmatch. Specified browser is: " + browser);
                break;
        }
        Configuration.browser = browser;
        Configuration.timeout = timeout;
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = pageLoadStrategy;

        if(remoteExecution){
            Configuration.remote = remoteHost;
        }

    }

    private static void shutdownWebdriver(){
        WebDriverRunner.closeWebDriver();
    }
}
