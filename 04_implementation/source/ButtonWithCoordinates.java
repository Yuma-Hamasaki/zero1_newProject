package Minesweeper;



import java.awt.Color;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class ButtonWithCoordinates extends JButton  {
	private int x;
	private int y;
	private boolean flag;

	public ButtonWithCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getCoordX() {
		return x;
	}

	public int getCoordY() {
		return y;
	}

	public void setFlag(boolean h) {
		this.flag = h;
	}

	public boolean getFlag() {
		return this.flag;
	}


	public void setEnabled(boolean b,Color color) {

		//if (!b && model.isRollover()) {

		//	model.setRollover(false);

		//}

		//boolean oldEnabled = isEnabled();

		// super.setEnabled(b);

		this.setForeground(color);
		this.setBackground(Color.WHITE);

		//firePropertyChange("enabled", oldEnabled, b);

		//if (b != oldEnabled) {

		repaint();
		//	SwingUtilities.updateComponentTreeUI(this);
		//}

		model.setEnabled(b);

	}

}
