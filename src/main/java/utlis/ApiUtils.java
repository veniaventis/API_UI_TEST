package utlis;

import api.models.ResponseJsonModel;
import aquality.selenium.core.logging.Logger;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;

public class ApiUtils {

    public static ResponseJsonModel sendGetRequest(String baseHttp ,String requestPath) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.get(requestPath).asJson();
        } catch (UnirestException e) {
            Logger.getInstance().debug("Get request doesn't send ", e);
        }
        return new ResponseJsonModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseJsonModel sendPostRequest(String baseHttp, String requestPath) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(requestPath)
                    .asJson();
        } catch (UnirestException e) {
            Logger.getInstance().debug("New Post doesn't send", e);
        }
        return new ResponseJsonModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }

    public static ResponseJsonModel uploadResponse(String httpBody, String filePath, String typeOfFile) {
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(httpBody).field(typeOfFile, new File(filePath)).asJson();
        } catch (UnirestException e) {
            Logger.getInstance().debug("Unable to upload file", e);
        }
        return new ResponseJsonModel(jsonResponse.getStatus(), jsonResponse.getBody());
    }
}
