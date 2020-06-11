package Minesweeper;

import java.util.Random;


public class Board {
	private Cell cells[][];
	private int size;
	
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
			
			if(cell.hasMine() == false &&( randx != x  && randy != y)) {
				System.out.println(randx + " , " + randy); //デバック用
				cell.setMine();
				i++;
				
				//地雷を設置したマスの八近傍の数字を加算
				for(int j = -1; j <= 1; j++) {
					for(int k = -1; k <= 1; k++) {
						if( j != 0 || k != 0) {
							if(randx + j >= 0 && randx + j < size && randy + k >= 0 && randy + k < size) {
								cells[randx + j][randy + k].addMineNum();
							}
						}
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
				for(int j = -1; j <= 1; j++) {
					for(int k = -1; k <= 1; k++) {
						//現在のマス以外の場合
						if( j != 0 || k != 0) {
							//盤の外側を参照しない場合
							if(x + j >= 0 && x + j < size && y + k >= 0 && y + k < size) {
								//八近傍のセルが開いていなければ再帰的にカバーを取り除く
								if(cells[x + j][y + k].isHidden()) {
									removeCover(x + j,y + k);
								}
							}
						}
					}
				}
			}
			return false;
		}
	}

}
