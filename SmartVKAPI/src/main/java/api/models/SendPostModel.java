package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class SendPostModel {
    private String postId;

    public SendPostModel(@JsonProperty(value = "post_id") String postId) {
        this.postId = postId;
    }
}
