
public class Digger implements Puttable {

	@Override
	public Puttable put(Grid grid) {
		return grid.remove();
	}

	@Override
	public boolean isPuttable(Grid grid) {
		if (grid.isDisabled() || grid.getFlower() == null)
			return false;
		return true;
	}
	

}
