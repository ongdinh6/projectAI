package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.PlayerController;
import model.AIPlayer;
import model.BoardGame;
import model.BotAI;
import model.Node;
import model.Player;

public class CaroBoard extends JFrame {
	BoardGame bGame;
	Point point;
	JButton btn;
	public JButton[][] board;
	int x, y, r, c = 0;
	JPanel panel, panelnew;
	PlayerController pc;
	int scorePlayer = 0;
	boolean turn = false;
	Point pointForX;

	public JButton[][] getBoard() {
		return board;
	}

	public void setBoard(JButton[][] board) {
		this.board = board;
	}

	public CaroBoard() {

		bGame = new BoardGame();
		panel = new JPanel();
		add(panel);
		panelnew = new JPanel();
		panel.setLayout(new GridLayout(bGame.row, bGame.col));

		// Action of player
		pc = new PlayerController();
		Player p1 = new Player();
		board = placeBoard(panel);
		checkPoint(p1, board);

		setTitle("CaroGame");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(670, 670);
	}

	public void checkPoint(Player player, JButton[][] board) {
		btn = new JButton();

		for (x = 0; x < board.length; x++) {
			for (y = 0; y < board.length; y++) {
				JButton b = new JButton();
				b.setLocation(x, y);
				btn = board[x][y];
				btn.setBackground(null);
				btn.setBorder(BorderFactory.createLineBorder(Color.gray, (int) 1));
				JButton bb = btn;

				bb.setText(null);
				if (x == 7 && y == 7) {
					bb.setText("X");
					bGame.updateBoard(player, x, y);
					turn = true;
					player.setPlayer(2);
				} else {
					bb.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							super.mouseClicked(e);
							point = e.getPoint();
							point.setLocation(b.getLocation());
							System.out.println(point.x + ":" + point.y);
							r = b.getLocation().x;
							c = b.getLocation().y;
							boolean click1 = true;
							boolean click2 = true;
							boolean isValid = false;
							boolean isDaCo = false;
							Point pointX = null;
							int nuoc = 0;
							int[][] bo = new int[r][c];
							if (point.x == r && point.y == c) {
								isValid = true;
								System.out.println(isValid);
							}
							if (isValid == true) {
								if (bb.getText() == null) {
									bb.setText("O");
									System.out.println("Score of player" + player.getPlayer() + ": "
											+ bGame.getScore(bGame.updateBoard(player, r, c), true, player));
									click2 = false;
									System.out.println("P2: " + r + ":" + c);
									player.setPlayer(1);
									turn = false;
									nuoc++;

									System.out.print("boardGoiY" + bGame.minimax(1, bGame.updateBoard(player, r, c)));

									//
									if (checkWin(r, c)) {
										String str = "Player win !";
										JOptionPane.showMessageDialog(null, str);
										Window w = SwingUtilities.getWindowAncestor(bb);
										if (w != null) {
											w.setVisible(false);
										}
										Test test = new Test();
										test.run();
										return;
									}
								

									pointX = bGame.getPoint();

									if (turn == false) {
										for (int i = 0; i < board.length; i++) {
											for (int j = 0; j < board.length; j++) {
												if (i == pointX.x && j == pointX.y) {
													board[i][j].setText("X");

//												System.out.println("Score of AI:" + bGame
//														.getScore(bGame.updateBoard(player, i, j), false, 1));
													player.setPlayer(2);

													if (checkWin(i, j) == true) {
														if (turn == false) {
															String str = "AI  win!";
															JOptionPane.showMessageDialog(null, str);
															Window w = SwingUtilities.getWindowAncestor(bb);
															if (w != null) {
																w.setVisible(false);
															}
															Test test = new Test();
															test.run();
														}
														return;
													}
													bo = bGame.updateBoard(player, i, j);
													turn = true;
												}
											}
										}
									}
									//
								}
							}
						}

					});

				}
			}
		}

	}

	public boolean isValid(Point point) {
		int x = point.x;
		int y = point.y;
		if (board[x][y] == null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkCorret(int i, int j) {
		if (i >= 0 && j >= 0 && i < bGame.row && j < bGame.col) {
			return true;

		} else {
			return false;
		}
	}

	public boolean checkWin(int i, int j) {
		int d = 0, k = i, h = 1;

		while (board[k][j].getText() == board[i][j].getText() && checkCorret(i, j)) {
			d++;
			k++;
			if (k > bGame.row - 1) {
				break;
			}
		}
		k = i - 1;// k = i-1
		if (k < 0)
			k = 0;
		while (board[k][j].getText() == board[i][j].getText() && k > 0) {
			d++;
			k--;
		}
		if (d > 4)
			return true;
		d = 0;
		h = j;

		// kiá»ƒm tra cá»™t
		while (board[i][h].getText() == board[i][j].getText() && checkCorret(i, j)) {
			d++;
			h++;
			if (h > bGame.row - 1) {
				break;
			}
		}
		if (j == 0) {
			h = 0;
		} else {
			h = j - 1;
			if (h < 0) {
				h = 0;
			}
		}
		while (board[i][h].getText() == board[i][j].getText() && checkCorret(i, j)) {
			d++;
			h--;
			if (h < 0) {
				break;
			}
		}
		if (d > 4)
			return true;
		// kiá»ƒm tra Ä‘Æ°á»�ng chĂ©o 1
		h = i;
		k = j;
		d = 0;
		while (board[i][j].getText() == board[h][k].getText() && checkCorret(i, j)) {
			d++;
			h++;
			k++;
			if (k >= bGame.col || h >= bGame.col) {
				break;
			}
		}
		h = i - 1;// h = i -1
		k = j - 1;// k = j -1
		if (h < 0)
			h = 0;
		if (k < 0)
			k = 0;
		while (board[i][j].getText() == board[h][k].getText() && checkCorret(i, j)) {
			d++;
			h--;
			k--;
			if (k < 0 || h < 0) {
				break;
			}
		}
		if (d > 4)
			return true;

//		h = i;// 
		k = j;//
		d = 0;
		if (i == bGame.col - 1) {
			h = bGame.col - 1;
		} else {
			h = i;
		}
		while (board[i][j].getText() == board[h][k].getText() && checkCorret(i, j)) {
			d++;
			h++;
			k--;
			if (k < 0 || h > bGame.row - 1) {
				break;
			}
		}
		h = i - 1;// h = i -1
		if (h < 0)
			h = 0;
		if (j == bGame.col - 1) {
			k = bGame.col - 1;
		} else {
			k = j + 1;
			if (k > bGame.col - 1) {
				k = bGame.col - 1;
			}
		} // moithem+1
		while (board[i][j].getText() == board[h][k].getText() && checkCorret(i, j)) {
			d++;
			h--;
			k++;
			if (h >= bGame.row || k >= bGame.col) {
				break;
			}
		}

		if (d > 4)
			return true;

		// náº¿u khĂ´ng Ä‘Æ°Æ¡ng chĂ©o nĂ o thá»�a mĂ£n thĂ¬
		// tráº£ vá»� false.
		return false;
	}

	public JButton[][] placeBoard(JPanel panel) {
		JButton[][] board = new JButton[bGame.row][bGame.col];
		for (int i = 0; i < bGame.row; i++) {
			for (int j = 0; j < bGame.col; j++) {
				board[i][j] = new JButton();
				panel.add(board[i][j]);
			}
		}
		return board;
	}

	//
	public int checkEnd(int row, int col) {
		int r = 0, c = 0;
		int i;
		boolean human, pc;
		// Check hang ngang
		while (c < bGame.row - 4) {
			human = true;
			pc = true;
			for (i = 0; i < 5; i++) {
				if (bGame.getBoard()[row][c + i] != 1)
					human = false;
				if (bGame.getBoard()[row][c + i] != 2)
					pc = false;
			}
			if (human)
				return 1;
			if (pc)
				return 2;
			c++;
		}

		// Check hang doc
		while (r < bGame.col - 4) {
			human = true;
			pc = true;
			for (i = 0; i < 5; i++) {
				if (bGame.getBoard()[r + i][col] != 1)
					human = false;
				if (bGame.getBoard()[r + i][col] != 2)
					pc = false;
			}
			if (human)
				return 1;
			if (pc)
				return 2;
			r++;
		}

		// Check duong cheo xuong
		r = row;
		c = col;
		while (r > 0 && c > 0) {
			r--;
			c--;
		}
		while (r < bGame.col - 4 && c < bGame.row - 4) {
			human = true;
			pc = true;
			for (i = 0; i < 5; i++) {
				if (bGame.getBoard()[r + i][c + i] != 1)
					human = false;
				if (bGame.getBoard()[r + i][c + i] != 2)
					pc = false;
			}
			if (human)
				return 1;
			if (pc)
				return 2;
			r++;
			c++;
		}

		// Check duong cheo len
		r = row;
		c = col;
		while (r < bGame.col - 1 && c > 0) {
			r++;
			c--;
		}

		while (r >= 4 && c < bGame.col - 4) {
			human = true;
			pc = true;
			for (i = 0; i < 5; i++) {
				if (bGame.getBoard()[r - i][c + i] != 1)
					human = false;
				if (bGame.getBoard()[r - i][c + i] != 2)
					pc = false;
			}
			if (human)
				return 1;
			if (pc)
				return 2;
			r--;
			c++;
		}
		return 0;
	}

	//

}
