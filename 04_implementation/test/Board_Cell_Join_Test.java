package Minesweeper;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;

public class Board_Cell_Join_Test {

	Board board;
	Cell[][] cells;
	
	@Test
	public void Tets1_1() {
		board = new Board(5);
		cells = board.getCellList();
		cells[1][1].setMine();
		boolean expected_result = true;
		boolean actual = board.removeCover(1,1);
		assertThat(actual,is(expected_result));
	}
	@Test
	public void Tets1_2() {
		board = new Board(5);
		cells = board.getCellList();
		boolean expected_result = false;
		boolean actual = board.removeCover(1,1);
		assertThat(actual,is(expected_result));
	}
	@Test
	public void Tets2_1() {
		board = new Board(5);
		board.setMine(1,1,3);
		for (int y=0; y<5; y++) {
			for (int x=0; x<5; x++) {
				if(board.getCellList()[x][y].hasMine()) {
					System.out.print("m");
				}else {
					System.out.print(board.getCellList()[x][y].getMineNum());
				}
			}
			System.out.println("");
		}
		
	}
	@Test
	public void Tets2_2() {
		board = new Board(10);
		board.setMine(1,1,20);
		for (int y=0; y<10; y++) {
			for (int x=0; x<10; x++) {
				if(board.getCellList()[x][y].hasMine()) {
					System.out.print("m");
				}else {
					System.out.print(board.getCellList()[x][y].getMineNum());
				}
			}
			System.out.println("");
		}
		
	}
	public void Tets2_3() {
		board = new Board(15);
		board.setMine(1,1,56);
		for (int y=0; y<15; y++) {
			for (int x=0; x<15; x++) {
				if(board.getCellList()[x][y].hasMine()) {
					System.out.print("m");
				}else {
					System.out.print(board.getCellList()[x][y].getMineNum());
				}
			}
			System.out.println("");
		}
		
	}
}
