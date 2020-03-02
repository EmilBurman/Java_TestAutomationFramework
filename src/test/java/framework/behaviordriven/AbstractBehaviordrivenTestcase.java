package framework.behaviordriven;

import framework.AbstractTestcase;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, strict = true)
public class AbstractBehaviordrivenTestcase extends AbstractTestcase {

}
