package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements MouseListener,ActionListener{
	private int cellsNum;
	private int mineTotal;
	private JFrame frame = new JFrame("MineSweeper");
	private JPanel startPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private ButtonWithCoordinates[][] buttonArray;
	private Minesweeper minesweeper;
	private Level level;
	public GUI() {
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		level = Level.NOMAL;
	}
	public void gameStart() {
		switch(level) {
			case EASY:
				mineTotal = 3;
				cellsNum = 5;
				break;
			case NOMAL:
				mineTotal = 20;
				cellsNum = 10;
				break;
			case HARD:
				mineTotal = 56;
				cellsNum = 15;
				break;
		}
		
		minesweeper = new Minesweeper(mineTotal,cellsNum);
		buttonArray = new ButtonWithCoordinates[cellsNum][cellsNum];
		for(int i = 0;i < cellsNum;i++) {
			for(int j = 0;j < cellsNum;j++) {
				buttonArray[j][i] = new ButtonWithCoordinates(j,i);
				buttonArray[j][i].setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
				buttonArray[j][i].addMouseListener(this);
				gamePanel.add(buttonArray[j][i]);
			}
		}
		gamePanel.setLayout(new GridLayout(cellsNum,cellsNum));
		JLabel flagLabel = new JLabel("Flag");
		JLabel mineLabel = new JLabel("Mine");
		infoPanel.add(flagLabel);
		infoPanel.add(mineLabel);
		infoPanel.setLayout(new FlowLayout());
		frame.add("Center",gamePanel);
		frame.add("South", infoPanel);
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
			ImageIcon icon = new ImageIcon("src\\Minesweeper\\flag.png");
				button.setIcon(icon);
		}
		button.setFlag(!flag);
	}
	private void displayWin() {
		JLabel label = new JLabel("You win!!");
		label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,50));
		JOptionPane.showMessageDialog(null,label,"Result",JOptionPane.PLAIN_MESSAGE);
	
	}
	
	private void displayGameover() {
		JLabel label = new JLabel("You lose...");
		label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,50));
		JOptionPane.showMessageDialog(null,label,"Result",JOptionPane.PLAIN_MESSAGE);
		frame.remove(gamePanel);
		frame.remove(infoPanel);
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	public void updateDisplay(Cell[][] cells) {
		for(int i = 0; i < cellsNum;i++) {
			for(int j = 0; j < cellsNum;j++) {
				boolean isHidden = cells[j][i].isHidden();
				if(isHidden == true) {
					continue;
				}
				else {
					buttonArray[j][i].setEnabled(false);
					buttonArray[j][i].removeMouseListener(this);
					buttonArray[j][i].setIcon(null);
					
					boolean hasMine = cells[j][i].hasMine();
					if(hasMine == true) {
						ImageIcon icon = new ImageIcon("src\\Minesweeper\\mine.png");
						buttonArray[j][i].setDisabledIcon(icon);
						
					}
					else {
						int bombNum = cells[j][i].getMineNum();
						if(0 < bombNum) {
							buttonArray[j][i].setForeground(cells[j][i].getColor());
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
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
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
