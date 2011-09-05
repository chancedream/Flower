import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class DiggerImage {
	private static BufferedImage digger;
	static {
		try {
			digger = ImageIO.read(new File("res/scoop.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BufferedImage get() {
		return digger;
	}
}
