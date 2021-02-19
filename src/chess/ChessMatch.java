package chess;

import boardgame.Board;

import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    // Creates the match - Standard chess board is 8 x 8.
    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
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

    public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
        Position source = sourcePos.toPosition();
        Position target = targetPos.toPosition();

        // Checking if there was really a piece on source position
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target); //TODO makeMove()
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position!");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        // White Pieces
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        // Black Pieces
        placeNewPiece('e', 8, new King(board, Color.BLACK));
    }
}
