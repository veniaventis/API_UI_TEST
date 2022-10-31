package api.models;

import com.mashape.unirest.http.JsonNode;
import lombok.Data;

@Data
public class ResponseJsonModel {
    protected int statusCode;
    protected JsonNode body;

    public ResponseJsonModel(int statusCode, JsonNode body) {
        this.statusCode = statusCode;
        this.body = body;
    }
}
