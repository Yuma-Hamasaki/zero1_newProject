package Minesweeper;



import javax.swing.JButton;

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


}
