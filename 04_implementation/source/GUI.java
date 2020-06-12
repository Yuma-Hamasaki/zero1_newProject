package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


//フラグが立ったまま、マスが消えた場合フラグの数がおかしい
//ボタンがイネーブルになったときの文字色の変更
public class GUI extends JFrame implements MouseListener,ActionListener{
	private int cellsNum;
	private int mineTotal;
	private int flagNum;
	private JFrame frame = new JFrame("MineSweeper");
	private JPanel startPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private ButtonWithCoordinates[][] buttonArray;
	private JLabel flagLabel = new JLabel("Flag : 0　");
	private JLabel mineLabel = new JLabel("　Mine : 0");
	private Minesweeper minesweeper;
	private JComboBox combo;
	private Level level;
	
	public GUI() {
		flagLabel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
		mineLabel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void displayStartPanel() {
		String[] menu = { "初級", "中級", "上級" };
		combo = new JComboBox(menu);
		JButton button = new JButton("Start!");
		button.addActionListener(this);
		combo.setPreferredSize(new Dimension(80, 30));
		startPanel.add(combo);
		startPanel.add(button);
		int index = combo.getSelectedIndex();
		frame.add(startPanel, BorderLayout.CENTER);
		frame.setSize(50,120);
		frame.setVisible(true);
	}
	public void gameStart() {
		switch(level) {
			case EASY:
				mineTotal = 3;
				cellsNum = 5;
				frame.setSize(500,500);
				break;
			case NORMAL:
				mineTotal = 20;
				cellsNum = 10;
				frame.setSize(500,500);
				break;
			case HARD:
				mineTotal = 56;
				cellsNum = 15;
				frame.setSize(725,725);
				break;
		}
		mineLabel.setText("　Mine : " + String.valueOf(mineTotal));
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
			if(button.getFlag() == false) {
				clickLeft(button);
			}
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
			minesweeper.startTime();
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
			flagNum--;
			flagLabel.setText("Flag : " + String.valueOf(flagNum)+"　");
		}
		else {
			ImageIcon icon = new ImageIcon("src\\Minesweeper\\flag.png");
			button.setIcon(icon);
			flagNum++;
			flagLabel.setText("Flag : " + String.valueOf(flagNum)+"　");
		}
		button.setFlag(!flag);
	}
	private void displayWin() {
		JLabel label = new JLabel("You win!!\n" + minesweeper.getTime());
		label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,50));
		JOptionPane.showMessageDialog(null,label,"Result",JOptionPane.PLAIN_MESSAGE);
		gameReset();
		displayStartPanel();
	}
	
	private void displayGameover() {
		JLabel label = new JLabel("You lose...");
		label.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,50));
		JOptionPane.showMessageDialog(null,label,"Result",JOptionPane.PLAIN_MESSAGE);
		gameReset();
		displayStartPanel();
	}
	
	private void gameReset() {
		frame.remove(gamePanel);
		frame.remove(infoPanel);
		gamePanel.removeAll();
		infoPanel.removeAll();
		flagLabel.setText("Flag : 0　");
		minesweeper = null;
		buttonArray = null;
		flagNum = 0;
		Cell.zeroOpenedNum();
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
						buttonArray[j][i].setIcon(icon);
						
					}
					else {
						int bombNum = cells[j][i].getMineNum();
						if(0 < bombNum) {
							buttonArray[j][i].setText("<html><font color=red>" + String.valueOf(bombNum)+"</font></html>");
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
		int index = combo.getSelectedIndex();
		switch (index) {
		case 0:
			this.level = Level.EASY;
			break;
		case 1:
			this.level = Level.NORMAL;
			break;
		case 2:
			this.level = Level.HARD;
			break;
		}
		startPanel.removeAll();
		frame.remove(startPanel);
		SwingUtilities.updateComponentTreeUI(frame);
		gameStart();
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
