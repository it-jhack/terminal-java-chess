package application;

import chess.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            // Print line number coordinates
            System.out.print((8 - i)+ " ");

            // Print chess pieces in line
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]);
            }
            // Board line is over, so it needs to break line
            System.out.println();
        }
        // Print row letter coordinates at the bottom of the board
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            // This is the empty square
            System.out.print("-");
        }
        else {
            System.out.print(piece);
        }
        // Spaces between to avoid pieces being visually too crammed
        System.out.print(" ");
    }
}
