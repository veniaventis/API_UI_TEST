package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.LinkedList;
import java.util.List;

public class PostForm extends Form {
    String id;
    String authorId;
    IButton btnPostLike;
    ILabel lblPostText;
    List<CommentForm> commentList = new LinkedList<>();

    public PostForm(String name,String id, String authorId) {
        super(By.xpath(String.format("//div[@id='page_wall_posts']//div[@data-post-id='%s_%s']",authorId,id)), name);
        this.id = id;
        this.authorId = authorId;
        btnPostLike = getElementFactory().getButton(By.xpath(String.format("//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[@data-section-ref='reactions-button']",authorId,id)),"Like button");
        lblPostText = getElementFactory().getLabel(By.xpath(String.format("//div[@id='page_wall_posts']/div[@data-post-id='%s_%s']//div[contains(@class,'wall_post_text')]",authorId,id)),"Post text");
    }

    public CommentForm newComment(String name, String id, String authorId) {
        CommentForm comment = new CommentForm(name,id,authorId,this.id,this.authorId);
        commentList.add(comment);
        return comment;
    }

    public String getId() {
        return id;
    }

    public String getPostText() {
        return lblPostText.getText();
    }

    public boolean isExist() {
        return this.state().waitForExist();
    }

    public void clickLikeBtn() {
        btnPostLike.click();
    }

    public boolean isNotDisplayed() {
        return this.state().waitForNotDisplayed();
    }
}
