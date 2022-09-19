package framework.utils;

import framework.logger.LoggerUtils;

public final class Parser {

    private Parser() {}

    public static int extractNumbersFromText(String text) {
        LoggerUtils.info("extracting numbers from text: " + text);
        String replaced = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(replaced);
    }

}