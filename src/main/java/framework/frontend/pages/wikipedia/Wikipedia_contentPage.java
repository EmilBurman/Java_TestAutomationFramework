package framework.frontend.pages.wikipedia;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Wikipedia_contentPage {
    private static final String FIRST_HEADING_ID = "firstHeading";

    public static boolean checkIfFirstHeadingContainsTerm (String term) {
        String firstHeadingText = $(By.id(FIRST_HEADING_ID)).getText().toLowerCase();
        return firstHeadingText.contains(term.toLowerCase());
    }
}
