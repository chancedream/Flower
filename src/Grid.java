import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Grid {
	private int row;
	private int col;
	private boolean disabled = false;
	private Flower flower;
	private List<ChangeListener> listenerList = new ArrayList<ChangeListener>();
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public Grid(int row,  int col, boolean disabled, Flower flower) {
		this.row = row;
		this.col = col;
		this.disabled = disabled;
		this.flower = flower;
	}
	
	public void put(Flower flower) {
		if (!disabled && this.flower == null)
			this.flower = flower;
	}
	
	public Flower remove() {
		Flower result = flower;
		this.flower = null;
		return result;
	}
	
	public void fade() {
		if (flower != null) {
			if (flower.getInnerColor() == flower.getOuterColor()) {
				flower = null;
			}
			else {
				flower.setOuterColor(flower.getInnerColor());
			}
		}
	}
	
	public boolean sameOuterColor(Grid grid) {
		return grid != null && grid.getFlower() != null && flower != null && flower.getOuterColor() == grid.getFlower().getOuterColor();
	}
	
	public void addChangeListener(ChangeListener listener) {
		listenerList.add(listener);
	}
	
	public void fireChange() {
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listenerList) {
			listener.stateChanged(event);
		}
	}
}
