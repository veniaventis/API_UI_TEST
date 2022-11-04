package utlis;

public class StringUtil {
    private static final String PROTOCOL = ConfigUtils.getTestData("protocol");

    public static String substringIndexOff(String link) {
        return link.substring(link.indexOf(PROTOCOL));
    }
}
