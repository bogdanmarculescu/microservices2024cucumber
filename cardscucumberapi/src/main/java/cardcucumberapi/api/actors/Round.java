package cardcucumberapi.api.actors;

import cardcucumberapi.api.APIClient;
import cardcucumberapi.api.dtos.OutcomeDTO;

import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.*;

public class Round {

    private final String userName;
    private final APIClient apiClient;
    private OutcomeDTO outcomeDTO;

    public Round(String name) {
        this.userName = name;
        this.apiClient = new APIClient();
    }

    public OutcomeDTO callTestEndpoint() throws Exception{
        HttpResponse<OutcomeDTO> outcome = apiClient.getTestOutcome();
        assertThat(outcome.statusCode()).isEqualTo(200);
        this.outcomeDTO = outcome.body();
        return this.outcomeDTO;
    }

    public OutcomeDTO getOutcomeDTO() {
        return outcomeDTO;
    }
}
