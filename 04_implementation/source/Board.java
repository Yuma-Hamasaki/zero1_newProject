package Minesweeper;

import java.util.Random;


public class Board {
	private Cell cells[][];
	private int size;
	
	private int[][] neighbor =
		{
			{-1, 1},	{0, 1},		{1, 1},
			{-1, 0},				{1, 0},
			{-1, -1},	{0, -1},	{1, -1}
		};

	
	public Board(int size){
		this.size = size;
		//セルの作成
		cells = new Cell[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				Cell cell = new Cell();
				cells[i][j] =  cell;
			}
		}
		
	}
	
	public Cell[][] getCellList() {
		return this.cells;
	}
	
	public void setMine(int x, int y, int mineNum) {
		Random rand = new Random();
		int randx;
		int randy;
		for(int i = 0; i < mineNum;) {
			//地雷作成時のランダムな座標生成
			randx = rand.nextInt(size);
			randy = rand.nextInt(size);	
			
			Cell cell = cells[randx][randy];
			//地雷の設置
			if(cell.hasMine() == false &&( (randx < x - 1  || x + 1 < randx) || (randy < y - 1  || y + 1 < randy))) {
				System.out.println(randx + " , " + randy); //デバック用
				cell.setMine();
				i++;
				
				//地雷を設置したマスの八近傍の数字を加算
				int xNeighbor;
				int yNeighbor;
				//地雷を設置したマスの八近傍の数字を加算
				for(int[] xy : neighbor) {
					xNeighbor = randx + xy[0];
					yNeighbor = randy + xy[1];
					//盤の外側を参照しない場合
					if(inBoard(xNeighbor, yNeighbor)) {
						cells[xNeighbor][yNeighbor].addMineNum();
					}
				}
			}
			
		}
	}
	public boolean removeCover(int x,int y) {
		boolean hasMine = cells[x][y].hasMine();
		//cellに爆弾があった場合
		if(hasMine == true) {
			for(Cell[] cellsLine : cells) {
				for(Cell cell : cellsLine) {
					cell.open();
				}
			}
			return true;
		}
		//cellに爆弾がなかった場合
		else {
			cells[x][y].open();
			int mineNum = cells[x][y].getMineNum();
			//八近傍の爆弾の数が0だった場合
			if(mineNum == 0) {
				//八近傍のの探索
				int xNeighbor;
				int yNeighbor;
				for(int[] xy : neighbor) {
					xNeighbor = x + xy[0];
					yNeighbor = y + xy[1];
					if(inBoard(xNeighbor, yNeighbor) // 盤の外側を参照しておらず、
							&& cells[xNeighbor][yNeighbor].isHidden()) { //	かつそのセルが開いていなければ
						// 再帰的にカバーを取り除く
						removeCover(xNeighbor, yNeighbor);
					}
				}
			}
			return false;
		}
	}
	private boolean inBoard(int x, int y) {
		if(x < 0 || y < 0) {
			return false;
		}
		else if(size <= x || size <= y) {
			return false;
		}

		return true;
	}

}
