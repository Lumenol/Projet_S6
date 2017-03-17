package mariaLost.items.model.animation;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public abstract class SpriteSheetLoader {

	/**
	 * Load frames from a sprite sheet.
	 * @param spriteSheet
	 * 					The image of the sprite sheet.
	 * @param nbRow
	 * 				The number of row of the sprite sheet.
	 * @param nbColumn
	 * 				The number of column of the sprite sheet.
	 * @param first
	 * 				The number of the first frame you want to load
	 * @param last
	 * 	  			The number of the last frame you want to load
	 * @return a List of Image containing the frame from "first" parameters to "last" parameters
	 *@pre The parameters can't be inferior or equal to 0
	 */
    public static List<Image> load(Image spriteSheet, int nbRow, int nbColumn, int first, int last) {
        if (nbRow < 1 || nbColumn < 1 || first < 1 || last < 1)
            throw new IllegalArgumentException("Row number can't be inferior or equal to 0.");
        if (last < first)
            throw new IllegalArgumentException("First frame number can't be inferior to last frame number");
            
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
    
    /**
     * Crop the image to remove the useless transparent pixels.
     * @param image
     * 				The image you want to clean.
     * @return an newly created WritableImage
     */
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
