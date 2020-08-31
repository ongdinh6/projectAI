package model;

import java.awt.Point;
import java.util.ArrayList;

public class BoardGame {
	private static final Player Player = null;
	public int[][] board;
	public int row = 15;
	public int col = 15;
	public int size = 15;
	private Point point;

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int[][] getBoard() {
		return board;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public BoardGame() {
		this.board = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = 0;
			}
		}
	}

	public BoardGame(int[][] board) {
		this.board = getBoard();
	}

	public void showBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void showBoard(int[][] board) {
		board = this.board;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] updateBoard(Player player, int row, int col) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (row == i && col == j && board[row][col] == 0) {
					board[row][col] = player.getPlayer();
				}
			}
		}
		System.out.println();
		// show
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		return board;

	}

	public int getScore(int[][] board, boolean luot, Player player) {

		return evaluateHorizontal(board, luot, player) + evaluateVertical(board, luot, player)
				+ evaluateDiagonal(board, luot, player);
	}

	// danh gia ngang
	public int evaluateHorizontal(int[][] board, boolean luot, Player player) {
		int demlientiep = 0;
		int blocks = 2;
		int score = 0;
		boolean nguoichoi;
		if (player.getPlayer() == 1) {
			nguoichoi = true;
		} else {
			nguoichoi = false;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == (luot ? 2 : 1)) {
					demlientiep++;
				} else if (board[i][j] == 0) {// quan tiep theo la trong
					if (demlientiep > 0) {
						blocks--;
						score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
						demlientiep = 0;
						blocks = 1;
					} else {
						blocks = 1;
					}
				} else if (demlientiep > 0) { 
					score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
					demlientiep = 0;
					blocks = 2;
				} else {
					blocks = 2;
				}
			}
			if (demlientiep > 0) {
				score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
			}
			demlientiep = 0;
			blocks = 2;
		}

		return score;

	}

	// danh gia hang doc
	public int evaluateVertical(int[][] board, boolean luot, Player player) {
		int demlientiep = 0;
		int blocks = 2;
		int score = 0;
		boolean nguoichoi;
		if (player.getPlayer() == 1) {
			nguoichoi = true;
		} else {
			nguoichoi = false;
		}
		for (int j = 0; j < board.length; j++) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][j] == (luot ? 2 : 1)) {
					demlientiep++;
				} else if (board[i][j] == 0) {
					if (demlientiep > 0) {
						blocks--;
						score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
						demlientiep = 0;
						blocks = 1;
					} else {
						blocks = 1;
					}
				} else if (demlientiep > 0) {
					score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
					demlientiep = 0;
					blocks = 2;
				} else {
					blocks = 2;
				}
			}
			if (demlientiep > 0) {
				score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
			}
			demlientiep = 0;
			blocks = 2;
		}
		return score;

	}

	// danh gia 2 duong cheo
	public int evaluateDiagonal(int[][] board, boolean luot, Player player) {

		int demlientiep = 0;
		int blocks = 2;
		int score = 0;
		boolean nguoichoi;
		if (player.getPlayer() == 1) {
			nguoichoi = true;
		} else {
			nguoichoi = false;
		}
		// Ä�uong cheo len
		for (int k = 0; k <= 2 * (board.length - 1); k++) {
			int iStart = Math.max(0, k - board.length + 1);
			int iEnd = Math.min(board.length - 1, k);
			for (int i = iStart; i <= iEnd; ++i) {
				int j = k - i;

				if (board[i][j] == (luot ? 2 : 1)) {
					demlientiep++;
				} else if (board[i][j] == 0) {
					if (demlientiep > 0) {
						blocks--;
						score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
						demlientiep = 0;
						blocks = 1;
					} else {
						blocks = 1;
					}
				} else if (demlientiep > 0) {
					score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
					demlientiep = 0;
					blocks = 2;
				} else {
					blocks = 2;
				}

			}
			if (demlientiep > 0) {
				score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);

			}
			demlientiep = 0;
			blocks = 2;
		}
		// Ä�uong cheo xuong
		for (int k = 1 - board.length; k < board.length; k++) {
			int iStart = Math.max(0, k);
			int iEnd = Math.min(board.length + k - 1, board.length - 1);
			for (int i = iStart; i <= iEnd; ++i) {
				int j = i - k;
				if (board[i][j] == (luot ? 2 : 1)) {
					demlientiep++;
				} else if (board[i][j] == 0) {
					if (demlientiep > 0) {
						blocks--;
						score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
						demlientiep = 0;
						blocks = 1;
					} else {
						blocks = 1;
					}
				} else if (demlientiep > 0) {
					score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);
					demlientiep = 0;
					blocks = 2;
				} else {
					blocks = 2;
				}
			}
			if (demlientiep > 0) {
				score += getConsecutiveSetScore(demlientiep, blocks, luot == nguoichoi);

			}
			demlientiep = 0;
			blocks = 2;
		}
		return score;
	}

	public int getConsecutiveSetScore(int count, int blocks, boolean currentTurn) {
		if (blocks == 2 && count < 5)
			return 0;
		switch (count) {
		// 5 con lien tiep
		case 5: {
			return 10000;
		}
		case 4: {
			// 4 con lien tiep
			if (currentTurn) // luot minh
				return 800;
			else {// luot cua o
				if (blocks == 0)// khong bi chan
					return 600;
				else
					return 300;
			}
		}
		case 3: {
			// 3 con lien tiep
			if (blocks == 0) {// khong bi chan
				if (currentTurn)// luot minh
					return 500;
				else
					return 200;
			} else {
				// Bi chan
				if (currentTurn)// luot minh
					return 100;
				else
					return 20;
			}
		}
		case 2: {
			// 2 con lien tiep
			if (blocks == 0) {// khong bi chan
				if (currentTurn)// luot minh
					return 50;
				else
					return 10;
			} else {
				return 5;
			}
		}
		case 1: {
			return 0;
		}
		}
		return 1000;
	}

	public int minimax(int depth, int[][] board) {
		Player player = new Player();
		ArrayList<int[][]> listBoard = new ArrayList<int[][]>();
		int[][] bct = new int[row][col];
		int[][] boardThuGan = new int[row][col];
		for (int i = 0; i < boardThuGan.length; i++) {
			for (int j = 0; j < boardThuGan.length; j++) {
				boardThuGan[i][j] = board[i][j];
			}
		}
		int sc = 0;
		boolean isTimThay = false;
		int max = getScore(board, false, player) - getScore(board, true, player);
		int hang = 0, cot = 0;
		for (int i = 0; i < boardThuGan.length; i++) {
			for (int j = 0; j < boardThuGan.length; j++) {
				if (boardThuGan[i][j] == 0) {
					boardThuGan[i][j] = 1;
					sc = getScore(boardThuGan, false, player) - getScore(boardThuGan, true, player);
					if (max < sc) {
						max = sc;
						hang = i;
						cot = j;
						boardThuGan[i][j] = 0;
						isTimThay = true;
					} else {
						boardThuGan[i][j] = 0;
					}
				}
			}

		}
		if (isTimThay == true) {
			listBoard.add(updateBoard(player, hang, cot));
			this.point = new Point(hang, cot);
		} 
		else {
			hang = 0;
			cot = 0;
		}
		return sc;

	}

}
