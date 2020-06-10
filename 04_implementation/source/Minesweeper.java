package minesweeper;

public class Minesweeper {

	private int mineTotal;

	private static int cellsNum;

	private Board board;

	public Minesweeper(int mineTotal, int cellsNum) {
		this.mineTotal = mineTotal;
		this.cellsNum = cellsNum;
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

}

