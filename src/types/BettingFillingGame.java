package types;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class BettingFillingGame extends AbstractFillingGame {
	private int bet;
	private int maxjogadas;
	private int score;
	private int initScore;
	private int playsLeft;

	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 * @param score
	 * @param bet
	 * @param maxjogadas
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
	 * 
	 */
	@Override
	public int score() {
		return this.score;
	}

	/** 
	 * 
	 */
	@Override
	public void play(int x, int y) {
		super.play(x, y);
		this.playsLeft--;
	}

	/**
	 * 
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
	 * 
	 * @return
	 */
	public int numberOfPlaysLeft() {
		return this.playsLeft;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public Bottle getNewBootle() {
		return new Cup();
	}

	/**
	 * 
	 * @return
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
