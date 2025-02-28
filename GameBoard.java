import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameBoard extends JFrame {
    private static final int SIZE = 8; // Size of chessboard
    private JPanel[][] squares = new JPanel[SIZE][SIZE]; // 2D array for the chessboard
    private String[][] piecesArray; // 2D array to store piece names, colors, and positions

    public GameBoard() {
        setTitle("Chessboard");
        setSize(750, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE)); // Use GridLayout for the chessboard layout

        // Initialize the 2D array of panels (for squares)
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel();
                // Set alternate colors for the chessboard squares (black and white)
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(Color.WHITE); // White squares
                } else {
                    squares[row][col].setBackground(Color.BLACK); // Black squares
                }
                add(squares[row][col]); // Add each square to the board
            }
        }

        // Initialize the pieces array (you can add more pieces as needed)
        piecesArray = new String[32][3]; // Array to store piece name, color, and position
        loadPieces(); // Load the chess pieces into the array

        // Initially populate the board with pieces
        populateBoard();
    }

    // Merge Sort to arrange pieces based on their positions on the chessboard
    public void mergeSort(String[][] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);  // Sort the left half
            mergeSort(array, mid + 1, right); // Sort the right half
            merge(array, left, mid, right);  // Merge the two halves
        }
    }

    // Merge method used by mergeSort
    private void merge(String[][] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        String[][] leftArray = new String[n1][3];
        String[][] rightArray = new String[n2][3];

        // Copy data to temporary arrays leftArray[] and rightArray[]
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Merge the temporary arrays back into array[]
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (Integer.parseInt(leftArray[i][2]) <= Integer.parseInt(rightArray[j][2])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of leftArray[], if any
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy the remaining elements of rightArray[], if any
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Populate the board with pieces after sorting
    private void populateBoard() {
        //mergeSort(piecesArray, 0, piecesArray.length - 1); // Sort pieces based on position

        int pieceRow = 0; // Keeps track of the number of pieces
        int squareName = 0; // All squares are numbered 1-64

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int boardPosition = Integer.parseInt(piecesArray[pieceRow][2]);

                // If the square number matches the piece position, place the piece
                if (squareName == boardPosition) {
                    String imagePath = piecesArray[pieceRow][0]; // Save the image path
                    String pieceName = piecesArray[pieceRow][1]; // Save the piece name

                    ImageIcon icon = new ImageIcon(imagePath);
                    Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

                    JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                    JLabel textLabel = new JLabel(pieceName, SwingConstants.CENTER);
                    textLabel.setForeground(Color.BLACK); // Make text black and centered at the bottom

                    JPanel piecePanel = new JPanel(new BorderLayout());
                    piecePanel.setOpaque(false); // Transparent background
                    piecePanel.add(pieceLabel, BorderLayout.CENTER);
                    piecePanel.add(textLabel, BorderLayout.SOUTH);

                    squares[row][col].setLayout(new BorderLayout());
                    squares[row][col].add(piecePanel, BorderLayout.CENTER);

                    pieceRow++; // Move to the next piece
                }
                squareName++; // Move to the next square
            }
        }

        revalidate(); // Ensure layout updates
        repaint(); // Refresh view
    }

    // Load chess pieces into the pieces array
    private void loadPieces() {
        // Initialize chess pieces with their names, colors, and positions
        piecesArray[0][0] = "rook_white.png"; piecesArray[0][1] = "White Rook"; piecesArray[0][2] = "0";
        piecesArray[1][0] = "whiteknight.png"; piecesArray[1][1] = "White Knight"; piecesArray[1][2] = "1";
        piecesArray[2][0] = "bishop_white.png"; piecesArray[2][1] = "White Bishop"; piecesArray[2][2] = "2";
        piecesArray[3][0] = "queen_white.png"; piecesArray[3][1] = "White Queen"; piecesArray[3][2] = "3";
        piecesArray[4][0] = "king_white.png"; piecesArray[4][1] = "White King"; piecesArray[4][2] = "4";
        piecesArray[5][0] = "bishop_white.png"; piecesArray[5][1] = "White Pawn"; piecesArray[5][2] = "5";
        piecesArray[6][0] = "whiteknight.png"; piecesArray[6][1] = "White Pawn"; piecesArray[6][2] = "6";
        piecesArray[7][0] = "rook_white.png"; piecesArray[7][1] = "White Pawn"; piecesArray[7][2] = "7";
        piecesArray[8][0] = "pawn_white.png"; piecesArray[8][1] = "White Pawn"; piecesArray[8][2] = "8";
        piecesArray[9][0] = "pawn_white.png"; piecesArray[9][1] = "White Pawn"; piecesArray[9][2] = "9";
        piecesArray[10][0] = "pawn_white.png"; piecesArray[10][1] = "White Pawn"; piecesArray[10][2] = "10";
        piecesArray[11][0] = "pawn_white.png"; piecesArray[11][1] = "White Pawn"; piecesArray[11][2] = "11";
        piecesArray[12][0] = "pawn_white.png"; piecesArray[12][1] = "White Pawn"; piecesArray[12][2] = "12";
        piecesArray[13][0] = "pawn_white.png"; piecesArray[13][1] = "White Pawn"; piecesArray[13][2] = "13";
        piecesArray[14][0] = "pawn_white.png"; piecesArray[14][1] = "White Pawn"; piecesArray[14][2] = "14";
        piecesArray[15][0] = "pawn_white.png"; piecesArray[15][1] = "White Pawn"; piecesArray[15][2] = "15";
        piecesArray[16][0] = "pawn_black.png"; piecesArray[16][1] = "Black Rook"; piecesArray[16][2] = "48";
        piecesArray[17][0] = "pawn_black.png"; piecesArray[17][1] = "Black Rook"; piecesArray[17][2] = "49";
        piecesArray[18][0] = "pawn_black.png"; piecesArray[18][1] = "Black Rook"; piecesArray[18][2] = "50";
        piecesArray[19][0] = "pawn_black.png"; piecesArray[19][1] = "Black Rook"; piecesArray[19][2] = "51";
        piecesArray[20][0] = "pawn_black.png"; piecesArray[20][1] = "Black Rook"; piecesArray[20][2] = "52";
        piecesArray[21][0] = "pawn_black.png"; piecesArray[21][1] = "Black Rook"; piecesArray[21][2] = "53";
        piecesArray[22][0] = "pawn_black.png"; piecesArray[22][1] = "Black Rook"; piecesArray[22][2] = "54";
        piecesArray[23][0] = "pawn_black.png"; piecesArray[23][1] = "Black Rook"; piecesArray[23][2] = "55";
        piecesArray[24][0] = " BlackRook.png"; piecesArray[24][1] = "Black Pawn"; piecesArray[24][2] = "56";
        piecesArray[25][0] = "knight_black.png"; piecesArray[25][1] = "Black Knight"; piecesArray[25][2] = "57";
        piecesArray[26][0] = "bishop_black.png"; piecesArray[26][1] = "Black Bishop"; piecesArray[26][2] = "58";
        piecesArray[27][0] = "queen_black.png"; piecesArray[27][1] = "Black Queen"; piecesArray[27][2] = "59";
        piecesArray[28][0] = "king_black.png"; piecesArray[28][1] = "Black King"; piecesArray[28][2] = "60";
        piecesArray[29][0] = "bishop_black.png"; piecesArray[29][1] = "Black knight"; piecesArray[29][2] = "61";
        piecesArray[30][0] = "knight_black.png"; piecesArray[30][1] = "Black knight"; piecesArray[30][2] = "62";
        piecesArray[31][0] = " BlackRook.png"; piecesArray[31][1] = "Black Rook"; piecesArray[31][2] = "63";
         


         
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}

    

