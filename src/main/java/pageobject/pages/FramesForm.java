package pageobject.pages;

import framework.logger.LoggerUtils;
import pageobject.elements.TextElement;
import framework.driver.pageobject.BaseForm;
import framework.utils.FrameUtils;
import org.openqa.selenium.By;

public class FramesForm extends BaseForm {

    private final By topIframe = By.xpath("//div[@id='frame1Wrapper']/iframe");
    private final By bottomIframe = By.xpath("//div[@id='frame2Wrapper']/iframe");
    private final TextElement frameText = new TextElement(By.xpath("//h1"), "FrameText");

    public FramesForm() {
        super(By.xpath("//div[@id='frame2Wrapper']"), "Form:Frames");
    }

    public String getTextFromTopIframe() {
        LoggerUtils.info("text getting from top iframe");
        FrameUtils.switchToIframe(topIframe);
        String text = frameText.getText();
        FrameUtils.backToDefaultContent();
        return text;
    }

    public String getTextFromBottomIframe() {
        LoggerUtils.info("text getting from bottom iframe");
        FrameUtils.switchToIframe(bottomIframe);
        String text = frameText.getText();
        FrameUtils.backToDefaultContent();
        return text;
    }

}