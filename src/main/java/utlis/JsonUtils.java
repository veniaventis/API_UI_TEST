package utlis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import lombok.SneakyThrows;

public class JsonUtils {
    @SneakyThrows
    public static <T> T jsonStringModelResponse(JsonNode content, Class<T> clazz) {
        return new ObjectMapper().readValue(content.getObject().get("response")
                .toString(), clazz);
    }
    @SneakyThrows
    public static <T> T jsonToStringModel(JsonNode content, Class<T> clazz) {
        return new ObjectMapper().readValue(content.getObject().toString(), clazz);
    }

    @SneakyThrows
    public static <T> T jsonToArray(JsonNode content, Class<T> clazz) {
        return new ObjectMapper().readValue(content.getObject().getJSONArray("response").get(0)
                .toString(),clazz);
    }

}
