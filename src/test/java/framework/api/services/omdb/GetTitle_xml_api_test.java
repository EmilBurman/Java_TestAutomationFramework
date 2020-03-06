package framework.api.services.omdb;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static framework.tags.TestcaseTags.*;
import static framework.api.misc.AvailableApiServices.OMDB;
import static framework.api.misc.OmdbApiTerms.*;
import static framework.api.misc.OmdbApiTerms.SHORT_PLOT;
import static framework.api.services.ApiServiceManager.*;
import static framework.utils.JsonUtils.getSpecificValueFromJSON;

@Tag(API)
@Tag(API_OMDB)
public class GetTitle_xml_api_test {
    
    @ParameterizedTest
    @ValueSource(strings = {"Shawshank","Spiderman","Nausicaa of the Valley of the Wind"})
    public void validateMovieExists(String title) throws IOException {
        String uriRequest = new OmdbUriRequest.omdbRequestBuilder(TITLE,title)
                .usingFormat(MOVIE)
                .usingDatatype(XML_DATATYPE)
                .withPlotType(SHORT_PLOT)
                .withPageNumber(1)
                .build()
                .toString();
        // Make request and get the answer as a JSON string
        String request = getSpecificValueFromXmlResponse(OMDB, uriRequest, "title");
        System.out.println(request);
        //Make sure the title matches the expected value
        Assertions.assertTrue(request.toLowerCase().contains(title.toLowerCase()),"Unable to find the requested title.");
    }
    
}
