package utlis;


import api.Methods;
import lombok.SneakyThrows;
import org.apache.hc.core5.net.URIBuilder;

import static utlis.UrlParams.*;

public class UrlUtils {
    public static final String BASE_URL = ConfigUtils.getSettingsData("urlApi");
    public static final String OWNER_ID = ConfigUtils.getConfidentialData("ownerId");
    public static final String ACCESS_TOKEN = ConfigUtils.getConfidentialData("accessToken");
    public static final String API_VERSION = ConfigUtils.getTestData("apiVersion");

    public static String getOwnerId() {
        return OWNER_ID;
    }


    public static String setUrl(String method, String params) {
        return String.format("%s%s%s",BASE_URL, method,params);
    }
    @SneakyThrows
    public static String sendPostUri( String message) {
        return setUrl(Methods.SEND_POST.getMethod(),
                new URIBuilder().setParameter(OWNER__ID.getParams(), OWNER_ID)
                .addParameter(MESSAGE.getParams(), message)
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(), API_VERSION).build().toString());
    }

    @SneakyThrows
    public static String editPostWithAttachmentUri(String postId, String message, String sentPhotoID) {
        return setUrl(Methods.EDIT_POST.getMethod(),
                new URIBuilder().setParameter(OWNER__ID.getParams(), OWNER_ID)
                .addParameter(POST_ID.getParams(), postId)
                .addParameter(MESSAGE.getParams(), message)
                .addParameter(ATTACHMENTS.getParams(),String.format("photo%s_%s", OWNER_ID, sentPhotoID))
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(), API_VERSION).build().toString());
    }

    @SneakyThrows
    public static String sendGetPhotoUploadUrl(String ownerId) {
        return setUrl( Methods.PHOTO_UPLOAD_SERVER.getMethod(), new URIBuilder().setParameter(USER_ID.getParams(), ownerId)
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(), API_VERSION).build().toString());
    }

    @SneakyThrows
    public static String savedPhotoUploadUrl(String encodedUrl, String uploadPhotoServer, String uploadPhotoHash) {
        return setUrl(Methods.SAVE_WALL_PHOTO.getMethod(),new URIBuilder()
                .setParameter(USER_ID.getParams(), OWNER_ID)
                .setParameter(PHOTO.getParams(), encodedUrl)
                .addParameter(SERVER.getParams(), uploadPhotoServer)
                .addParameter(HASH.getParams(), uploadPhotoHash)
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(), API_VERSION).build().toString());
    }


    @SneakyThrows
    public static String sendCommentUrl(String postId, String message) {
        return setUrl(Methods.WALL_CREATE_COMMENT.getMethod(), new URIBuilder().setParameter(OWNER__ID.getParams(), OWNER_ID)
                .addParameter(POST_ID.getParams(), postId)
                .addParameter(MESSAGE.getParams(), message)
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(), API_VERSION).build().toString());
    }

    @SneakyThrows
    public static String isLikedUrl(String userId ,String postId) {
        return setUrl(Methods.IS_LIKED.getMethod(), new URIBuilder().setParameter(OWNER__ID.getParams(), OWNER_ID)
                .addParameter(USER_ID.getParams(), userId)
                .addParameter(TYPE.getParams(), POST.getParams())
                .addParameter(ITEM_ID.getParams(), postId)
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(), API_VERSION).build().toString());
    }

    @SneakyThrows
    public static String deletePostUrl(String postId) {
        return setUrl(Methods.DELETE_POST.getMethod(),new URIBuilder().setParameter(OWNER__ID.getParams(), OWNER_ID)
                .addParameter(POST_ID.getParams(), postId)
                .addParameter(ACCESS__TOKEN.getParams(), ACCESS_TOKEN)
                .addParameter(VERSION.getParams(),API_VERSION).build().toString());
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }
}
