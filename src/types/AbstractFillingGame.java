package types;

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
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bottleSize
	 * @param contents
	 */
	public AbstractFillingGame(Filling[] contents, int numberOfUsedSymbols, int seed, int bottleSize) {

		this.table = new Table(contents, numberOfUsedSymbols, seed, bottleSize);
		this.numberOfRounds = 0;
		this.jogadas = 0;
	}

	/**
	 * 
	 * @return
	 */
	public int jogadas() {
		return this.jogadas;
	}

	/**
	 * 
	 * @return
	 */

	public int getBottlesSize() {
		return this.table.getSizeBottles();
	}

	/**
	 * 
	 * @param x
	 * @param y
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
	 * 
	 */
	@Override
	public void provideHelp() {
		Bottle newBottle = new Bottle(this.bottleSize);
		table.addBootle(newBottle);
	}

	/**
	 * 
	 * @return
	 */
	public abstract Bottle getNewBootle(); // .----------------------------------------------------------------

	/**
	 * 
	 * @return
	 */
	public abstract void updateScore(); // .----------------------------------------------------------------

	/**
	 * 
	 * @param x
	 * @return
	 */
	@Override
	public Filling top(int x) {
		return this.table.top(x);
	}

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
	 * 
	 */
	@Override
	public void startNewRound() {
		this.table.regenerateTable();
		numberOfRounds++;
		this.finished = false;
		this.jogadas = 0;
	}

	/**
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
