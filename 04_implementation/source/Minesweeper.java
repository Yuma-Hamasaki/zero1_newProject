package Minesweeper;

public class Minesweeper {

	private int mineTotal;

	private int cellsNum;

	private Board board;
	private long time;

	public Minesweeper(int mineTotal, int cellsNum) {
		this.mineTotal = mineTotal;
		this.cellsNum = cellsNum;
		this.board = new Board(this.cellsNum);
	}

	public void setAllMine(int x, int y) {
		board.setMine(x, y, mineTotal);
	}

	public boolean removeCover(int x, int y) {
		return board.removeCover(x, y);
	}

	public Cell[][] getCellList() {
		return board.getCellList();
	}
	
	public int getMineTotal() {
		return this.mineTotal;
	}
	
	public void startTime() {
		time = System.currentTimeMillis();
	}
	
	public String getTime() {
		Long currentTime = System.currentTimeMillis();
		long diffMillis = currentTime - time;
		int diff = (int)(diffMillis/1000);
		int Min = diff/60;
		int Sec = diff%60;
		return Min + " : " + Sec;
	}

}

