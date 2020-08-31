package model;

import java.awt.Point;
import java.util.Random;

import javax.swing.JButton;

import model.BoardGame;
import model.Player;
import view.CaroBoard;

public class BotAI {
	CaroBoard caroBoard ;
	BoardGame boardGame = new BoardGame();
	public Player player = new Player();
	
	public int heuristic(Player player, int x, int y, JButton [][] boardButton) {
		Point point = new Point(x, y);
		boardButton = this.caroBoard.board;
		
		if (player.getPlayer() == 1) {
			for (int i = 0; i < boardButton.length; i++) {
				for (int j = 0; j < boardButton.length; j++) {
					if(x == i && y == j) {
						boardButton[x][y].setText("X");
						boardGame.updateBoard(player, x, y);
					}
				}
			}
		}
		
		return 1;
	}
	public void checkPoint(Point point, int[][] boardGame) {
		for (int i = 0; i < boardGame.length; i++) {
			for (int j = 0; j < boardGame.length; j++) {
				if(point.x == i && point.y == j) {
					boardGame[i][j] = player.getPlayer();
				}
			}
		}
		for (int i = 0; i < boardGame.length; i++) {
			for (int j = 0; j < boardGame.length; j++) {
				System.out.print(boardGame[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
