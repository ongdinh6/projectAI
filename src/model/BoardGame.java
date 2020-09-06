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
	public int evaluationBoard = 0;
	public ArrayList<Point> listMax = new ArrayList<Point>();
	public ArrayList<Point> listMin = null;
	int maxDepth = 2;
	int minDepth = 0;
	int ai_x, ai_y;

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
				} else if (demlientiep > 0) { // gáº·p Ă´ bá»‹ cháº·n
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
		// Duong cheo len
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
		// Duong cheo xuong
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
				return 3000;
			else {// luot cua o
				if (blocks == 0)// khong bi chan
					return 2000;
				else
					return 300;
			}
		}
		case 3: {
			// 3 con lien tiep
			if (blocks == 0) {// khong bi chan
				if (currentTurn)// luot minh
					return 1000;
				else
					return 600;
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

	public int[][] clone(int[][] board) {
		int[][] boardClone = new int[row][col];
		for (int i = 0; i < boardClone.length; i++) {
			for (int j = 0; j < boardClone.length; j++) {
				boardClone[i][j] = board[i][j];
			}
		}
		return boardClone;
	}

	int count = 0;

	public int minimax(int depth, int[][] board, boolean turn, int alpha, int beta) {
		Player player = new Player();
		int hang = 0;
		int cot = 0;
		boolean isTimThay = false;
		int tempmin = Integer.MIN_VALUE;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0) {
					int[][] cloneboard = clone(board);
					count++;
					cloneboard[i][j] = 2;

					int min = checkMax(depth - 1, cloneboard, false, alpha, beta);
					if (tempmin < min) 
						tempmin = min;
//						 = tempmin;
						hang = i;
						cot = j;
						isTimThay = true;

						if (tempmin < alpha) {
							setPoint(new Point(hang, cot));
						}
						beta = Math.min(beta, tempmin);
					
				}
			}
<<<<<<< HEAD
		}
		return beta;

//			}
//		}
//		return beta;
	}

	public int checkMax(int depth, int[][] board, boolean turn, int alpha, int beta) {
		int tempmax = Integer.MAX_VALUE;
		int sc = -1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0) {
					int[][] cloneboard = clone(board);
					count++;
					cloneboard[i][j] = 1;
					int max = minimax(depth - 1, cloneboard, false, alpha, beta);
					System.out.println("max: " + max);
					if (tempmax > max) {
						tempmax = max;
						sc = tempmax;

						if (sc >= beta) {
							System.out.println("beta checkMax: " + beta);
							setPoint(new Point(i, j));
=======
			if (isTimThay == true) {
				return max;
			} else {
				hang = 0;
				cot = 0;
			}
		} else {
			if (turn == true) { // isCheckMin le
				int tempmin = Integer.MIN_VALUE;
				int sc = -1;
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board.length; j++) {
						if (board[i][j] == 0) {
							int[][] cloneboard = clone(board);
							cloneboard[i][j] = 2;

							int min = minimax(depth - 1, cloneboard, false, alpha, beta);
							if (tempmin < min) {
								tempmin = min;
								sc = tempmin;
								hang = i;
								cot = j;
								isTimThay = true;
								if (sc < alpha) {
									setPoint(new Point(hang, cot));
								}
								beta = Math.min(beta, sc);
								return beta;
							}
						}
					}
				}

			} else { // is CheckMax chan
				int tempmax = Integer.MAX_VALUE;
				int sc = -1;
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board.length; j++) {
						if (board[i][j] == 0) {
							int[][] cloneboard = clone(board);
							cloneboard[i][j] = 1;
							int max = minimax(depth - 1, cloneboard, true, alpha, beta);
							if (tempmax > max) {
								tempmax = max;
								sc = tempmax;
								hang = i;
								cot = j;
								isTimThay = true;
								if (sc > beta) {
									setPoint(new Point(hang, cot));
								}
								alpha = Math.max(alpha, sc);
								return alpha;
							}
>>>>>>> parent of 8ca5a33... update03 minimax
						}

					}
<<<<<<< HEAD
					alpha = Math.max(alpha, sc);
=======
				}
>>>>>>> parent of 8ca5a33... update03 minimax

				}
			}
		}
		return alpha;
	}

	public void alphabeta(int alpha, int beta, int depth, int[][] board, int player) {
		if (depth%3 == 0) {
			minimax(depth, board, true, alpha, beta);
//			player = 1;
		} else { // checkMax
			checkMax(depth, board, false, alpha, beta);
		}
		System.out.println("count: " + count);
	}

	public Point moveOn(int player) {
<<<<<<< HEAD
		// alpha xet node max, beta xet node min
		alphabeta(-10000, 10000, 1, board, player);
=======
		//alpha xet node max, beta xet node min
		alphabeta(-10000, 10000, 5, board, player);
>>>>>>> parent of 8ca5a33... update03 minimax
		Point temp = getPoint();
		if (temp != null) {
			ai_x = temp.x;
			ai_y = temp.y;
		}
		return new Point(ai_x, ai_y);
	}
}