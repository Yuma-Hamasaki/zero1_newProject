package minesweeper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class CellTest {
	@Test
	public void test1() {
		Cell cell1 = new Cell();
		int expected = 0;
		int actual = Cell.getOpenedNum();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test2() {
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		int expected = 0;
		int actual = cell1.getMineNum();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test3() {
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		
		boolean expected = false;
		boolean actual = cell1.hasMine();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test4() {
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		
		boolean expected = true;
		boolean actual = cell1.isHidden();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test5() {
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		
		boolean expected = true;
		cell1.setMine();
		boolean actual = cell1.hasMine();
		assertThat(actual, is(expected));
	}
	
	@Test
	public void test6() {
		//前回までの状態の再現
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		cell1.setMine();
		//今回のテスト内容
		boolean expected = false;
		cell2.open();
		boolean actual = cell2.isHidden();
		assertThat(actual, is(expected));
	}
	/*test７は別のテストクラスで行う。
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
	*/
	@Test
	public void test8() {
		//前回までの状態の再現
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		cell1.setMine();
		cell2.open();
		//今回のテスト内容
		int expected = 1;
		cell2.addMineNum();
		int actual = cell2.getMineNum();
		assertThat(actual, is(expected));
	}
	/*test７は別のテストクラスで行う。
	@Test
	public void test9() {
		//前回までの状態の再現
		Cell cell1 = new Cell();
		Cell cell2 = new Cell();
		cell1.setMine();
		cell2.open();
		cell2.addMineNum();
		//今回のテスト内容
		int expected = 2;
		cell1.open();
		int actual = Cell.getOpenedNum();
		assertThat(actual, is(expected));
	}
	*/
}
