package application;

import chess.ChessMatch;
import boardgame.Board;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) { // TODO change 'true' for '!checkmate' condition
			// UI: User Interface
			UI.clearConsole();
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);

			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);

			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

		}

		// sc.close(); TODO uncomment after changing 'true' for '!checkmate' condition on 'while (true) {...}'
	}

}
