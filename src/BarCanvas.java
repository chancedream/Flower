import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class BarCanvas extends Canvas {
	private List<Puttable> queue;

	public BarCanvas(List<Puttable> queue) {
		this.queue = queue;
	}

	public void paint(Graphics g) {
		int delta = 0;
		for (int i = 0; i < 3; i++) {
			if (queue.size() > i) {
				Puttable puttable = queue.get(i);
				BufferedImage image = null;
				if (puttable instanceof Flower) {
					Flower flower = (Flower) puttable;
					image = FlowerImages.get(flower.getOuterColor(),
							flower.getInnerColor());
				} else if (puttable instanceof Dragonfly) {
					Dragonfly dragonfly = (Dragonfly) puttable;
					image = DragonflyImages.get(dragonfly.getColor());
				} else {
					image = DiggerImage.get();
				}
				g.drawImage(image, delta, 0, image.getWidth(),
						image.getHeight(), null);
				delta += image.getWidth();
			}
		}
	}
}
