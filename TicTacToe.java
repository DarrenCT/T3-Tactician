import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' }, };
        printGameBoard(gameBoard);
        // Scan user input, prompt for input between 1-9

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9)");
            int playerPos = scan.nextInt();

            // System.out.println(position);

            placePiece(gameBoard, playerPos, "player");
            System.out.println();

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;

            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);
            System.out.println();
        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
        } else if (user.equals("cpu")) {
            symbol = 'O';
        }
        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List mainDiag = Arrays.asList(1, 5, 9);
        List secDiag = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(mainDiag);
        winningConditions.add(secDiag);

        for (List l : winningConditions) {
            if (playerPositions.containsAll(l)) {
                return "You won!!!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU won...";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Tie";
            }
        }

        return "";
    }

}