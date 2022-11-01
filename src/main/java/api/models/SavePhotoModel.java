package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SavePhotoModel {
    @JsonProperty("album_id")
    private String albumId;
    private String date;
    private String id;
    @JsonProperty("owner_id")
    private String ownerId;
    @JsonProperty("has_tags")
    private String hasTags;
    @JsonProperty("access_key")
    private String accessKey;
    private String text;
    private String height;
    private String width;
    private List<Object> sizes;
}
