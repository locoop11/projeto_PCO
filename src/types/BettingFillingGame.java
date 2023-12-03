package types;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class BettingFillingGame extends AbstractFillingGame{
	 	private int bet;
	    private int maxPlays;
	    private int score;



	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 * @param score
	 * @param bet
	 * @param maxPlays
	 */
	public BettingFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, 
			int bottleSize, int score, int bet, int maxPlays) {
		
		super(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.score = score;
        this.bet = bet;
        this.maxPlays = maxPlays;
		
	
	}

	
	/**
	 * 
	 */
	@Override
	public void provideHelp() {
        if (jogadas < maxPlays) {
            Bottle newBottle = new Cup(); // Assuming Cup is a subclass of Bottle
            table.addBootle(newBottle);
            jogadas++;
        }
    }


	

	/**
	 * 
	 */
	@Override
	public int score() {
        return score;
    }

	/**
	 * 
	 */
	@Override
	public boolean isRoundFinished() {
        return areAllFilled() || jogadas >= maxPlays;
    }


	/**
	 * 
	 * @return
	 */
	public int numberOfPlaysLeft() {
		return 0;
	}


	/**
	 * 
	 * @return
	 */
	@Override
	public Bottle getNewBootle() {
        return new Cup(); // Assuming Cup is a subclass of Bottle
    }

	/**
	 * 
	 * @return
	 */
	@Override
	public void updateScore() {
        if (isRoundFinished()) {
            int numberOfPlays = jogadas();
            if (numberOfPlays <= maxPlays) {
                score += 2 * bet;
            } else {
                score -= bet;
            }
        }
    }

	public String toString() {
        // Provide a textual description of the game
        return "BettingFillingGame{" +
                "score=" + score +
                ", bet=" + bet +
                ", maxPlays=" + maxPlays +
                ", jogadas=" + jogadas +
                '}';
    }


}
