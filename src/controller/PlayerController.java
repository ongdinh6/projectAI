package controller;

import model.BoardGame;
import model.Player;

public class PlayerController implements IController{
	BoardGame board;
	
	public PlayerController() {
		board = new BoardGame();
	}

	@Override
	public boolean isValid(int i, int j) {
		//ngoai gioi han cua ban co
		if(i >= board.getSize() || j >= board.getSize()) {
			return false;
		}
		//xung dot khi de len nhau
		for(int row = 0; i < board.getSize(); row++) {
			for(int col = 0; col < board.getSize(); col++) {
				if(i == row && j == col) {
					if(board.getBoard()[i][j] != 0) {
						return false;
					}else {
						return true;
					}
				}
			}
		}
		return true;
	}
	@Override
	public int[][] moved(Player player, int i, int j) {
		int [][] newBoard = new int[15][15];
		int [][] b = board.getBoard();
		if(!isValid(i, j)) {
			System.out.println("The place is unvalid!");
			return null;
		}else {
			for (int row = 0; row < b.length; row++) {
				for(int col = 0; col < b.length; col++) {
					if(i == row && j == col) {
						b[i][j] = player.getPlayer();
						newBoard = b;
					}
				}
			}
		}
		board.setBoard(newBoard);
		return newBoard;
	}
	public void showBoard() {
		int[][] board = this.board.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

	@Override
	public boolean isOver(int[][] board) {
		board = this.board.getBoard();
		int xungdot = 0;
		int s = 1;
		for (int i = board.length - 1; i >= 0; i--) {
			s += 1;
			for (int j = board.length - s; j >= 0; j--) {
				if (board[j] == board[i]) {
					xungdot += 1;
				}
			}
		}
		
		System.out.println(xungdot);
		
		return false;
	}


}
