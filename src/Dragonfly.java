
public class Dragonfly implements Puttable {
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public Dragonfly(Color color) {
		this.color = color;
	}

	@Override
	public Puttable put(Grid grid) {
		Flower flower = grid.getFlower();
		if (flower != null) {
			flower.setInnerColor(color);
			flower.setOuterColor(color);
		}
		return null;
	}

	@Override
	public boolean isPuttable(Grid grid) {
		if (grid.isDisabled() || grid.getFlower() == null)
			return false;
		return true;
	}

}
