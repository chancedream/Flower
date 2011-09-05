import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class DragonflyImages {
	private static Map<Color, BufferedImage> map;
	
	static {
		map = new HashMap<Color, BufferedImage>();
		for (Color color : Color.values()) {
			try {
				BufferedImage image = ImageIO.read(new File(String.format("res/%sd.png", color.toString().toLowerCase())));
				map.put(color, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static BufferedImage get(Color color) {
		return map.get(color);
	}
}
