package framework.utils;

import org.junit.jupiter.api.Tag;

public class Tags {
    // All tags connected to frontend testing through Selenium
    public static final String FRONTEND = "frontend.test";
    public static final String FRONTEND_WIKIPEDIA = "frontend.test.wikipedia";

    // All tags connected to direct API testing
    public static final String API = "api.test";
    public static final String API_CAT = "api.test.cat";

    /*
    For anyone using this repository I haven't found a way to run behaviordriven tests with tags from here.
    Instead you must use already defined cucumber tags that follow the gherkin definition of a tag with @
    Currently known tags are:
    @BDD
    @BDD_CAT
    @BDD_HIQ

    Please understand that the compiler is case sensitive.
     */
}
