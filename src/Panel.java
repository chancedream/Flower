import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Panel {
	private int width;
	private int height;
	private Grid[][] grids;
	private LinkedList<Puttable> queue = new LinkedList<Puttable>();
	private int queueLength = 30;
	private List<ChangeListener> queueChangeListenerList = new ArrayList<ChangeListener>();
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Grid[][] getGrids() {
		return grids;
	}

	public void setGrids(Grid[][] grids) {
		this.grids = grids;
	}

	public LinkedList<Puttable> getQueue() {
		return queue;
	}

	public void setQueue(LinkedList<Puttable> queue) {
		this.queue = queue;
	}

	public int getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	public Panel(int width, int height) {
		this.width = width;
		this.height = height;
		grids = new Grid[height][];
		for (int i = 0; i < height; i++) {
			grids[i] = new Grid[width];
			for (int j = 0; j < width; j++) {
				grids[i][j] = new Grid(i, j, false, null);
			}
		}
	}
	
	public void addQueueChangeListener(ChangeListener listener) {
		queueChangeListenerList.add(listener);
	}
	
	public void fireQueueChange() {
		ChangeEvent event = new ChangeEvent(queue);
		for (ChangeListener listener : queueChangeListenerList) {
			listener.stateChanged(event);
		}
	}
}
