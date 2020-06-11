package Minesweeper;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements MouseListener{
	private static int cellsNum = 9;
	private int mineTotal = 10;
	private JFrame frame = new JFrame("MineSweeper");
	private JPanel panel = new JPanel();
	private JPanel win_panel = new JPanel();
	private JPanel lose_panel = new JPanel();
	private ButtonWithCoordinates[][] buttonArray = new ButtonWithCoordinates[cellsNum][cellsNum];
	private Minesweeper minesweeper;
	public GUI() {
		minesweeper = new Minesweeper(mineTotal,cellsNum);
		for(int i = 0;i < cellsNum;i++) {
			for(int j = 0;j < cellsNum;j++) {
				buttonArray[j][i] = new ButtonWithCoordinates(j,i);
				buttonArray[j][i].setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
				buttonArray[j][i].addMouseListener(this);
				panel.add(buttonArray[j][i]);
			}
		}
		panel.setPreferredSize(new Dimension(270,270));
		panel.setLayout(new GridLayout(cellsNum,cellsNum));
		frame.add(panel);
		frame.setSize(500,500);
		frame.setResizable(false);
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
		Cell[][] cells = minesweeper.getCellList();
		int openNum = Cell.getOpenedNum();
		if(openNum == 0) {
			minesweeper.setAllMine(x,y);
		}
		isMine = minesweeper.removeCover(x,y);
		updateDisplay(cells);
		if(isMine == true) {
			displayGameover();
			return;
		}
		openNum = Cell.getOpenedNum();
		if(openNum == (cellsNum*cellsNum) - minesweeper.getMineTotal()) {
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
			ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\新しいフォルダー\\source\\src\\Minesweeper\\flag.png");
				button.setIcon(icon);
		}
		button.setFlag(!flag);
	}
	public void displayWin() {
		JLabel label = new JLabel("You win!!");
		label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,50));
		win_panel.add(label);
		frame.remove(panel);
		frame.add(win_panel);
	}
	
	public void displayGameover() {
		JLabel label = new JLabel("You lose...");
		label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,50));
		lose_panel.add(label);
		frame.remove(panel);
		frame.add(lose_panel);
	}
	
	public void updateDisplay(Cell[][] cells) {
		for(int i = 0; i < cellsNum;i++) {
			for(int j = 0; j < cellsNum;j++) {
				boolean isHidden = cells[j][i].isHidden();
				if(isHidden == true) {
					continue;
				}
				else {
					buttonArray[j][i].removeMouseListener(this);
					buttonArray[j][i].setEnabled(false);
					boolean hasMine = cells[j][i].hasMine();
					if(hasMine == true) {
						ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\新しいフォルダー\\source\\src\\Minesweeper\\mine.png");
						buttonArray[j][i].setIcon(icon);
						
					}
					else {
						int bombNum = cells[j][i].getMineNum();
						if(0 < bombNum) {
							buttonArray[j][i].setText(String.valueOf(bombNum));
						}
						else {
							buttonArray[j][i].setText("");
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
