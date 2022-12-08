package pageobject.pages;

import framework.driver.pageobject.BaseForm;
import framework.logger.LoggerUtils;
import pageobject.elements.TextElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import pageobject.elements.Button;

public class AlertsForm extends BaseForm {

    private final Button toSeeAlertButton = new Button(By.xpath("//button[@id='alertButton']"), "Button:toSeeAlert");
    private final Button timeAlertButton = new Button(By.xpath("//button[@id='timerAlertButton']"),"Button:timeAlertButton");
    private final Button confirmBoxButton = new Button(By.xpath("//button[@id='confirmButton']"), "Button:toSeeConfirm");
    private final Button promptBoxButton = new Button(By.xpath("//button[@id='promtButton']"), "Button:toSeePrompt");
    private final TextElement confirmResult = new TextElement(By.xpath("//span[@id='confirmResult']"), "ConfirmResult");
    private final TextElement promptResult = new TextElement(By.xpath("//span[@id='promptResult']"), "PromptResult");

    public AlertsForm() {
        super(By.xpath("//div[@id='javascriptAlertsWrapper']"), "Form:Alerts");
    }

    public void clickOnToSeeAlertButton() {
        toSeeAlertButton.click();
    }
    public void clickOnTimeAlertButton(){timeAlertButton.click();}

    public void clickOnConfirmButton() {
        confirmBoxButton.click();
    }

    public void clickOnPromptButton() {
        promptBoxButton.click();
    }

    public boolean isAppearConfirmResult(String text) {
        return confirmResult.isTextToBePresentInElement(text);
    }

    public boolean isAppearPromptResult(String text) {
        return promptResult.isTextToBePresentInElement("You entered " + text);
    }

    public String getAlertText() {
        LoggerUtils.info("get alert text");
        return waitForAlert().getText();
    }

    public void acceptOpenedAlert() {
        LoggerUtils.info("alert accepting");
        waitForAlert().accept();
    }

    public void sendKeysAndAcceptPrompt(String text) {
        LoggerUtils.info("prompt sending '" + text + "' and accepting");
        Alert alert = waitForAlert();
        alert.sendKeys(text);
        alert.accept();
    }

}