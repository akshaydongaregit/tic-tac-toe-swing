package com.tresk.toe.board;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlayBoardUI extends JFrame{

	private static final long serialVersionUID = -8117305278932769687L;

	PlayerTurn turn = new PlayerTurn();
	PlayBoard playBoard ;

	JFrame im;
	JPanel panel = new JPanel();
	
	public PlayBoardUI() {
		im = this;
		initBoard();
		initCells();
		initWindow();
	
	}
	private void initBoard() {
		playBoard= new PlayBoard();
		playBoard.initDirections();
	}
	private void initCells() {
		GridLayout layout = new GridLayout(3, 3);
		panel.setLayout(layout);
		
		for(int i=0;i<3;i++) 
		for(int j=0;j<3;j++){		
			JButton btn = new JButton("");
			btn.setActionCommand(j+","+i);
			btn.addActionListener(new PlayBoardUI.ClickListener());
			panel.add(btn);
			
		}
		
		this.add(panel);
	}
	
	private void initWindow() {
		this.setTitle("Tic Tak Toe");
		this.setSize(500, 500);
		this.setLocation(100, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new PlayBoardUI();
	}

	public void resetBoard() {
		for(int i=0;i<panel.getComponentCount();i++) 
			((JButton)panel.getComponent(i)).setText("");
		
		this.playBoard.reset();
	}
	
	private class ClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String clicked = e.getActionCommand();
			JButton btn = (JButton) e.getSource();
			
			System.out.println("clicked : "+clicked);
			StringTokenizer tokenizer = new StringTokenizer(clicked, ",");
			int x = Integer.parseInt(tokenizer.nextToken());
			int y = Integer.parseInt(tokenizer.nextToken());
			
			int player = turn.getTurn();
			
			if(playBoard.setCell(x, y, player))
				btn.setText(""+player);
			int wonPlayer = playBoard.verifyBoard();
			if(wonPlayer!=0) {
				JOptionPane.showMessageDialog(null,"Player "+wonPlayer+" won !!! ");
				System.out.println("|********************|\n Winner Player "+wonPlayer+"\n|________________________________________|");
				resetBoard();
			}else if(playBoard.verifyDraw()) {
				JOptionPane.showMessageDialog(null,"Match is Drawn !!!");
				System.out.println("|********************|\n Match is Drawn !!! \n|________________________________________|");
				resetBoard();
			}
		}
		
	}
}
