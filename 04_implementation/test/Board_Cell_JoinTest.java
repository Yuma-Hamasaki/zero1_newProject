package minesweeper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.Before;

public class Board_Cell_JoinTest {
	Board board;
	Cell[][] cellList;
	int x;
	@Before
	public void setUp() {
		Board board = new Board(9);
		cellList = board.getCellList();
	}
	
	@Test
	public void test1_1() {
		boolean expected = true;
		cellList[1][1].setMine();
		boolean actual = board.removeCover(1,1);
		
		assertThat(actual, is(expected));
	}
}
