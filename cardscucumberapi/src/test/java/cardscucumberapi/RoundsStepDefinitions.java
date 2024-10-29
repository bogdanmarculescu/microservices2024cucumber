package cardscucumberapi;

import cardcucumberapi.api.actors.Round;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundsStepDefinitions {
    private Round round;

    @Given("a new user {word}")
    public void a_new_user(String word) {
        // round can be initialized maybe
        this.round = new Round(word);
    }

    @When("(s)he calls the endpoint to test")
    public void calls_the_endpoint_to_test() throws Exception {
        // someone maybe ought to call that endpoint - round
        round.callTestEndpoint();
    }

    @Then("(s)he gets a test outcome")
    public void gets_a_test_outcome() {
        // check that round has the correct outcome
        assertThat(round.getOutcomeDTO().getOutcomeId())
                .isEqualTo(42);

    }
}
