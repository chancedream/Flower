import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class FlowerImages {
	private static Map<Color, Map<Color, BufferedImage>> map;
	static {
		map = new HashMap<Color, Map<Color, BufferedImage>>();
		for (Color outerColor : Color.values()) {
			Map<Color, BufferedImage> subMap = new HashMap<Color, BufferedImage>();
			map.put(outerColor, subMap);
			for (Color innerColor : Color.values()) {
				try {
					File file = new File(String.format("res/%sf.png", outerColor.toString().toLowerCase()));
					BufferedImage image = ImageIO.read(file);
					Graphics2D graphics = image.createGraphics();
					BufferedImage innerImage = ImageIO.read(new File(String.format("res/%s_s2.png", innerColor.toString().toLowerCase().charAt(0))));
					graphics.drawImage(innerImage, 0, 0 ,image.getWidth(), image.getHeight(), null);
					graphics.dispose();
					subMap.put(innerColor, image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static BufferedImage get(Color outerColor, Color innerColor) {
		return map.get(outerColor).get(innerColor);
	}
}
