package minesweeper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.Before;

public class Board_Cell_JoinTest {
	Board board;
	//Cell[][] cellList;
	int x;
	@Before
	public void setUp() {
		board = new Board(9);
	}
	
	@Test
	public void test1_1() {
		boolean expected = true;
		board.getCellList()[1][1].setMine();
		boolean actual = board.removeCover(1,1);
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test1_2() {
		boolean expected = false;
		boolean actual = board.removeCover(1,1);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test2() {
		board.setMine(1, 1, 10);
		for (int y=0; y<9; y++) {
			for (int x=0; x<9; x++) {
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
