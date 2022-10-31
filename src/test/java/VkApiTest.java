import api.ApiRequest;
import forms.AuthenticationPage;
import forms.CommentForm;
import forms.NavigationMenuForm;
import forms.PostForm;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import utlis.ConfigUtils;
import utlis.SikuliUtils;

public class VkApiTest extends BaseTest {
    private final int randomStringLength = 10;
    private String autogenMessage;
    private final AuthenticationPage authForm = new AuthenticationPage();
    private final NavigationMenuForm navMenuForm = new NavigationMenuForm();
    private final String login = ConfigUtils.getConfidentialData("userLogin");
    private final String password = ConfigUtils.getConfidentialData("password");
    private final String userId = ConfigUtils.getConfidentialData("userID");
    private final String photoFolderPath = ConfigUtils.getTestData("uploadPath");
    private final String uploadPhotoName = ConfigUtils.getTestData("uploadPhotoName");
    @Test
    public void vkGuiApiWallPostTest() {
        Logger.getInstance().info("Authorization in vk.com");
        authForm.signIn(login,password);
        Logger.getInstance().info("Opening 'My page'");
        navMenuForm.clickMyPageBtn();
        Logger.getInstance().info("Sending request to create post on the wall");
        String postId = ApiRequest.sendPostOnTheWall(autogenMessage);
        PostForm sentPost = new PostForm("API post",postId, userId);
        Assert.assertEquals(sentPost.getPostText(), autogenMessage, "Posted text in GUI and sent text through API are not equal");
        Assert.assertTrue(sentPost.isExist(),String.format("Post %s from user %s doesn't exist",sentPost.getId(), userId));
        Logger.getInstance().info("Sending request to edit post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        ApiRequest.editPostWithAttachment(autogenMessage,sentPost.getId(),String.format("%s%s",photoFolderPath, uploadPhotoName));
        Assert.assertEquals(sentPost.getPostText(), autogenMessage,"Posted text in GUI and sent edited text through API are equal");
        Assert.assertTrue(SikuliUtils.isExistPhoto(uploadPhotoName),"Post doesn't contain photo from previous step");
        Logger.getInstance().info("Sending request to leave a comment to post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        String commentId = ApiRequest.sendCommentToPost(autogenMessage,sentPost.getId());
        CommentForm sentComment = sentPost.newComment("API comment", commentId, userId);
        Assert.assertTrue(sentComment.isDisplayed(), String.format("Post %s from user %s doesn't exist", sentComment.getId(), userId));
        Logger.getInstance().info("Clicking 'like' on the post");
        sentPost.clickLikeBtn();
        Assert.assertTrue(ApiRequest.isLikedPost(sentPost.getId(), userId), String.format("Post %s doesn't have 'like reaction' from user %s", sentPost.getId(), userId));
        Logger.getInstance().info("Sending request to delete post on the wall");
        ApiRequest.deletePost(sentPost.getId());
        Assert.assertTrue(sentPost.isNotDisplayed(),String.format("Post %s from user %s still exist",sentPost.getId(), userId));
    }
}
