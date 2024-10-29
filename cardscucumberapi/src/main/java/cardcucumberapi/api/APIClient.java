package cardcucumberapi.api;

import java.io.UncheckedIOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import cardcucumberapi.api.dtos.OutcomeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


public class APIClient {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final String BACKEND_HOST = "http://localhost:8000";
    private final HttpClient httpClient;

    public APIClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // A test on "/round/test

    public HttpResponse<OutcomeDTO> getTestOutcome() throws Exception{
        HttpRequest testOutcome = HttpRequest.newBuilder(
                URI.create(BACKEND_HOST + "/round/test"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return httpClient.send(testOutcome, new JsonBodyHandler<>(OutcomeDTO.class));
    }

    public HttpResponse<OutcomeDTO> getOngoingOutcome() throws Exception{
        HttpRequest testOutcome = HttpRequest.newBuilder(
                        URI.create(BACKEND_HOST + "/ongoing"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return httpClient.send(testOutcome, new JsonBodyHandler<>(OutcomeDTO.class));
    }

    // A test on "/ongoing"



    // A POST on "/record


    static class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {

        private final Class<T> clazz;

        public JsonBodyHandler(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {
            var stringBodySubscriber = HttpResponse.BodySubscribers
                    .ofString(StandardCharsets.UTF_8);

            return HttpResponse.BodySubscribers.mapping(
                    stringBodySubscriber,
                    (body) -> {
                        try {
                            return OBJECT_MAPPER.readValue(body, this.clazz);
                        } catch (JsonProcessingException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }

    }

    static class JsonListBodyHandler<T> implements HttpResponse.BodyHandler<List<T>> {

        private final CollectionType mapCollectionType;

        public JsonListBodyHandler(Class<T> clazz) {
            this.mapCollectionType = OBJECT_MAPPER.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
        }

        @Override
        public HttpResponse.BodySubscriber<List<T>> apply(HttpResponse.ResponseInfo responseInfo) {
            var stringBodySubscriber = HttpResponse.BodySubscribers
                    .ofString(StandardCharsets.UTF_8);

            return HttpResponse.BodySubscribers.mapping(
                    stringBodySubscriber,
                    (body) -> {
                        try {
                            return OBJECT_MAPPER.readValue(body, this.mapCollectionType);
                        } catch (JsonProcessingException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }

    }
}
