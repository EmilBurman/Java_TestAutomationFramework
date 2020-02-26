package framework.api.services.cat;

import framework.api.AbstractApiTestcase;
import framework.utils.JsonUtils;
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

import static framework.api.services.cat.CatServiceManager.getOtherAnimalFact;
import static framework.utils.JsonUtils.getJSONResourceAsJSONObject;
import static framework.utils.Tags.API;
import static framework.utils.Tags.API_CAT;

@Tag(API)
@Tag(API_CAT)
public class GetFact_api_test extends AbstractApiTestcase {

    @Test
    public void valdiate200Response(){
        Assertions.assertTrue(CatServiceManager.validateResponseCode(HttpStatus.SC_OK, "/random"), "Endpoint responded with a incorrect status code.");
    }

    @Test
    public void validateRandomFactResponseTextIsNotEmpty(){
        Assertions.assertTrue(CatServiceManager.getRandomCatFact()!= null, "Unable to get a fact, please investigate.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"horse","dog"})
    public void validateOtherAnimalFactresponseTextIsNotEmpty(String animal){
        Assertions.assertTrue(getOtherAnimalFact(animal).contains(animal.toLowerCase()), "Unable to get a fact, please investigate.");
    }

    @ParameterizedTest
    @MethodSource("specificCatFacts")
    public void validateSpecificFact(String factID, String expectedFact){
        Assertions.assertTrue(CatServiceManager.getSpecificFactText(factID).contains(expectedFact), "Fetched fact differs from expected result.");
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
        String apiJsonObject = CatServiceManager.getSpecificFactObject(factID);
        Assertions.assertTrue(JsonUtils.convertJSONStringToJSONObject(apiJsonObject).equals(expectedObject));
    }

    private static Stream<Arguments> specificCatObjects() {
        return Stream.of(
                Arguments.of("591f98883b90f7150a19c28c", getJSONResourceAsJSONObject("framework/api.services.cat/catfact.json"))
        );
    }
}
