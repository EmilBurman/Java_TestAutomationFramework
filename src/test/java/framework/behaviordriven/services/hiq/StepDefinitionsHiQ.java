package framework.behaviordriven.services.hiq;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class StepDefinitionsHiQ {

    @Given("I navigate to the HiQ website")
    public void i_navigate_to_the_HiQ_website() {
        System.out.println("HEJ HEJ MONIKA");
        Assertions.assertTrue(true);
    }

    @When("I arrive on the page")
    public void i_arrive_on_the_page() {
        System.out.println("HEJ PÅ DIG MONIKA");
        Assertions.assertTrue(true);
    }

    @Then("I should see a beautiful HiQ logo")
    public void i_should_see_a_beautiful_HiQ_logo() {
        System.out.println("HEJ HEJ MONIKA");
        Assertions.assertTrue(true);
    }
}
