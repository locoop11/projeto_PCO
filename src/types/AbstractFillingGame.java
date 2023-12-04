package types;

/**
 * Abstract class AbstractFillingGame - Class that implements the interface
 * FillingGame.
 */
public abstract class AbstractFillingGame implements FillingGame {

	public static String EOL = System.lineSeparator();

	protected Table table;

	protected int jogadas;

	protected int score;

	protected boolean jogada = false;

	protected boolean finished = false;

	protected int numberOfRounds;

	protected int bottleSize;

	protected Bottle[] bottles;

	public static final String empty = "⬜";

	/**
	 * Constructor for objects of class AbstractFillingGame
	 * 
	 * @param symbols             - Array of fillings that can be used in the game.
	 * @param numberOfUsedSymbols - Number of fillings that will be used in the
	 *                            game.
	 * @param seed                - Seed for randomization.
	 * @param bottleSize          - Size of the bottle.
	 */
	public AbstractFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize) {

		this.table = new Table(symbols, numberOfUsedSymbols, seed, bottleSize);
		this.numberOfRounds = 0;
		this.jogadas = 0;
	}

	/**
	 * Function to get the number of rounds played.
	 * 
	 * @return - Returns the number of rounds played.
	 */
	public int jogadas() {
		return this.jogadas;
	}

	/**
	 * Function to get the bottle size.
	 * 
	 * @return - Returns the bottle size.
	 */

	public int getBottlesSize() {
		return this.table.getSizeBottles();
	}

	/**
	 * 
	 * @param x - Position of the bottle to get filling from.
	 * @param y - Position of the bottle to get filling to.
	 */
	@Override
	public void play(int x, int y) {
		if (this.table.isEmpty(y)) {
			this.table.pourFromTo(x, y);
		}

		while (!this.table.isEmpty(x) && !this.table.isFull(y) && this.top(x).equals(this.top(y))) {
			this.table.pourFromTo(x, y);
		}
		this.jogadas++;
		this.jogada = true;

		if (!this.jogada) {
			System.out.println("Different type of fillings cannot be merged.");
			this.jogadas--;
		}

		if (this.isRoundFinished()) {
			this.updateScore();
			this.finished = true;
		}
	}

	/**
	 * Creates a new bottle and adds it to the table.
	 * 
	 */
	@Override
	public void provideHelp() {
		Bottle newBottle = new Bottle(this.bottleSize);
		table.addBootle(newBottle);
	}

	/**
	 * Function to create a new bottle.
	 * 
	 * @return - Returns the bottle.
	 */
	public abstract Bottle getNewBootle(); // .----------------------------------------------------------------

	/**
	 * Updates the score of the game.
	 * 
	 */
	public abstract void updateScore(); // .----------------------------------------------------------------

	/**
	 * Gets the top filling of a bottle.
	 * 
	 * @param x The index of the bottle.
	 * @return The top filling of the bottle.
	 */
	@Override
	public Filling top(int x) {
		return this.table.top(x);
	}

	/**
	 * Check us the bottle has only one filling.
	 * 
	 * @return True if the bottle has only one filling, false otherwise.
	 */
	@Override
	public boolean singleFilling(int x) {
		return this.table.singleFilling(x);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public abstract boolean isRoundFinished(); // .----------------------------------------------------------------

	/**
	 * 
	 * @return
	 */
	@Override
	public abstract int score(); // .----------------------------------------------------------------

	/**
	 * Starts a new round.
	 */
	@Override
	public void startNewRound() {
		this.table.regenerateTable();
		numberOfRounds++;
		this.finished = false;
		this.jogadas = 0;
	}

	/**
	 * Checks is all the botles in the table are singleFilled
	 * 
	 * @return
	 */
	@Override
	public boolean areAllFilled() {
		return this.table.areAllFilled();
	}

	/**
	 * 
	 * @return
	 */
	public String toString() {
		StringBuilder res = new StringBuilder();

		res.append(this.table.toString());

		return res.toString();
	}

}
