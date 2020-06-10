package Minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements MouseListener{
	static int cellsNum = 9;
	private static JFrame frame = new JFrame("MineSweeper");
	private static JPanel panel = new JPanel();
	private ButtonWithCoordinates[][] buttonArray = new ButtonWithCoordinates[cellsNum][cellsNum];
	private Minesweeper minesweeper;
	private Board board;
	public GUI() {
		minesweeper = new Minesweeper();
		for(int i = 0;i < cellsNum;i++) {
			for(int j = 0;j < cellsNum;j++) {
				
				buttonArray[j][i] = new ButtonWithCoordinates(j,i);
				buttonArray[j][i].addMouseListener(this);
				panel.add(buttonArray[j][i]);
			}
		}
		panel.setPreferredSize(new Dimension(270,270));
		panel.setLayout(new GridLayout(cellsNum,cellsNum));
		frame.add(panel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ButtonWithCoordinates button = (ButtonWithCoordinates) e.getSource();
		if(e.getButton() == MouseEvent.BUTTON1) {
			clickLeft(button);
		}
		else if(e.getButton() == MouseEvent.BUTTON3) {
			clickRight(button);
		}
	}
	private void clickLeft(ButtonWithCoordinates button) {
		int x = button.getCoordX();
		int y = button.getCoordY();
		boolean isMine;
		int openNum = cell.getOpenedNum();
		if(openNum == 0) {
			Minesweeper.setAllMine(x,y);
		}
		isMine = Minesweeper.removeCover(x,y);
		Cell[][] cells = Minesweeper.getCell();
		updateDisplay(cells,button);
		if(isMine == true) {
			displayGameover();
			return;
		}
		openNum = cell.getOpenedNum();
		if(openNum == (cellsNum*cellsNum) - Minesweeper.getMineTotal()) {
			displayWin();
			return;
		}
	}
	private void clickRight(ButtonWithCoordinates button) {
		boolean flag = button.getFlag();
		if(flag == true) {
			button.setIcon(null);
		}
		else {
			ImageIcon icon = new ImageIcon("./hataa.png");
			button.setIcon(icon);
		}
		button.setFlag(!flag);
	}
	public void displayWin() {}
	
	public void displayGameover() {}
	
	public void updateDisplay(Cell[][] cells) {
		for(int i = 0; i < cellsNum;i++) {
			for(int j = 0; j < cellsNum;j++) {
				boolean isHidden = cells[j][i].isHidden();
				if(isHidden == true) {
					continue;
				}
				else {
					boolean hasMine = cells[j][i].hasMine();
					if(hasMine == true) {
						ImageIcon icon = new ImageIcon("./bakudan.png");
						buttonArray[j][i].setIcon(icon);
					}
					else {
						int bombNum = cells[j][i].getMineNum();
						if(0 < bombNum) {
							buttonArray[j][i].setText(String.valueOf(bombNum));
						}
						else {
							buttonArray[j][i].setText(String.valueOf(bombNum));
						}
					}
				}
			}
		}
	}
	
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
