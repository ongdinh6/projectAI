package controller;

import model.Player;

public interface IController {
	public boolean isValid(int i, int j);

	public int[][] moved(Player player, int i, int j);

	public boolean isOver(int[][] board);

}
