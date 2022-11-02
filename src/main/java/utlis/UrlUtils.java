package utlis;


import lombok.SneakyThrows;
import org.apache.hc.core5.net.URIBuilder;

import static utlis.UrlParams.*;

public class UrlUtils {

    @SneakyThrows
    public static String sendPostUri(String http, String method, String ownerId, String message, String accessToken, String apiVersion) {
        return String.format("%s%s", http, new URIBuilder().setPath(method)
                .setParameter(OWNER_ID.getParams(), ownerId)
                .addParameter(MESSAGE.getParams(), message)
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(), apiVersion).build().toString());
    }

    @SneakyThrows
    public static String editPostWithAttachmentUri(String http, String method, String ownerId, String postId, String message, String sentPhotoID, String accessToken, String apiVersion) {
        return String.format("%s%s", http, new URIBuilder().setPath(method)
                .setParameter(OWNER_ID.getParams(), ownerId)
                .addParameter(POST_ID.getParams(), postId)
                .addParameter(MESSAGE.getParams(), message)
                .addParameter(ATTACHMENTS.getParams(),String.format("photo%s_%s", ownerId, sentPhotoID))
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(), apiVersion).build().toString());
    }

    @SneakyThrows
    public static String sendGetUrl(String http, String method, String ownerId, String accessToken, String apiVersion) {
        return String.format("%s%s", http,new URIBuilder().setPath(method)
                .setParameter(USER_ID.getParams(), ownerId)
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(), apiVersion).build().toString());
    }

    @SneakyThrows
    public static String savedPhotoUploadUrl(String http, String method, String ownerId, String encodedUrl, String uploadPhotoServer, String uploadPhotoHash, String accessToken, String apiVersion) {
        return String.format("%s%s", http, new URIBuilder().setPath(method)
                .setParameter(USER_ID.getParams(), ownerId)
                .setParameter(PHOTO.getParams(), encodedUrl)
                .addParameter(SERVER.getParams(), uploadPhotoServer)
                .addParameter(HASH.getParams(), uploadPhotoHash)
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(), apiVersion).build().toString());
    }


    @SneakyThrows
    public static String sendCommentUrl(String http, String method, String ownerId, String postId, String message, String accessToken, String apiVersion) {
        return String.format("%s%s", http, new URIBuilder().setPath(method)
                .setParameter(OWNER_ID.getParams(), ownerId)
                .addParameter(POST_ID.getParams(), postId)
                .addParameter(MESSAGE.getParams(), message)
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(), apiVersion).build().toString());
    }

    @SneakyThrows
    public static String isLikedUrl(String http, String method, String ownerId, String userId, String postId, String accessToken, String apiVersion) {
        return String.format("%s%s", http, new URIBuilder().setPath(method)
                .setParameter(OWNER_ID.getParams(), ownerId)
                .addParameter(USER_ID.getParams(), userId)
                .addParameter(TYPE.getParams(), POST.getParams())
                .addParameter(ITEM_ID.getParams(), postId)
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(), apiVersion).build().toString());
    }

    @SneakyThrows
    public static String deletePostUrl(String http, String method, String ownerId, String postId, String accessToken, String apiVersion) {
        return String.format("%s%s", http, new URIBuilder().setPath(method)
                .setParameter(OWNER_ID.getParams(), ownerId)
                .addParameter(POST_ID.getParams(), postId)
                .addParameter(ACCESS_TOKEN.getParams(), accessToken)
                .addParameter(VERSION.getParams(),apiVersion).build().toString());
    }
}
