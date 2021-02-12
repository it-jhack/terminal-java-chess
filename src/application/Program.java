package application;

import chess.ChessMatch;
import boardgame.Board;

public class Program {

	public static void main(String[] args) {

		ChessMatch chessMatch = new ChessMatch();

		// UI: User Interface Class
		UI.printBoard(chessMatch.getPieces());


	}

}
