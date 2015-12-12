public class TicTacToe {

	public static void main(String[] args) {
		long k = System.nanoTime();
		int[][] board;
		if (args.length == 0)
			board = new int[3][3];
		else {
			int s;
			try {
				s = Integer.parseInt(args[0]);
			} catch (Exception e) {
				s = 3;
			}
			board = new int[s][s];
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		System.out.println(addPiece(board, true, 0));
		System.out.println((System.nanoTime() - k) / 1E9 + " seconds to run");
	}

	public static int addPiece(int[][] board, boolean turn, int turns) {
		int result = evaluate(board, turn, turns);
		if (result != 0)
			return result;
		else if (turns == board.length * board[0].length && result == 0)
			return result;
		int best;
		if (turn)
			best = -1;
		else
			best = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != 0)
					continue;
				if (turn)
					board[i][j] = 1;
				else
					board[i][j] = -1;
				int result2 = addPiece(board, !turn, turns + 1);
				board[i][j] = 0;
				if (turn && result2 == 1)
					return 1;
				else if (!turn && result2 == -1)
					return -1;
				if (result2 > best && turn)
					best = result2;
				else if (result2 < best && !turn)
					best = result2;
			}
		}
		return best;
	}

	public static int evaluate(int[][] board, boolean turn, int depth) {
		int color;
		if (turn)
			color = 1;
		else
			color = -1;
		boolean allSame = true;
		for (int i = 0; i < board.length; i++) {
			allSame = true;
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != color) {
					allSame = false;
					break;
				}
			}
			if (allSame)
				return color;
		}
		for (int i = 0; i < board[0].length; i++) {
			allSame = true;
			for (int j = 0; j < board.length; j++) {
				if (board[j][i] != color) {
					allSame = false;
					break;
				}
			}
			if (allSame)
				return color;
		}
		allSame = true;
		for (int i = 0; i < board.length; i++) {
			if (board[i][board.length - i - 1] != color) {
				allSame = false;
				break;
			}
		}
		if (allSame)
			return color;
		allSame = true;
		for (int i = 0; i < board.length; i++) {
			if (board[i][i] != color) {
				allSame = false;
				break;
			}
		}
		if (allSame)
			return color;
		return 0;
	}
}