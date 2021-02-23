package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() { //TODO delete generic possibleMoves(); implement specific logic;
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // ABOVE
        // setting first position value above rook
        p.setValues(position.getRow() - 1, position.getColumn());
        // while square above is empty
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // set square as possible move
            p.setRow(p.getRow() - 1); // decrement to check next row above
        }
        // if position exists && it's an opponent's piece
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true; // true == it's a possible move
        }

        // BELOW
        p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // LEFT
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // RIGHT
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

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