package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    // Pawn's access to match, because of En Passant special move
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // if moveCount() == 0 ∴ can move two rows forward
        // otherwise only one forward, or capture on diagonal
        if (getColor() == Color.WHITE) {
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // Pawn cannot "jump" over another piece to move two squares
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            p.setValues(position.getRow() - 2, position.getColumn());
            if (getBoard().positionExists(p)
                    && !getBoard().thereIsAPiece(p) // if second square is free
                    && getBoard().positionExists(p2) // if first square is free
                    && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // if opponent's piece is in either frontal diagonal square, pawn can capture
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // En Passant special move (white)
            // logically, white pawn at rank 5 (row 3) is a must for it to be able to perform En Passant
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) // there's a piece on the left
                        && isThereOpponentPiece(left) // it's an opponent's piece
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) { // it's vulnerable
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right)
                        && isThereOpponentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        }
        else if (getColor() == Color.BLACK) {
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // Pawn cannot "jump" over another piece to move two squares
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            p.setValues(position.getRow() + 2, position.getColumn());
            if (getBoard().positionExists(p)
                    && !getBoard().thereIsAPiece(p) // if second square is free
                    && getBoard().positionExists(p2) // if first square is free
                    && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // if opponent's piece is in either frontal diagonal square, pawn can capture
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // En Passant special move (black)
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left)
                        && isThereOpponentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right)
                        && isThereOpponentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        // TODO Promotion: pawn becomes another piece by reaching other side of board

        // TODO "en passant" special capture
        // "En passant" is when a pawn moves two squares and passes by a square
        // being attacked by opponent's pawn. This special capture can only be
        // performed in the play immediately after pawn has advanced.
        return mat;
    }
}

