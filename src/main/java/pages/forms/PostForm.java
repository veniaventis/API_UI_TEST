package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utlis.StringUtil;

import java.util.LinkedList;
import java.util.List;

public class PostForm extends Form {
    protected String id;
    protected String authorId;

    private final IButton btnPostLike;
    private final ILabel lblPostText;
    private static final String BTN_POST_LIKE_LOC = "//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[@data-section-ref='reactions-button']";
    private static final String LBL_POST_TEXT_LOC = "//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[contains(@class,'wall_post_text')]";
    private static final String SAVE_LINK = "//div[contains(@id,'%s') and contains(@class,'wall_post_cont _wall_post_con')]//child::a";

    public final List<CommentForm> commentList = new LinkedList<>();

    public PostForm(String name, String id, String authorId) {
        super(By.xpath(String.format("//div[@id='page_wall_posts']//child::div[@data-post-id='%s_%s']", authorId, id)), name);
        this.id = id;
        this.authorId = authorId;
        this.btnPostLike = getElementFactory().getButton(By.xpath(String.format(BTN_POST_LIKE_LOC, authorId, id)), "Like button");
        this.lblPostText = getElementFactory().getLabel(By.xpath(String.format(LBL_POST_TEXT_LOC, authorId, id)), "Post text");

    }

    public CommentForm newComment(String name, String id, String authorId) {
        CommentForm comment = new CommentForm(name, id, authorId, this.id, this.authorId);
        commentList.add(comment);
        return comment;
    }

    public String getPostText() {
        return lblPostText.getText();
    }

    public String getPhotoLink(String attribute) {
        String link = getElementFactory().getLink(By.xpath(String.format(SAVE_LINK, id)), "Get Link").getAttribute(attribute);
        return StringUtil.substringIndexOff(link);
    }

    public void clickLikeBtn() {
        btnPostLike.click();
    }
}
