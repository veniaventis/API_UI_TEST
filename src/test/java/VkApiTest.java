import api.ApiRequest;
import api.models.SendPostModel;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.apache.http.HttpStatus;
import pages.AuthenticationPage;
import pages.MyPage;
import pages.forms.CommentForm;
import pages.forms.PostForm;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FeedsPage;
import pages.MainPage;
import utlis.ConfigUtils;
import utlis.ImageComparisonUtils;

import static api.ApiRequest.RESPONSE_JSON;

public class VkApiTest extends BaseTest {
    private final int randomStringLength = Integer.parseInt(ConfigUtils.getTestData("randomStringLength"));
    private final String attribute = ConfigUtils.getTestData("attribute");
    private String autogenMessage;
    private final AuthenticationPage authenticationPage = new AuthenticationPage();
    private final MainPage mainPage = new MainPage();
    private final FeedsPage feedsPage = new FeedsPage();
    private final MyPage myPage = new MyPage();
    private final String login = ConfigUtils.getConfidentialData("userLogin");
    private final String password = ConfigUtils.getConfidentialData("password");
    private final String userId = ConfigUtils.getConfidentialData("userID");
    private final String photoFolderPath = ConfigUtils.getTestData("imagePath");
    private final String imageName = ConfigUtils.getTestData("uploadImageName");

    @Test
    public void vkGuiApiWallPostTest() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Main Page doesn't open");

        mainPage.getLoginForm().signIn(login);
        Assert.assertTrue(authenticationPage.state().waitForDisplayed(), "Authentication Page hasn't been loaded");

        authenticationPage.getPasswordForm().signIn(password);
        Assert.assertTrue(feedsPage.state().waitForDisplayed(),"Feed Page hasn't been loaded");

        Logger.getInstance().info("Opening 'My page'");
        feedsPage.getNavigationMenuForm().clickMyPageBtn();

        Logger.getInstance().info("Sending request to create post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        SendPostModel sendPostModel = ApiRequest.sendPostOnTheWall(autogenMessage);
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));

        PostForm sentPost = myPage.getPostForm("API post", sendPostModel.getPostId(), userId);
        Assert.assertEquals(sentPost.getPostText(), autogenMessage, "Posted text in GUI and sent text through API are not equal");
        Assert.assertTrue(sentPost.state().waitForExist(), String.format("Post %s from user %s doesn't exist", sendPostModel.getPostId(), userId));

        Logger.getInstance().info("Sending request to edit post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        ApiRequest.editPostWithAttachment(autogenMessage, sendPostModel.getPostId(),String.format("%s%s",photoFolderPath,imageName));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));
        ImageComparisonUtils.savePhoto(sentPost.getPhotoLink(attribute));
        Assert.assertEquals(sentPost.getPostText(), autogenMessage, "Posted text in GUI and sent edited text through API are equal");
        Assert.assertEquals(ImageComparisonState.MATCH, ImageComparisonUtils.runComparison().getImageComparisonState(), "Post doesn't contain photo from previous step");

        Logger.getInstance().info("Sending request to leave a comment to post on the wall");
        autogenMessage = RandomStringUtils.randomAlphanumeric(randomStringLength);
        String commentId = ApiRequest.sendCommentToPost(autogenMessage, sendPostModel.getPostId());
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));

        CommentForm sentComment = sentPost.newComment("API comment", commentId, userId);
        Assert.assertTrue(sentComment.isDisplayed(), String.format("Post %s from user %s doesn't exist", commentId, userId));

        sentPost.clickLikeBtn();
        Assert.assertTrue(ApiRequest.isLikedPost(sendPostModel.getPostId(), userId), String.format("Post %s doesn't have 'like reaction' from user %s", sendPostModel.getPostId(), userId));
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));

        Logger.getInstance().info("Sending request to delete post on the wall");
        ApiRequest.deletePost(sendPostModel.getPostId());
        Assert.assertEquals(RESPONSE_JSON.getStatusCode(), HttpStatus.SC_OK,
                String.format("status code is not %d status is:%d", HttpStatus.SC_OK, RESPONSE_JSON.getStatusCode()));
        Assert.assertTrue(sentPost.state().waitForNotExist(), String.format("Post %s from user %s still exist", sendPostModel.getPostId(), userId));
    }
}
