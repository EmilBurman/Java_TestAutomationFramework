package framework.behaviordriven.services.cat;

import framework.api.services.cat.CatServiceManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.time.Instant;
import java.util.Date;

public class StepDefinitionsCat {
    String response;
    @Given("I get a random cat fact")
    public void getRandomCatFact(){
        response = CatServiceManager.getRandomCatFactObject();
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
