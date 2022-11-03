package utlis;


public enum UrlParams {
    OWNER__ID("owner_id"),
    ACCESS__TOKEN("access_token"),
    VERSION("v"),
    USER_ID("user_id"),
    POST_ID("post_id"),
    ITEM_ID("item_id"),
    MESSAGE("message"),
    ATTACHMENTS("attachments"),
    PHOTO("photo"),
    SERVER("server"),
    HASH("hash"),
    TYPE("type"),
    POST("post");

    private final String params;

    UrlParams(String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }
}
