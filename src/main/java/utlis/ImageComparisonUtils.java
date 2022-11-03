package utlis;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import lombok.SneakyThrows;
import org.testng.internal.ConfigurationGroupMethods;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;

public class ImageComparisonUtils {
    private final static String IMAGE_EXTENSION = ConfigUtils.getTestData("imageExtension");

    private final static String IMAGE_PATH = ConfigUtils.getTestData("imagePath");
    private final static String DOWNLOAD_IMAGE_NAME = ConfigUtils.getTestData("downloadImageName");

    @SneakyThrows
    public static void savePhoto(String path) {
        URL url = new URL(path);
        ImageIO.write(ImageIO.read(url), IMAGE_EXTENSION, new File(String.format("%s%s", IMAGE_PATH,DOWNLOAD_IMAGE_NAME)));
    }

    public static ImageComparisonResult runComparison() {
        return new ImageComparison(
                ImageComparisonUtil.readImageFromResources(String.format("%s%s",ConfigUtils.getTestData("imagePath"),ConfigUtils.getTestData("uploadImageName"))),
                ImageComparisonUtil.readImageFromResources(String.format("%s%s",IMAGE_PATH,DOWNLOAD_IMAGE_NAME)))
                .compareImages();
    }
}