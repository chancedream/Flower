
public class Flower implements Puttable {
	private Color innerColor;
	private Color outerColor;

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public Color getOuterColor() {
		return outerColor;
	}

	public void setOuterColor(Color outerColor) {
		this.outerColor = outerColor;
	}
	
	public Flower(Color color) {
		innerColor = color;
		outerColor = color;
	}

	public Flower(Color innerColor, Color outerColor) {
		this.innerColor = innerColor;
		this.outerColor = outerColor;
	}
	
	@Override
	public Puttable put(Grid grid) {
		grid.put(this);
		return null;
	}

	@Override
	public boolean isPuttable(Grid grid) {
		if (grid.isDisabled() || grid.getFlower() != null)
			return false;
		return true;
	}

}
