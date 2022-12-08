package pageobject.pages;

import framework.logger.LoggerUtils;
import pageobject.elements.TextElement;
import framework.driver.pageobject.BaseForm;
import framework.utils.FrameUtils;
import org.openqa.selenium.By;

public class NestedFramesForm extends BaseForm {

    private final By parentIframe = By.xpath("//iframe[@id='frame1']");
    private final By childIframe = By.xpath("//iframe");
    private final TextElement parentFrameText = new TextElement(By.xpath("//body"), "FrameText");
    private final TextElement childFrameText = new TextElement(By.xpath("//p"), "FrameText");

    public NestedFramesForm() {
        super(By.xpath("//div[@id='framesWrapper']"), "Form:NestedFrames");
    }

    public void switchToParentIframe() {
        FrameUtils.switchToIframe(parentIframe);
    }

    public String getTextFromParentIframe() {
        LoggerUtils.info("text getting from parent frame");
        return parentFrameText.getText();
    }

    public void switchToChildIframe() {
        FrameUtils.switchToIframe(childIframe);
    }

    public String getTextFromChildIframe() {
        LoggerUtils.info("text getting from child iframe");
        return childFrameText.getText();
    }

    public void backToDefaultContent() {
        LoggerUtils.info("backing to default content");
        FrameUtils.backToDefaultContent();
    }

}