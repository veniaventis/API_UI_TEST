package api.models;

import com.mashape.unirest.http.JsonNode;


public class ResponseJsonModel {
    protected int statusCode;
    protected JsonNode body;

    public int getStatusCode() {
        return statusCode;
    }

    public JsonNode getBody() {
        return body;
    }
}
