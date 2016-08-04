package guessthenumber;

public class GuessTheNumber {

	private int numberToGuess = (int) (Math.random() * 10 + 1);

	public int getNumberToGuess() {
		return numberToGuess;
	}

	public enum clues {
		bigger, smaller, bingo, newGame
	}

	public clues compareNumbers(int numberFromPlayer) {
		clues solved = null;
		if (numberFromPlayer > numberToGuess) {
			solved = clues.smaller;
		} else if (numberFromPlayer < numberToGuess) {
			solved = clues.bigger;
		} else if (numberFromPlayer == numberToGuess) {
			solved = clues.bingo;
		}
		return solved;
	}

}
