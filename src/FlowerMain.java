import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


public class FlowerMain {
	public static final int row = 11;
	public static final int col = 11;

	public static void main(String[] args) {
		Panel panel = new Panel(col, row);
		PanelController panelController = new PanelController(panel);
		panelController.generateQueue(Difficulty.HARD);
		FlowerButtonActionListener listener = new FlowerButtonActionListener(panelController);
		FlowerFrame frame = new FlowerFrame();
		Container container = frame.getContentPane();
		BarCanvas barCanvas = new BarCanvas(panel.getQueue());
		barCanvas.setBounds(0, 0, FlowerFrame.WIDTH, 70);
		QueueChangeListener queueChangeListener = new QueueChangeListener(barCanvas);
		panel.addQueueChangeListener(queueChangeListener);
		panel.fireQueueChange();
		FlowerPanel flowerPanel = new FlowerPanel();
		flowerPanel.setLayout(new GridLayout(col,row));
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				FlowerButton button = new FlowerButton(i,j);
				button.addActionListener(listener);
				flowerPanel.add(button);
				panel.getGrids()[i][j].addChangeListener(new GridChangeListener(button));
			}
		}
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, barCanvas, flowerPanel);
		container.add(jsp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}
