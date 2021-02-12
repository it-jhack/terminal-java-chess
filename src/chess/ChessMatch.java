package chess;

import boardgame.Board;

public class ChessMatch {

    private Board board;

    // Creates the match - Standard chess board is 8 x 8.
    public ChessMatch() {
        board = new Board(8, 8);
    }

    // Returns a matrix of chess pieces corresponding to this match.
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat [i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

}
