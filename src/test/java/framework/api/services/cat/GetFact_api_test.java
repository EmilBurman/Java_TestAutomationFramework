package framework.api.services.cat;

import framework.api.AbstractApiTestcase;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static framework.adapters.HTTPadapter.validateResponseCode;
import static framework.api.services.utils.CatTags.DOG;
import static framework.api.services.utils.CatTags.HORSE;
import static framework.utils.JsonUtils.*;
import static framework.utils.Tags.API;
import static framework.utils.Tags.API_CAT;

@Tag(API)
@Tag(API_CAT)
public class GetFact_api_test extends AbstractApiTestcase {
    String uriRequest;
    @ParameterizedTest
    @ValueSource(strings = {HORSE,DOG})
    public void checkIfAnimalTypeExists(String animalType){
        uriRequest = new CatServiceManager.CatRequestBuilder()
                .getRandom()
                .usingAnimalType(animalType)
                .build()
                .toString();
        String responseAsJson = checkResponseAndConvertToJsonString(uriRequest);
        // Make sure JSON contains expected value
        Assertions.assertTrue(getSpecificValueFromJSON(responseAsJson,"type").contains(animalType.toLowerCase()), "Unable to get a fact, please investigate.");
    }

    @ParameterizedTest
    @ValueSource(ints = {5,10})
    public void getDifferentAmountOfAnimal(Integer amount){
        uriRequest = new CatServiceManager.CatRequestBuilder()
                .getRandom()
                .withAmount(amount)
                .build()
                .toString();
        String responseAsJson = checkResponseAndConvertToJsonString(uriRequest);
        // Make sure JSON contains expected value
        Assertions.assertTrue(convertJSONStringToJSONArray(responseAsJson).size()==amount, "Unable to get several facts, please investigate.");
    }

    @Test
    public void validateRandomFactResponseTextIsNotEmpty(){
        uriRequest = new CatServiceManager.CatRequestBuilder()
                .getRandom()
                .build()
                .toString();
        String responseAsJson = checkResponseAndConvertToJsonString(uriRequest);
        // Make sure JSON contains expected value
        Assertions.assertTrue(getSpecificValueFromJSON(responseAsJson,"text")!=null,"Missing response text");
    }

    @ParameterizedTest
    @MethodSource("specificCatFacts")
    public void validateSpecificFactThroughId(String factID, String expectedFact){
        uriRequest = new CatServiceManager.CatRequestBuilder()
                .usingID(factID)
                .build()
                .toString();
        String responseAsJson = checkResponseAndConvertToJsonString(uriRequest);
        // Make sure JSON contains expected value
        Assertions.assertTrue(getSpecificValueFromJSON(responseAsJson,"text").contains(expectedFact), "Fetched fact differs from expected result.");
    }

    private static Stream<Arguments> specificCatFacts() {
        return Stream.of(
                Arguments.of("591f98883b90f7150a19c28c", "Cats lived with soldiers in trenches, where they killed mice during World War I."),
                Arguments.of("591f98703b90f7150a19c14f", "Unlike humans, cats do not need to blink their eyes on a regular basis to keep their eyes lubricated.")
        );
    }

    @ParameterizedTest
    @MethodSource("specificCatObjects")
    public void validateEntireObject(String factID, JSONObject expectedObject){
        uriRequest = new CatServiceManager.CatRequestBuilder()
                .usingID(factID)
                .build()
                .toString();
        String responseAsJson = checkResponseAndConvertToJsonString(uriRequest);
        Assertions.assertTrue(convertJSONStringToJSONObject(responseAsJson).equals(expectedObject), "Fetched fact differs from expected result.");
    }

    private static Stream<Arguments> specificCatObjects() {
        return Stream.of(
                Arguments.of("591f98883b90f7150a19c28c", getJSONResourceAsJSONObject("framework/api.services.cat/catfact.json"))
        );
    }

    public String checkResponseAndConvertToJsonString(String uri){
        // Make the call
        HttpResponse responseAsHttp = new CatServiceManager().getResponseFromUriAsHttpResponse(uri);
        // Validate 200 as expected from call
        Assertions.assertTrue(validateResponseCode(HttpStatus.SC_OK,responseAsHttp));
        // Convert to json to manage data validation
        String responseAsJson = convertHttpResponseToJsonString(responseAsHttp);
        return responseAsJson;
    }
}
