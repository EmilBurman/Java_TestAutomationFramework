package framework.frontend.pages.wikipedia;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class Wikipedia_startpage {
    private static final String START_URL = "https://en.wikipedia.org/wiki/Main_Page";
    private static final String WEBSITE_LOGO_ID = "p-logo";
    private static final String SEARCH_FIELD_ID = "searchInput";
    private static final String SEARCH_BUTTON_ID = "searchButton";

    public static void openPage(){
        open(START_URL);
        assert $(By.id(WEBSITE_LOGO_ID)).isDisplayed();
    }

    public static boolean searchForTerm (String termToSearchFor){
        $(By.id(SEARCH_FIELD_ID)).sendKeys(termToSearchFor);
        $(By.id(SEARCH_BUTTON_ID)).click();
        return Wikipedia_contentPage.checkIfFirstHeadingContainsTerm(termToSearchFor);
    }
}
