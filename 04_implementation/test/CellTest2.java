package minesweeper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class CellTest2 {
	@Test
	public void test7() {
		//前回までの状態の再現
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		cell1.setMine();
		cell2.open();
		//今回のテスト内容
		int expected = 1;
		int actual = Cell.getOpenedNum();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test9() {
		//前回までの状態の再現
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		cell1.setMine();
		cell2.addMineNum();
		//今回のテスト内容
		int expected = 2;
		cell1.open();
		int actual = Cell.getOpenedNum();
		assertThat(actual, is(expected));
	}
}
