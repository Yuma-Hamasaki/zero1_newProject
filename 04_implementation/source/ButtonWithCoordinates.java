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
		this.setForeground(color);
		repaint();
		model.setEnabled(b);

	}

}
