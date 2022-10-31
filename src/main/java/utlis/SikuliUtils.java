package utlis;

import aquality.selenium.core.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SikuliUtils {
    private static Screen screen;
    private static final double PERCENT = 0.6;

    public static boolean isExistPhoto(String photoName) {
        Logger.getInstance().debug("Finding photo on the screen");
        screen = new Screen();
        ImagePath.add(ConfigUtils.getConfidentialData("uploadPath"));
        Match exists = screen.exists(new Pattern(photoName).similar(PERCENT));
        if (exists != null) {
            saveBufferedImageToFile(exists.getImage().get(), "png");
            return true;
        }
        Logger.getInstance().debug("Photo wasn't found on the screen");
        return false;
    }

    public static void saveBufferedImageToFile(BufferedImage image, String format) {
        try {
            Logger.getInstance().debug(String.format("Saving buffered image to .%s", format));
            ImageIO.write(image, format, new File(String.format("target/screeshots/checked_photo.%s", format)));
        } catch (IOException e) {
            Logger.getInstance().error("Image cannot be saved");
            e.printStackTrace();
        }
    }
}
