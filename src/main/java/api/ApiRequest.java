package api;

import api.models.*;
import lombok.SneakyThrows;
import utlis.ApiUtils;
import utlis.JsonUtils;
import utlis.UrlUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class ApiRequest {
    public static ResponseJsonModel RESPONSE_JSON = null;
    @SneakyThrows
    public static SendPostModel sendPostOnTheWall(String message) {
        String request = UrlUtils.sendPostUri( message);
        RESPONSE_JSON = ApiUtils.sendPostRequest(request);
        return JsonUtils.jsonStringModelResponse(RESPONSE_JSON.getBody(), SendPostModel.class);
    }

    public static String editPostWithAttachment(String message, String postId, String filePath) {
        String sentPhotoId = getSavedPhotoResponse(UrlUtils.OWNER_ID, filePath).getId();
        String request = UrlUtils.editPostWithAttachmentUri(postId, message, sentPhotoId);
        RESPONSE_JSON = ApiUtils.sendPostRequest(request);
        return RESPONSE_JSON.getBody().getObject().get("response").toString();
    }

    @SneakyThrows
    public static SavePhotoModel getSavedPhotoResponse(String ownerId, String filePath) {
        String request = UrlUtils.sendGetPhotoUploadUrl(UrlUtils.OWNER_ID);
        RESPONSE_JSON = ApiUtils.sendGetRequest(request);
        WallUploadServerModel wallUploadServerResponse = JsonUtils.jsonStringModelResponse(RESPONSE_JSON.getBody(),WallUploadServerModel.class);
        RESPONSE_JSON = ApiUtils.uploadResponse(wallUploadServerResponse.getUploadUrl(), filePath, "photo");
        UploadModel uploadPhotoResponse = JsonUtils.jsonToStringModel(RESPONSE_JSON.getBody(), UploadModel.class);
        String encodedURL = null;
        encodedURL = URLEncoder.encode(uploadPhotoResponse.getPhoto(), StandardCharsets.UTF_8);
        String request1 = String.format("%s%s?user_id=%s&photo=%s&server=%s&hash=%s&access_token=%s&v=%s", UrlUtils.BASE_URL, Methods.SAVE_WALL_PHOTO.getMethod(), ownerId, encodedURL, uploadPhotoResponse.getServer(), uploadPhotoResponse.getHash(), UrlUtils.ACCESS_TOKEN,UrlUtils.API_VERSION);
        RESPONSE_JSON = ApiUtils.sendPostRequest(request1);
        return JsonUtils.jsonToArray(RESPONSE_JSON.getBody(), SavePhotoModel.class);
    }

    @SneakyThrows
    public static String sendCommentToPost(String message, String postId) {
        String request = UrlUtils.sendCommentUrl(postId, message);
        RESPONSE_JSON = ApiUtils.sendPostRequest(request);
        CommentModel response = JsonUtils.jsonStringModelResponse(RESPONSE_JSON.getBody(), CommentModel.class);
        return response.getCommentId();
    }

    @SneakyThrows
    public static boolean isLikedPost(String postId, String userId) {
        String request = UrlUtils.isLikedUrl(userId, postId);
        RESPONSE_JSON = ApiUtils.sendGetRequest(request);
        IsLikedModel response = JsonUtils.jsonStringModelResponse(RESPONSE_JSON.getBody(), IsLikedModel.class);
        return response.isLiked();
    }

    public static void deletePost(String postId) {
        String request = UrlUtils.deletePostUrl(postId);
        ApiUtils.sendPostRequest(request);
    }
}
