package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class ImageUtils {

    public static Image convert(
            BufferedImage image) {

        return SwingFXUtils.toFXImage(
                image,
                null);
    }
}