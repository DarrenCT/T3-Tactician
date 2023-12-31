import java.util.*;

public class TicTacToe {
	private static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	private static ArrayList<Integer> aiPositions = new ArrayList<Integer>();
	private static List<List> winningConditions = new ArrayList<List>();
	private static char[] gameBoard = new char[9];
	private static final char player = 'X', ai ='O';
	
	public static void main(String[] args) {
		initiateWinCon();
		//initialize the empty gameBoard
		for(int i = 0; i < gameBoard.length; i++) {
			gameBoard[i] = ' ';
		}
		printGameBoard(gameBoard);

		while (true) {
			// prompt human player for input
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9)");
			int playerPos = scan.nextInt();
			// make sure human player chooses an empty position, else prompt for another input
			while (playerPositions.contains(playerPos) || aiPositions.contains(playerPos)) {
				System.out.println("Position taken, enter valid position");
				playerPos = scan.nextInt();
			}
			// place piece to the desired position
			placePiece(gameBoard, playerPos, "player");
			// check to see if the move results in a winning condition
			String result = checkWinner();
			if (!result.isEmpty()) {
				System.out.println(result);
				break;
			}
			System.out.println();

			int bestScore = Integer.MIN_VALUE;
	        int bestMove = -1;

	        for (int i = 0; i < gameBoard.length; i++) {
	            if (gameBoard[i] == ' ') {
	            	//guess next move
	                gameBoard[i] = ai;
	                int score = minimax(gameBoard, 0, false);
	                //undo guessing
	                gameBoard[i] = ' ';

	                if (score > bestScore) {
	                    bestScore = score;
	                    bestMove = i + 1;
	                }
	            }
	        }

	        if (bestMove != -1) {
	        	 placePiece(gameBoard, bestMove, "ai");
	        	
	        }
	        printGameBoard(gameBoard);
			// check to see if there is a winner after ai's turn
			result = checkWinner();
			if (!result.isEmpty()) {
				System.out.println(result);
				break;
			}
		}

	}

	public static void printGameBoard(char[] gameBoard) {
		for(int i = 0; i < gameBoard.length; i++) {
			if(i == 0 || i == 3 || i == 6) {
				System.out.print(gameBoard[i]);
				System.out.print('|');
			} else if (i == 1 || i == 4 || i == 7) {
				System.out.print(gameBoard[i]);
				System.out.print('|');
			} else if (i == 2 || i == 5) {
				System.out.println(gameBoard[i]);
				System.out.println("-+-+-");
			} else {
				System.out.print(gameBoard[i]);
			}
		}
		System.out.println();
	}

	public static void placePiece(char[] gameBoard, int position, String user) {
		// set piece symbol based on the current player
		char symbol = ' ';
		if (user.equals("player")) {
			symbol = player;
			playerPositions.add(position);
		} else if (user.equals("ai")) {
			symbol = ai;
			aiPositions.add(position);
		}
		// place piece on the corresponding location on the gameboard
		switch (position) {
		case 1:
			gameBoard[0] = symbol;
			break;
		case 2:
			gameBoard[1] = symbol;
			break;
		case 3:
			gameBoard[2] = symbol;
			break;
		case 4:
			gameBoard[3] = symbol;
			break;
		case 5:
			gameBoard[4] = symbol;
			break;
		case 6:
			gameBoard[5] = symbol;
			break;
		case 7:
			gameBoard[6] = symbol;
			break;
		case 8:
			gameBoard[7] = symbol;
			break;
		case 9:
			gameBoard[8] = symbol;
			break;
		default:
			break;
		}

	}

	public static void initiateWinCon() {
		// define winning conditions
				List topRow = Arrays.asList(1, 2, 3);
				List midRow = Arrays.asList(4, 5, 6);
				List botRow = Arrays.asList(7, 8, 9);
				List leftCol = Arrays.asList(1, 4, 7);
				List midCol = Arrays.asList(2, 5, 8);
				List rightCol = Arrays.asList(3, 6, 9);
				List mainDiag = Arrays.asList(1, 5, 9);
				List secDiag = Arrays.asList(3, 5, 7);

				// Create a list of wining conditions and add all winning conditions to the list
				winningConditions.add(topRow);
				winningConditions.add(midRow);
				winningConditions.add(botRow);
				winningConditions.add(leftCol);
				winningConditions.add(midCol);
				winningConditions.add(rightCol);
				winningConditions.add(mainDiag);
				winningConditions.add(secDiag);
	}
	
	public static String checkWinner() {
		// check to see if current player positions contains all position on game board
		// corresponding to a giving win con
		for (List l : winningConditions) {
			if (playerPositions.containsAll(l)) {
				return "You won!!!";
			} else if (aiPositions.containsAll(l)) {
				return "CPU won...";
			} else if (playerPositions.size() + aiPositions.size() == 9) {
				return "Tie";
			}
		}

		return "";
	}
	
	//minimax algorithm
	public static int minimax(char[] gameBoard, int depth, boolean isMaximizing) {
        int score = evaluate(gameBoard);
        if (score != 0) return score;
        if (isFull(gameBoard)) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i] == ' ') {
                    gameBoard[i] = ai;
                    int currentScore = minimax(gameBoard, depth + 1, false);
                    gameBoard[i] = ' ';
                    bestScore = Math.max(currentScore, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < gameBoard.length; i++) {
                if (gameBoard[i] == ' ') {
                    gameBoard[i] = player;
                    int currentScore = minimax(gameBoard, depth + 1, true);
                    gameBoard[i] = ' ';
                    bestScore = Math.min(currentScore, bestScore);
                }
            }
            return bestScore;
        }
    }
	
	public static int evaluate(char[] board) {
        for (List<Integer> condition : winningConditions) {
            if (board[condition.get(0) - 1] == board[condition.get(1) - 1]
                    && board[condition.get(1) - 1] == board[condition.get(2) - 1]) {
                if (board[condition.get(0) - 1] == ai) {
                    return +10;
                } else if (board[condition.get(0) - 1] == player) {
                    return -10;
                }
            }
        }
        return 0;
    }
	
	public static boolean isFull(char[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }
}