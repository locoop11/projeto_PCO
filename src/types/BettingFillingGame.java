package types;
/**
 * A BettingFillingGame is a game where the player has a limited number of plays
 * 
 * Grupo 11
 * 
 * Hugo Silva - nº60253
 * Mariana Foz - nº58955
 * Natacha Neves - nº58649
 * 
 */

public class BettingFillingGame extends AbstractFillingGame {
	private int bet;
	private int maxjogadas;
	private int score;
	private int initScore;
	private int playsLeft;

	/**
	 * Constructor for objects of class BettingFillingGame
	 * 
	 * @param symbols             - Array of fillings that can be used in the game.
	 * @param numberOfUsedSymbols - Number of fillings that will be used in the
	 * @param seed                - Seed for randomization.
	 * @param bootleSize          - Size of each bottle.
	 * @param score               - Initial score.
	 * @param bet                 - Bet for each finishing the game.
	 * @param maxjogadas          - Maximum number of plays.
	 */
	public BettingFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed,
			int bottleSize, int score, int bet, int maxjogadas) {

		super(symbols, numberOfUsedSymbols, seed, bottleSize);
		this.score = score;
		this.initScore = score;
		this.bet = bet;
		this.maxjogadas = maxjogadas;
		this.playsLeft = maxjogadas;

	}

	/**
	 * Creates a new bottle of type cup and adds it to the table.
	 * 
	 */
	@Override
	public void provideHelp() {
		if (jogadas < maxjogadas) {
			Bottle newBottle = new Cup(); // Assuming Cup is a subclass of Bottle
			table.addBootle(newBottle);
			this.playsLeft--;
			if (this.isRoundFinished()) {
				this.updateScore();
			}
		}
	}

	/**
	 * get the score
	 */
	@Override
	public int score() {
		return this.score;
	}

	/**
	 * Plays by pouring from bottle x to bottle y.
	 * 
	 * @param x - Position of the bottle to get filling from.
	 * @param y - Position of the bottle to get filling to.
	 */
	@Override
	public void play(int x, int y) {
		super.play(x, y);
		this.playsLeft--;
	}

	/**
	 * Checks if the round is finished.
	 */
	@Override
	public boolean isRoundFinished() {
		if (areAllFilled() || this.playsLeft == 0) {
			this.finished = true;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the number of plays left.
	 * 
	 * @return - Returns the number of plays left.
	 */
	public int numberOfPlaysLeft() {
		return this.playsLeft;
	}

	/**
	 * Creates a new bottle
	 * 
	 * @return - Returns a new bottle.
	 */
	@Override
	public Bottle getNewBootle() {
		return new Cup();
	}

	/**
	 * Updates the score according to the number of plays used. If the round is not
	 * finished, the score is not updated.
	 * 
	 */
	@Override
	public void updateScore() {
		if (isRoundFinished()) {
			if (areAllFilled()) {
				int numberOfjogadas = this.jogadas();
				if (numberOfjogadas <= this.maxjogadas) {
					this.score = (2 * this.bet) + this.initScore;
				}
			} else {
				this.score = this.initScore - 100;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		updateScore();
		res.append("Score: " + this.score + AbstractFillingGame.EOL);
		res.append(super.toString());
		if (this.isRoundFinished()) {
			res.append("Status: This round is finished." + AbstractFillingGame.EOL);
			res.append(this.jogadas + " moves were used." + AbstractFillingGame.EOL);
		} else {
			res.append("Status: " + this.jogadas + " moves have been used until now. You still have "
					+ numberOfPlaysLeft() + " moves left." + AbstractFillingGame.EOL);
		}

		return res.toString();

	}
}
