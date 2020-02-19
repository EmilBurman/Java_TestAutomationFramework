package framework.frontend.pages.wikipedia;

import framework.frontend.AbstractFrontendTestcase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static framework.utils.Tags.FRONTEND;
import static framework.utils.Tags.FRONTEND_WIKIPEDIA;

public class SearchWikipedia_test extends AbstractFrontendTestcase {


    @ParameterizedTest
    @ValueSource(strings = {"banana","pineapple","pancake"})
    @Tag(FRONTEND)
    @Tag(FRONTEND_WIKIPEDIA)
    public void openWikipediaAndSearchForTerm(String term){
        Wikipedia_startpage.openPage();
        Wikipedia_startpage.searchForTerm(term);
    }
}
