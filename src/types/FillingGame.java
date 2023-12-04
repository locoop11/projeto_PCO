package types;

public interface FillingGame {

	/**
	 * Makes a play in the Game
	 * 
	 * @param x - Position of the bottle to get filling from.
	 * @param y - Position of the bottle to get filling to.
	 */
	public void play(int x, int y);

	/**
	 * Checks if the round is finished
	 * 
	 * @return
	 */
	public boolean isRoundFinished();

	/**
	 * Starts a new round
	 */
	public void startNewRound();

	/**
	 * Creates a new bottle
	 */
	public void provideHelp();

	/**
	 * Gets the top Filling of a bottle
	 * 
	 * @param x - Position of the bottle to get filling from.
	 * @return - Returns the top filling of the bottle.
	 */
	public Filling top(int x);

	/**
	 * Checks is bottle x has only one filling
	 * 
	 * @param x - Position of the bottle to get filling from.
	 * @return - Returns true if the bottle has only one filling, false otherwise.
	 */
	public boolean singleFilling(int x);

	/**
	 * Gets the current score
	 * 
	 * @return
	 */
	public int score();

	/**
	 * Checks is all bottles have the same filling
	 * 
	 * @return
	 */
	boolean areAllFilled();

}
