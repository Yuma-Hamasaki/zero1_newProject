package minesweeper_nigi;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class MinesweeperTest {
	Minesweeper minesweeper;
	
	@Before
	public void setUp() {
		int mineTotal = 10;
		int cellsNum = 9;
		minesweeper = new Minesweeper(mineTotal, cellsNum);
	}

	@Test
	public void testCase_1() {
		boolean expected_result = false;
		minesweeper.setAllMine(8, 8);
		boolean actual = minesweeper.removeCover(8, 8);
		
		assertThat(actual, is(expected_result));
	}
	
	@Test
	public void testCase_2() {
		Cell[][] cellList = minesweeper.getCellList();
		int expected_result = 81;
		int actual = 0;
		
		for(Cell[] cell : cellList) {
			actual += cell.length;
		}

		assertThat(actual, is(expected_result));

	}
	
	@Test
	public void testCase_3() {
		minesweeper.setAllMine(8, 8);
		Cell[][] cellList = minesweeper.getCellList();

		for (int y=0; y<9; y++) {
			for (int x=0; x<9; x++) {
				if(cellList[x][y].hasMine()) {
					System.out.print("m");
				}else {
					System.out.print(cellList[x][y].getMineNum());
				}
			}
			System.out.println("");
		}
	}	
	
	@Test
	public void testCase_4() {
		minesweeper.setAllMine(8, 8);

		minesweeper.startTime();
		try {
			Thread.sleep(30000);
		}
		catch(InterruptedException e) {
			
		}
		
		String actual = minesweeper.getTime();
		String expected_result = "0:30";
		
		assertThat(actual, is(expected_result));
		
	}	
}
