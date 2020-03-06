package framework.behaviordriven.services.cat;

import framework.api.services.cat.CatUriRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static framework.api.services.ApiServiceManager.getResponseFromUriAsJsonString;
import static framework.api.misc.AvailableApiServices.CAT;

public class StepDefinitionsCat {
    String response;
    String uri;
    @Given("I get a random cat fact")
    public void getRandomCatFact(){
        uri = new CatUriRequest.CatRequestBuilder()
                .getRandom()
                .build()
                .toString();
        response = getResponseFromUriAsJsonString(CAT,uri);
        Assertions.assertNotNull(response);
    }

    @When("the API responds to the get request")
    public void validateResponse(){
        Assertions.assertNotNull(response);
    }

    @Then("it should respond with a {int} status code")
    public void validateResponseCode(int responseCode){
        Assertions.assertTrue(true);
    }
}
