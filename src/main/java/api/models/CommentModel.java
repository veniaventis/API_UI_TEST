package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentModel {
    @JsonProperty("comment_id")
    private String commentId;
    @JsonProperty("parents_stack")
    private String[] parentsStack;


    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setParentsStack(String[] parentsStack) {
        this.parentsStack = parentsStack;
    }
}
