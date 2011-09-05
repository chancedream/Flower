import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class QueueChangeListener implements ChangeListener {
	private BarCanvas barCanvas;
	
	public QueueChangeListener(BarCanvas barCanvas) {
		this.barCanvas = barCanvas;
	}

	@Override
	public void stateChanged(ChangeEvent event) {
		barCanvas.repaint();
	}

}
