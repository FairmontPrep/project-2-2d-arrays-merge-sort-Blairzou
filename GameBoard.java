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

    // Sort the piecesArray using Arrays.sort and place pieces accordingly
    private void sortAndPopulateBoard() {
        // Sort piecesArray based on the position (index 2) in ascending order
        Arrays.sort(piecesArray, (piece1, piece2) -> Integer.compare(Integer.parseInt(piece1[2]), Integer.parseInt(piece2[2])));

        // Populate the board with sorted pieces
        int pieceIndex = 0; // Keeps track of the current piece

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int boardPosition = row * SIZE + col;

                // Check if the current square should have a piece
                if (pieceIndex < piecesArray.length) {
                    String[] piece = piecesArray[pieceIndex]; // Get the current piece
                    int piecePosition = Integer.parseInt(piece[2]); // Position of the current piece

                    // If the piece's position matches the current board square, place the piece
                    if (piecePosition == boardPosition) {
                        String imagePath = piece[0]; // Get the image path for the piece
                        String pieceName = piece[1]; // Get the piece's name

                        // Create the ImageIcon and scale the image
                        ImageIcon icon = new ImageIcon(imagePath);
                        Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));

                        // Add the image label to the board square
                        squares[row][col].removeAll();  // Remove existing components from the square
                        squares[row][col].add(pieceLabel);  // Add the new piece label
                        squares[row][col].revalidate();  // Revalidate the layout
                        squares[row][col].repaint();  // Refresh the square

                        pieceIndex++;  // Move to the next piece in the sorted list
                    }
                }
            }
        }

        revalidate(); // Ensure layout updates
        repaint(); // Refresh view
    }

    // Populate the board with pieces after sorting
    private void populateBoard() {
        sortAndPopulateBoard();
    }

    // Load chess pieces into the pieces array
    private void loadPieces() {
        // Initialize chess pieces with their names, colors, and positions
        piecesArray[0][0] = "rook_white.png"; piecesArray[0][1] = "White Rook"; piecesArray[0][2] = "0";
        piecesArray[1][0] = "bishop_white.png"; piecesArray[1][1] = "White Bishop"; piecesArray[1][2] = "2";
        piecesArray[2][0] = "whiteknight.png"; piecesArray[2][1] = "White Knight"; piecesArray[2][2] = "1";
        piecesArray[3][0] = "king_white.png"; piecesArray[3][1] = "White King"; piecesArray[3][2] = "4";
        piecesArray[4][0] = "queen_white.png"; piecesArray[4][1] = "White Queen"; piecesArray[4][2] = "3";
        piecesArray[5][0] = "rook_white.png"; piecesArray[5][1] = "White Pawn"; piecesArray[5][2] = "7";
        piecesArray[6][0] = "whiteknight.png"; piecesArray[6][1] = "White Pawn"; piecesArray[6][2] = "6";
        piecesArray[7][0] = "bishop_white.png"; piecesArray[7][1] = "White Pawn"; piecesArray[7][2] = "5";
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
        piecesArray[29][0] = "bishop_black.png"; piecesArray[29][1] = "Black Knight"; piecesArray[29][2] = "61";
        piecesArray[30][0] = "knight_black.png"; piecesArray[30][1] = "Black Knight"; piecesArray[30][2] = "62";
        piecesArray[31][0] = " BlackRook.png"; piecesArray[31][1] = "Black Rook"; piecesArray[31][2] = "63";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}



