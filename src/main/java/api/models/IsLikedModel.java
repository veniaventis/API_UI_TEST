package api.models;

import lombok.Getter;

@Getter
public class IsLikedModel {
    private boolean liked;
    private boolean copied;

    public void setLiked(int liked) {
        this.liked = liked == 1;
    }

    public void setCopied(int copied) {
        this.copied = copied == 1;
    }
}
