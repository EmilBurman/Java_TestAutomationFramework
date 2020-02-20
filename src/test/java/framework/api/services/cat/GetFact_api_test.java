package framework.api.services.cat;

import framework.api.AbstractApiTestcase;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static framework.utils.Tags.API;
import static framework.utils.Tags.API_CAT;

@Tag(API)
@Tag(API_CAT)
public class GetFact_api_test extends AbstractApiTestcase {

    @Test
    public void valdiate200Response(){
        Assertions.assertTrue(CatServiceManager.validateResponseCode(HttpStatus.SC_OK), "Endpoint responded with a incorrect status code.");
    }

    @Test
    public void validateRandomFactResponseTextIsNotEmpty(){
        Assertions.assertTrue(CatServiceManager.getRandomCatFact()!= null, "Unable to get a fact, please investigate.");
    }
}
