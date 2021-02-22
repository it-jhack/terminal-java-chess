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

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    // in chess, turn++ when both players have already played, NOT at each play
    private Integer turn = 1;
    private Color currentPlayer = Color.WHITE;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    // Creates the match - Standard chess board is 8 x 8.
    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePos, ChessPosition targetPos) {
        Position source = sourcePos.toPosition();
        Position target = targetPos.toPosition();

        // Checking if there was really a piece on source position
        validateSourcePosition(source);

        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position!");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("The selected piece is not yours!");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There are no possible moves for this piece!");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        // if for given source piece, target is NOT possible move
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The selected piece cannot move to that target position!");
        }
    }

    private void nextTurn() {
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
        if (currentPlayer == Color.WHITE) {
            turn++; // in chess, turn++ when both players have already played, NOT at each play
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup() {

        // White Pieces
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));

        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));

//        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
//        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('d', 1, new Queen(board, Color.WHITE));

        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        // Black Pieces
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));

        placeNewPiece('e', 8, new King(board, Color.BLACK));

        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));

//        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
//        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

        placeNewPiece('d', 8, new Queen(board, Color.BLACK));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
    }
}
