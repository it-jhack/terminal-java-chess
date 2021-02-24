package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);

        // if null || not your own piece
        return p == null || p.getColor() != getColor();
    }

    // CASTLING (see comments at the end of this Class for reference)
    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // Above King
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // Below King
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // King's Right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // King's Left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // North East
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // North West
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // South East
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // South West
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // CASTLING (see comments at the end of this Class for reference)
        // TODO create logic (on castling) that king cannot pass through square under attack
        if(getMoveCount() == 0 && !chessMatch.getCheck()) {
            // King's side Castling
            Position posRook1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posRook1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1); // (King's) right square
                Position p2 = new Position(position.getRow(), position.getColumn() + 2); // second square to the right
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) { // if both == null
                    // Add g1 (white) or g8 (black) as a possible move for the King
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            // Queen's side Castling
            Position posRook2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posRook2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1); // (King's) left square
                Position p2 = new Position(position.getRow(), position.getColumn() - 2); // 2nd square to the left
                Position p3 = new Position(position.getRow(), position.getColumn() - 3); // 3rd square to the left
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) { // if all == null
                    // Add c1 (white) or c8 (black) as a possible move for the King
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }


        }

        return mat;
    }
}

/*
 CASTLING: King moves 2 squares towards a Rook,
 which moves to square that the King passed (all at the same play)

 Initial set:
 R - - - K - - R

 Queen's side Castle
 - - K R - - - R

 King's side Castle
 R - - - - R K -

 CANNOT be done if:
 - There is any piece between King and castling Rook
 - King has already moved
 - Rook participating in the Castle has already moved
 - King is in check
 - Square that the king passes (which Rook goes to) is under attack

 Castling CAN be done if (common misconceptions):
 - King was in check earlier, but did NOT move to get out of check
 - Rook is under attack
 - Square that Rook passes through is under attack
*/

