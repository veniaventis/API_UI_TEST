import api.ApiRequest;
import forms.AuthenticationPage;
import forms.CommentForm;
import forms.NavigationMenuForm;
import forms.PostForm;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.testng.Assert;
import utlis.ConfigUtils;

public class VkApiTest extends BaseTest{

    private AuthenticationPage authForm = new AuthenticationPage();
    private NavigationMenuForm navMenuForm = new NavigationMenuForm();
    private final int randomStringLength = 10;
    private String autogenMessage;
    private final String login = ConfigUtils.getConfidentialData("user_login");
    private final String password = ConfigUtils.getConfidentialData("user_password");
    private final String user_id = ConfigUtils.getConfidentialData("user_id");
    private final String photoFolderPath = ConfigUtils.getTestData("uploadPath");
    private final String uploadPhotoName = ConfigUtils.getTestData("uploadPath");
    @Test
    public void vkGuiApiWallPostTest(){
        Logger.getInstance().info("Authorization in vk.com");
        authForm.signIn(login,password);
        Logger.getInstance().info("Opening 'My page'");
        navMenuForm.clickMyPageBtn();
        Logger.getInstance().info("Sending request to create post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        String postId = ApiRequest.sendPostOnTheWall(autogenMessage);
        PostForm sentPost = new PostForm("API post",postId,user_id);
        Assert.assertEquals(sentPost.getPostText(),autogenMessage, "Posted text in GUI and sent text through API are not equal");
        Assert.assertTrue(sentPost.isExist(),String.format("Post %s from user %s doesn't exist",sentPost.getId(),user_id));
        Logger.getInstance().info("Sending request to edit post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        ApiRequest.editPostWithAttachment(autogenMessage,sentPost.getId(),photoFolderPath+uploadPhotoName);
        Assert.assertEquals(sentPost.getPostText(),autogenMessage,"Posted text in GUI and sent edited text through API are equal");
        browser.scrollWindowBy(0,500);
//        Assert.assertTrue(SikuliUtils.isExistPhoto(uploadPhotoName),"Post doesn't contain photo from previous step");
        Logger.getInstance().info("Sending request to leave a comment to post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        String commentId = ApiRequest.sendCommentToPost(autogenMessage,sentPost.getId());
        CommentForm sentComment = sentPost.newComment("API comment", commentId, user_id);
        Assert.assertTrue(sentComment.isDisplayed(), "Post "+sentComment.getId()+" from user "+user_id+" doesn't exist");
        Logger.getInstance().info("Clicking 'like' on the post");
        sentPost.clickLikeBtn();
        Assert.assertTrue(ApiRequest.isLikedPost(sentPost.getId(),user_id), "Post "+sentPost.getId()+" doesn't have 'like reaction' from user "+user_id);
        Logger.getInstance().info("Sending request to delete post on the wall");
        ApiRequest.deletePost(sentPost.getId());
        Assert.assertTrue(sentPost.isNotDisplayed(),"Post "+sentPost.getId()+" from user "+user_id+" still exist");
    }

}
