import edu.duke.*;
import java.io.*;
/**
 * Write a description of ImageInverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageInverter {
    public ImageResource invert(ImageResource inImage){
        ImageResource inverted = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : inverted.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setBlue(255 - inPixel.getBlue());
            pixel.setGreen(255 - inPixel.getGreen());
        }
        return inverted;
    }
    public void ConvertAndSave() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource image = new ImageResource(f);
			ImageResource negative = invert(image);
			String fname = image.getFileName();
			String newName = "negative-" + fname;
			negative.setFileName(newName);
			negative.draw();
			negative.save();
		}
	}
}
