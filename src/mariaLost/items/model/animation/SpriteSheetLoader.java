package mariaLost.items.model.animation;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public abstract class SpriteSheetLoader {

    // return a List of frame from a sprite sheet
    public static List<Image> load(String spriteSheetPath, int nbRow, int nbColumn, int first, int last) {
        return load(new Image(spriteSheetPath), nbRow, nbColumn, first, last);
    }

    // return a List of frame from a sprite sheet
    public static List<Image> load(Image spriteSheet, int nbRow, int nbColumn, int first, int last) {
        if (nbRow < 1 || nbColumn < 1 || first < 1 || last < 1) {
            throw new IllegalArgumentException("Le nombre de ligne ne peut être négatif ou null.");
        }
        if (last < first) {
            throw new IllegalArgumentException("Le numéro de la première frame ne peut être inférieur à celui de la dernière.");
        }

        int frameHeight = (int) spriteSheet.getHeight() / nbRow;
        int frameWidth = (int) spriteSheet.getWidth() / nbColumn;
        List<Image> list = new LinkedList<Image>();

        for (int i = first; i <= last; i++) {
            int upperLeftX = ((i - 1) % nbColumn) * frameWidth;
            int upperLeftY = ((i - 1) / nbColumn) * frameHeight;
            PixelReader pixelReader = spriteSheet.getPixelReader();
            WritableImage frame = new WritableImage(pixelReader, upperLeftX, upperLeftY, frameWidth, frameHeight);
            list.add(cleanImage(frame));
        }
        return list;
    }

    // return an image with useless transparant border removed
    private static WritableImage cleanImage(WritableImage image) {
        PixelReader pixelReader = image.getPixelReader();
        int upperLeftX = (int) image.getWidth();
        int upperLeftY = 0;
        int lowerRightX = 0;
        int lowerRightY = 0;
        Color color;
        double opacity;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                color = pixelReader.getColor(x, y);
                opacity = color.getOpacity();
                if (opacity != 0) {
                    //upperLeftX
                    if (x < upperLeftX) {
                        upperLeftX = x;
                    }
                    //lowerRightX
                    if (x > lowerRightX) {
                        lowerRightX = x;
                    }
                    //lowerRightY
                    if (y > lowerRightY) {
                        lowerRightY = y;
                    }
                    // upperLeftY is the y of the first pixel with opacity !=0
                    if (upperLeftY == 0) {
                        upperLeftY = y;
                    }
                }
            }
        }
        int imageWidth = lowerRightX - upperLeftX;
        int imageHeight = lowerRightY - upperLeftY;
        return new WritableImage(pixelReader, upperLeftX, upperLeftY, imageWidth, imageHeight);
    }

}
