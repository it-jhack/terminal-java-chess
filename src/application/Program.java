package application;

import chess.ChessException;
import chess.ChessMatch;
import boardgame.Board;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) { // TODO change 'true' for '!checkmate' condition
			try {
				// UI: User Interface
				UI.clearConsole();
				UI.printMatch(chessMatch);
				System.out.print("\nSource: ");
				ChessPosition source = UI.readChessPosition(sc);

				//showing possible moves on different color
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearConsole();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				System.out.println("Press Enter to continue");
				sc.nextLine(); // wait to press 'Enter' after error
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Press Enter to continue");
				sc.nextLine(); // wait to press 'Enter' after error
			}



		}

		// sc.close(); TODO uncomment after changing 'true' for '!checkmate' condition on 'while (true) {...}'
	}

}
