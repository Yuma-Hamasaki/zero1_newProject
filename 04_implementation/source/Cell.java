package Minesweeper;

public class Cell {
	private static int openedNum;
	private int mineNum;
	private boolean hidden ;
	private boolean mine;
	
	Cell(){
		this.hidden = true;
		
	}
	
	public static int getOpenedNum() {
		return openedNum;
	}
	
	public int getMineNum() {
		return this.mineNum;
	}
	
	public void addMineNum() {
		this.mineNum ++;
	}
	
	public void setMine() {
		this.mine = true;
	}
	
	public boolean hasMine() {
		return this.mine;
	}
	
	public void open() {
		this.hidden = false;
	}
	public boolean isHidden() {
		return this.hidden;
	}
	
}
