package utlis;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageComparisonUtils {

    @SneakyThrows
    public static void savePhoto(String path) {
        URL url = new URL(path);
        ImageIO.write(ImageIO.read(url), "png", new File(ConfigUtils.getTestData("downloadPath")));
    }

    public static ImageComparisonResult runComparison() {
        return new ImageComparison(
                ImageComparisonUtil.readImageFromResources(ConfigUtils.getTestData("uploadImagePath")),
                ImageComparisonUtil.readImageFromResources(ConfigUtils.getTestData("downloadPath")))
                .compareImages();
    }
}