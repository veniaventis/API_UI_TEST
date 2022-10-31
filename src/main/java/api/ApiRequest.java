package api;

import api.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import utlis.ApiUtils;
import utlis.ConfigUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class ApiRequest {
    private static final String BASE_URL = ConfigUtils.getSettingsData("urlApi");
    private static final String OWNER_ID = ConfigUtils.getConfidentialData("ownerId");
    private static final String ACCESS_TOKEN = ConfigUtils.getConfidentialData("accessToken");
    private static final String API_VERSION = ConfigUtils.getTestData("apiVersion");

    @SneakyThrows
    public static String sendPostOnTheWall(String message) {
        String request = String.format("%s?owner_id=%s&message=%s&access_token=%s&v=%s", Methods.SEND_POST.getMethod(), OWNER_ID, message, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(BASE_URL, request);
        SendPostModel response = new ObjectMapper().readValue(jsonResponse.getBody().getObject().get("response").toString(), SendPostModel.class);
        return response.getPostId();
    }

    public static String editPostWithAttachment(String message, String postId, String filePath) {
        String sentPhotoId = getSavedPhotoResponse(OWNER_ID, filePath).getId();
        String request = String.format("%s?owner_id=%s&post_id=%s&message=%s&attachments=photo%s_%s&access_token=%s&v=%s", Methods.EDIT_POST.getMethod(), OWNER_ID, postId, message, OWNER_ID, sentPhotoId, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(BASE_URL, request);
        return jsonResponse.getBody().getObject().get("response").toString();
    }

    @SneakyThrows
    public static SavePhotoModel getSavedPhotoResponse(String ownerId, String filePath) {
        String request = String.format("%s?user_id=%s&access_token=%s&v=%s", Methods.PHOTO_UPLOAD_SERVER.getMethod(), ownerId, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendGetRequest(BASE_URL, request);
        WallUploadServerModel wallUploadServerResponse = new ObjectMapper().readValue(jsonResponse.getBody().getObject().get("response").toString(), WallUploadServerModel.class);
        ResponseJsonModel jsonResponse1 = ApiUtils.uploadResponse(wallUploadServerResponse.getUploadUrl(), filePath, "photo");
        UploadModel uploadPhotoResponse = new ObjectMapper().readValue(jsonResponse1.getBody().toString(), UploadModel.class);
        String encodedURL = null;
        encodedURL = URLEncoder.encode(uploadPhotoResponse.getPhoto(), StandardCharsets.UTF_8);
        String request1 = String.format("%s?user_id=%s&photo=%s&server=%s&hash=%s&access_token=%s&v=%s", Methods.SAVE_WALL_PHOTO.getMethod(),
                ownerId, encodedURL, uploadPhotoResponse.getServer(), uploadPhotoResponse.getHash(), ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse2 = ApiUtils.sendPostRequest(BASE_URL, request1);
        return new ObjectMapper().readValue(jsonResponse2.getBody().getObject().getJSONArray("response").get(0).toString(), SavePhotoModel.class);
    }

    @SneakyThrows
    public static String sendCommentToPost(String message, String postId) {
        String request = String.format("%s?owner_id=%s&post_id=%s&message=%s&access_token=%s&v=%s", Methods.WALL_CREATE_COMMENT.getMethod(), OWNER_ID, postId, message, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(BASE_URL, request);
        CommentModel response = new ObjectMapper().readValue(jsonResponse.getBody().getObject().get("response").toString(), CommentModel.class);
        return response.getCommentId();
    }

    @SneakyThrows
    public static boolean isLikedPost(String postId, String userId) {
        String request = String.format("%s?owner_id=%s&user_id=%s&type=post&item_id=%s&access_token=%s&v=%s", Methods.IS_LIKED.getMethod(), OWNER_ID, userId, postId, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendGetRequest(BASE_URL, request);
        IsLikedModel response = new ObjectMapper().readValue(jsonResponse.getBody().getObject().get("response").toString(), IsLikedModel.class);
        return response.isLiked();
    }

    public static void deletePost(String postId) {
        String request = String.format("%s?owner_id=%s&post_id=%s&access_token=%s&v=%s", Methods.DELETE_POST.getMethod(), OWNER_ID, postId, ACCESS_TOKEN, API_VERSION);
        ApiUtils.sendPostRequest(BASE_URL, request);
    }
}
