import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GridChangeListener implements ChangeListener {
	private FlowerButton button;
	
	public GridChangeListener(FlowerButton button) {
		this.button = button;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() instanceof Grid) {
			Grid grid = (Grid)e.getSource();
			if (grid.getFlower() == null) {
				button.setIcon(null);
			}
			else {
				button.setIcon(new ImageIcon(FlowerImages.get(grid.getFlower().getOuterColor(), grid.getFlower().getInnerColor())));
			}
		}

	}

}
