package api;

import api.models.*;
import lombok.SneakyThrows;
import utlis.ApiUtils;
import utlis.JsonUtils;
import utlis.UrlUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class ApiRequest {
    @SneakyThrows
    public static SendPostModel sendPostOnTheWall(String message) {
        String request = UrlUtils.sendPostUri( message);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(request);
        return JsonUtils.jsonStringModelResponse(jsonResponse.getBody(), SendPostModel.class);
    }

    public static String editPostWithAttachment(String message, String postId, String filePath) {
        String sentPhotoId = getSavedPhotoResponse(UrlUtils.OWNER_ID, filePath).getId();
        String request = UrlUtils.editPostWithAttachmentUri(postId, message, sentPhotoId);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(request);
        return jsonResponse.getBody().getObject().get("response").toString();
    }

    @SneakyThrows
    public static SavePhotoModel getSavedPhotoResponse(String ownerId, String filePath) {
        String request = UrlUtils.sendGetPhotoUploadUrl(UrlUtils.OWNER_ID);
        ResponseJsonModel jsonResponse = ApiUtils.sendGetRequest(request);
        WallUploadServerModel wallUploadServerResponse = JsonUtils.jsonStringModelResponse(jsonResponse.getBody(),WallUploadServerModel.class);
        ResponseJsonModel jsonResponse1 = ApiUtils.uploadResponse(wallUploadServerResponse.getUploadUrl(), filePath, "photo");
        UploadModel uploadPhotoResponse = JsonUtils.jsonToStringModel(jsonResponse1.getBody(), UploadModel.class);
        String encodedURL = null;
        encodedURL = URLEncoder.encode(uploadPhotoResponse.getPhoto(), StandardCharsets.UTF_8);
        String request1 = String.format("%s%s?user_id=%s&photo=%s&server=%s&hash=%s&access_token=%s&v=%s", UrlUtils.BASE_URL, Methods.SAVE_WALL_PHOTO.getMethod(), ownerId, encodedURL, uploadPhotoResponse.getServer(), uploadPhotoResponse.getHash(), UrlUtils.ACCESS_TOKEN,UrlUtils.API_VERSION);
        ResponseJsonModel jsonResponse2 = ApiUtils.sendPostRequest(request1);
        return JsonUtils.jsonToArray(jsonResponse2.getBody(), SavePhotoModel.class);
    }

    @SneakyThrows
    public static String sendCommentToPost(String message, String postId) {
        String request = UrlUtils.sendCommentUrl(postId, message);
        ResponseJsonModel jsonResponse = ApiUtils.sendPostRequest(request);
        CommentModel response = JsonUtils.jsonStringModelResponse(jsonResponse.getBody(), CommentModel.class);
        return response.getCommentId();
    }

    @SneakyThrows
    public static boolean isLikedPost(String postId, String userId) {
        String request = UrlUtils.isLikedUrl(userId, postId);
        ResponseJsonModel jsonResponse = ApiUtils.sendGetRequest(request);
        IsLikedModel response = JsonUtils.jsonStringModelResponse(jsonResponse.getBody(), IsLikedModel.class);
        return response.isLiked();
    }

    public static void deletePost(String postId) {
        String request = UrlUtils.deletePostUrl(postId);
        ApiUtils.sendPostRequest(request);
    }
}
