package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // NORTHWEST
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        // while square is empty
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // set square as possible move
            // change value to next possible square to be checked
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        // if position exists && it's an opponent's piece
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // true == it's a possible move
        }

        // NORTHEAST
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        // while square is empty
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // set square as possible move
            // change value to next possible square to be checked
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        // if position exists && it's an opponent's piece
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // true == it's a possible move
        }

        // SOUTHWEST
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        // while square is empty
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // set square as possible move
            // change value to next possible square to be checked
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        // if position exists && it's an opponent's piece
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // true == it's a possible move
        }

        // SOUTHEAST
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        // while square is empty
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // set square as possible move
            // change value to next possible square to be checked
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        // if position exists && it's an opponent's piece
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // true == it's a possible move
        }

        return mat;
    }
}
