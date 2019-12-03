import java.util.Scanner;
// Cameron was here for lab 11
// Cameron was here again 
public class ConnectFour {
    // prints the board
    public static void printBoard(char[][] array) {
        for (int i = (array.length - 1); i >= 0; --i) {
            for (int j = 0; j < array[0].length; ++j) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // sets each spot in the array to "-"
    public static void initializeBoard(char[][] array) {
        // iterates through all columns/ rows, placing a - and space for each spot in the array, forming a grid of hyphens
        for (int row = (array.length - 1); row >= 0; --row) {
            for (int col = 0; col < array[0].length; ++col) {
                array[row][col] = '-';
                System.out.print(array[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Places token in chosen column, finds next available spot if there are already tokens in column, returns row
    public static int insertChip(char[][] array, int col, char chipType) {
        // finds the lowest hyphen in the column chosen and places the character chipType there
        for (int i = 0; i < array.length; ++i) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                return i;
            }
        }
        // this will only be the result if column is already full
        return -1;
    }

    // Checks whether token creates 4 in a row of this chip type. Returns true if there are 4 in a row
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int i;
        int count = 0;
        // checks if there are four continuous characters of chipType in any row
        for (i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[0].length; ++j) {
                if (array[i][j] == chipType) {
                    ++count;
                    if (count == 4) {
                        return true;
                    }
                }
                else
                    count = 0;
            }
            count = 0;
        }
        // checks if there are any four continuous characters of chipType in any col
        count = 0;
        for (int j = 0; j < array[0].length; ++j) {
            for (i = 0; i < array.length; ++i) {
                if (array[i][j] == chipType) {
                    ++count;
                    if (count >= 4) {
                        return true;
                    }
                }
                else
                    count = 0;
            }
            count = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int col, row;
        char chipType;
        boolean isItOver = false;

        System.out.print("What would you like the height of the board to be? ");
        row = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        col = scnr.nextInt();

        char[][] array = new char[row][col];

        // initializes game, tells players
        initializeBoard(array);
        System.out.println();
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        row = 0;
        // loops until game comes to an end
        while (!isItOver) {
            // player 1's turn
            System.out.print("Player 1: Which column would you like to choose? ");
            col = scnr.nextInt();
            chipType = 'x';

            // calls insert chip method, if column is full it will assume the game is a draw
            insertChip(array, col, chipType);

            printBoard(array);

            // checks if game is over
            if (checkIfWinner(array, col, row, chipType)) {
                System.out.println("Player 1 won the game!");
                break;
            }
            // checks if the board is full without a winner (yielding a draw)
            if (isFilled(array)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }

            // player 2's turn
            System.out.print("Player 2: Which column would you like to choose? ");
            col = scnr.nextInt();
            chipType = 'o';
            // calls insert chip method, if column is full it will assume the game is a draw
            insertChip(array, col, chipType);

            printBoard(array);
            // checks if game is over
            if (checkIfWinner(array, col, row, chipType)) {
                System.out.println("Player 2 won the game!");
                break;
            }
            // checks if the board is full without a winner (yielding a draw)
            if (isFilled(array)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
    }

    // checks at beginning of loop if the board is full
    public static boolean isFilled(char[][] array) {
        // nested for loop iterates through each character in the array
        for (char[] i : array) {
            for (char j : i) {
                // if there is still a hyphen, it cannot yet be a draw
                if (j == '-') {
                    return false;
                }
            }
        }
        // if no one has won and the entire board is full, it's a draw
        return true;
    }

}
