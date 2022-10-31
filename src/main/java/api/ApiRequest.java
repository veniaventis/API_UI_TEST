package api;

import api.models.*;
import lombok.SneakyThrows;
import utlis.ApiUtils;
import utlis.ConfigUtils;
import utlis.JsonUtils;
import utlis.UrlUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class ApiRequest {
    private static final String BASE_URL = ConfigUtils.getSettingsData("urlApi");
    private static final String OWNER_ID = ConfigUtils.getConfidentialData("ownerId");
    private static final String ACCESS_TOKEN = ConfigUtils.getConfidentialData("accessToken");
    private static final String API_VERSION = ConfigUtils.getTestData("apiVersion");

    @SneakyThrows
    public static SendPostModel sendPostOnTheWall(String message) {
        String request = UrlUtils.sendPostUri(BASE_URL, Methods.SEND_POST.getMethod(), OWNER_ID, message, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(request);
        return JsonUtils.jsonStringModelResponse(jsonResponse.getBody(), SendPostModel.class);

    }

    public static String editPostWithAttachment(String message, String postId, String filePath) {
        String sentPhotoId = getSavedPhotoResponse(OWNER_ID, filePath).getId();
        String request = UrlUtils.editPostWithAttachmentUri(BASE_URL, Methods.EDIT_POST.getMethod(), OWNER_ID, postId, message, sentPhotoId, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(request);
        return jsonResponse.getBody().getObject().get("response").toString();
    }

    @SneakyThrows
    public static SavePhotoModel getSavedPhotoResponse(String ownerId, String filePath) {
        String request = UrlUtils.sendGetUrl(BASE_URL, Methods.PHOTO_UPLOAD_SERVER.getMethod(), ownerId, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendGetRequest(request);
        WallUploadServerModel wallUploadServerResponse = JsonUtils.jsonStringModelResponse(jsonResponse.getBody(),WallUploadServerModel.class);
        ResponseJsonModel jsonResponse1 = ApiUtils.uploadResponse(wallUploadServerResponse.getUploadUrl(), filePath, "photo");
        UploadModel uploadPhotoResponse = JsonUtils.jsonToStringModel(jsonResponse1.getBody(), UploadModel.class);
        String encodedURL = null;
        encodedURL = URLEncoder.encode(uploadPhotoResponse.getPhoto(), StandardCharsets.UTF_8);
        String request1 = String.format("%s%s?user_id=%s&photo=%s&server=%s&hash=%s&access_token=%s&v=%s", BASE_URL, Methods.SAVE_WALL_PHOTO.getMethod(), ownerId, encodedURL, uploadPhotoResponse.getServer(), uploadPhotoResponse.getHash(), ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse2 = ApiUtils.sendPostRequest(request1);
        return JsonUtils.jsonToArray(jsonResponse2.getBody(), SavePhotoModel.class);
    }

    @SneakyThrows
    public static String sendCommentToPost(String message, String postId) {
        String request = UrlUtils.sendCommentUrl(BASE_URL, Methods.WALL_CREATE_COMMENT.getMethod(), OWNER_ID, postId, message, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(request);
        CommentModel response = JsonUtils.jsonStringModelResponse(jsonResponse.getBody(), CommentModel.class);
        return response.getCommentId();
    }

    @SneakyThrows
    public static boolean isLikedPost(String postId, String userId) {
        String request = UrlUtils.isLikedUrl(BASE_URL, Methods.IS_LIKED.getMethod(), OWNER_ID, userId, postId, ACCESS_TOKEN, API_VERSION);
        ResponseJsonModel jsonResponse = ApiUtils.sendGetRequest(request);
        IsLikedModel response = JsonUtils.jsonStringModelResponse(jsonResponse.getBody(), IsLikedModel.class);
        return response.isLiked();
    }

    public static void deletePost(String postId) {
        String request = UrlUtils.deletePostUrl(BASE_URL, Methods.DELETE_POST.getMethod(), OWNER_ID, postId, ACCESS_TOKEN, API_VERSION);
        ApiUtils.sendPostRequest(request);
    }
}
