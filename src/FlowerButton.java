import javax.swing.JButton;


public class FlowerButton extends JButton {
	private int row;
	private int col;
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public FlowerButton(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}
