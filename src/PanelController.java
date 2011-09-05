import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class PanelController {
	private Panel panel;
	private Puttable movingFlower = null;
	private Set<Grid> needUpdateGrids = new HashSet<Grid>();

	public PanelController(Panel panel) {
		this.panel = panel;
	}
	
	public void generateQueue(Difficulty difficulty) {
		Color[] colors = null;
		boolean mix = false;
		switch (difficulty) {
		case EASY:
			colors = new Color[] {Color.WHITE, Color.BLUE, Color.RED};
			mix = false;
			break;
		case NORMAL:
			colors = new Color[] {Color.WHITE, Color.BLUE, Color.RED};
			mix = true;
			break;
		case HARD:
			colors = Color.values();
			mix = true;
			break;
		}
		Random random = new Random();
		for (int i = 0; i < panel.getQueueLength() - 2; i++) {
			Flower flower;
			if (mix) {
				flower = new Flower(colors[random.nextInt(colors.length)], colors[random.nextInt(colors.length)]);
			}
			else {
				flower = new Flower(colors[random.nextInt(colors.length)]);
			}
			panel.getQueue().add(flower);
		}
		panel.getQueue().add(random.nextInt(panel.getQueueLength()-1), new Dragonfly(colors[random.nextInt(colors.length)]));
		panel.getQueue().add(random.nextInt(panel.getQueueLength()), new Digger());
	}
	
	public void disable(int row, int col) {
		if (isValidGrid(row, col)) {
			panel.getGrids()[row][col].setDisabled(true);
		}
	}
	
	public boolean isValidGrid(int row, int col) {
		return row < panel.getHeight() && col < panel.getWidth() && row >= 0 && col >= 0;
	}
	
	public void put(int row, int col) {
		if (isValidGrid(row, col)) {
			Grid grid = panel.getGrids()[row][col];
			Puttable puttable = null;
			if (movingFlower != null) {
				puttable = movingFlower;
				if (!puttable.isPuttable(grid)) {
					return;
				}
				movingFlower = null;
			}
			else {
				if (panel.getQueue().isEmpty()) {
					return;
				}
				puttable = panel.getQueue().getFirst();
				if (puttable.isPuttable(grid)) {
					panel.getQueue().poll();
				}
				else {
					return;
				}
			}
			Puttable retVal = puttable.put(grid);
			if (retVal != null) {
				movingFlower = retVal;
			}
			needUpdateGrids.add(grid);
			grid.fireChange();
			panel.fireQueueChange();
			checkChange(grid);
		}
	}
	
	private void checkChange(Grid grid) {
		List<Grid> fadeRow = new ArrayList<Grid>();
		int row = grid.getRow();
		Grid current;
		while (--row >= 0) {
			current = panel.getGrids()[row][grid.getCol()];
			if (current.sameOuterColor(grid)) {
				fadeRow.add(current);
			}
			else {
				break;
			}
		}
		row = grid.getRow();
		while (++row < panel.getHeight()) {
			current = panel.getGrids()[row][grid.getCol()];
			if (current.sameOuterColor(grid)) {
				fadeRow.add(current);
			}
			else {
				break;
			}
		}
		if (fadeRow.size() < 2) {
			fadeRow.clear();
		}
		List<Grid> fadeCol = new ArrayList<Grid>();
		int col = grid.getCol();
		while (--col >= 0) {
			current = panel.getGrids()[grid.getRow()][col];
			if (current.sameOuterColor(grid)) {
				fadeCol.add(current);
			}
			else {
				break;
			}
		}
		col = grid.getCol();
		while (++col < panel.getWidth()) {
			current = panel.getGrids()[grid.getRow()][col];
			if (current.sameOuterColor(grid)) {
				fadeCol.add(current);
			}
			else {
				break;
			}
		}
		if (fadeCol.size() < 2) {
			fadeCol.clear();
		}
		List<Grid> fadeGrids = fadeRow;
		fadeGrids.addAll(fadeCol);
		if (!fadeGrids.isEmpty()) {
			fadeGrids.add(grid);
			for (Grid fadeGrid : fadeGrids) {
				fadeGrid.fade();
				needUpdateGrids.add(fadeGrid);
				fadeGrid.fireChange();
			}
			for (Grid fadeGrid : fadeGrids) {
				checkChange(fadeGrid);
			}
		}
	}
	
}
