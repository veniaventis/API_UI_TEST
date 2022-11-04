package pages.forms;

import aquality.selenium.core.elements.Element;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.LinkedList;
import java.util.List;

public class PostForm extends Form {
    private String id;
    private String authorId;

    private final IButton btnPostLike;
    private final ILabel lblPostText;
//    private  IButton  btnPostLike = getElementFactory().getButton(By.xpath(String.format("//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[@data-section-ref='reactions-button']", this.authorId, this.id)), "Like button");
//      ILabel  lblPostText = getElementFactory().getLabel(By.xpath(String.format("//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[contains(@class,'wall_post_text')]", this.authorId, this.id)), "Post text");

    private final List<CommentForm> commentList = new LinkedList<>();

    public PostForm(String name, String id, String authorId) {
        super(By.xpath(String.format("//div[@id='page_wall_posts']//child::div[@data-post-id='%s_%s']", authorId, id)), name);
        this.id = id;
        this.authorId = authorId;
        this.btnPostLike = getElementFactory().getButton(By.xpath(String.format("//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[@data-section-ref='reactions-button']", authorId, id)), "Like button");
        this.lblPostText = getElementFactory().getLabel(By.xpath(String.format("//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[contains(@class,'wall_post_text')]", authorId, id)), "Post text");

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
        String link = getElementFactory().getLink(By.xpath(String.format("//div[contains(@id,'%s') and contains(@class,'wall_post_cont _wall_post_con')]//child::a", id)), "Photo Link").getAttribute(attribute);
        return link.substring(link.indexOf("https"));
    }
    public void clickLikeBtn() {
        btnPostLike.click();
    }

    public boolean isNotDisplayed() {
        return this.state().waitForNotDisplayed();
    }
}
