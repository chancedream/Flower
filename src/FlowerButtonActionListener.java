import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FlowerButtonActionListener implements ActionListener {
	private PanelController panelController;
	
	public FlowerButtonActionListener(PanelController controller) {
		this.panelController = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof FlowerButton) {
			FlowerButton button = (FlowerButton)event.getSource();
			panelController.put(button.getRow(), button.getCol());
		}
	}

}
