package pageobject.pages;

import framework.driver.pageobject.BaseForm;
import framework.logger.LoggerUtils;
import org.openqa.selenium.By;
import pageobject.elements.TextField;
import framework.utils.KeyboardUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DatePickerForm extends BaseForm {

    private final TextField dateInput = new TextField(By.xpath("//input[@id='datePickerMonthYearInput']"), "Input:Date");
    private final TextField timeInput = new TextField(By.xpath("//input[@id='dateAndTimePickerInput']"), "Input:Time");

    public DatePickerForm() {
        super(By.xpath("//div[@id='datePickerContainer']"), "Form:DatePicker");
    }

    public String getDateFromPage() {
        LoggerUtils.info("get date from page");
        return dateInput.getValue();
    }

    public String getDateAndTimeFromPage() {
        LoggerUtils.info("get date and time from page");
        return timeInput.getValue().toLowerCase();
    }

    public void sendDateToInput(String date) {
        LoggerUtils.info("send date to " + dateInput.getName());
        dateInput.click();
        for (int i = 0; i < 10; i++) {
            KeyboardUtils.keyBackspace();
        }
        dateInput.sendText(date);
    }

}
