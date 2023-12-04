package types;

public class FinalScoringFillingGame extends AbstractFillingGame {

    protected int score;

    /**
     * Constructor without initial score.
     *
     * @param symbols             Array of possible symbols.
     * @param numberOfUsedSymbols Number of symbols to be used in the table.
     * @param seed                Seed for randomization.
     * @param bottleSize          Size of the bottle.
     */
    public FinalScoringFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize) {
        super(symbols, numberOfUsedSymbols, seed, bottleSize);
    }

    /**
     * Constructor with initial score.
     *
     * @param symbols             Array of possible symbols.
     * @param numberOfUsedSymbols Number of symbols to be used in the table.
     * @param seed                Seed for randomization.
     * @param bottleSize          Size of the bottle.
     * @param score               Initial score.
     */
    public FinalScoringFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize, int score) {
        super(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.score = score;
    }

  
    @Override
    public void provideHelp() {
		super.provideHelp();
		if (this.score - 100 > 0) {
			this.score -= 100;
		} else {
			this.score = 0;
		}
	}

    
    @Override
	public void updateScore() {
		if (this.jogadas < 10) {
			this.score = 1000;
		} else if (this.jogadas >= 10 && this.jogadas < 15) {
			this.score = 500;
		} else if (this.jogadas >= 15 && this.jogadas < 25) {
			this.score = 200;
		}
	}
    
    @Override
    public boolean isRoundFinished() {
		if (areAllFilled()) {
			this.finished = true;
			this.updateScore();
			return finished;
			} else {
			return finished;
		}
	}

    @Override
    public int score() {
        return this.score;
    }

    @Override
    public Bottle getNewBootle() {
        Bottle newBottle = new Bottle();
        table.addBootle(newBottle);
        return newBottle;
    }

    @Override
    public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Score: " + this.score + AbstractFillingGame.EOL);
		
		result.append(super.toString());
		if (this.finished) {
			int firstLineIndex = result.indexOf(System.lineSeparator());
			result.replace(0, firstLineIndex, "Score: " + this.score);
			result.append("Status: This round is finished." + AbstractFillingGame.EOL);
			result.append(this.jogadas + " moves were used." + AbstractFillingGame.EOL);
		} else {
			result.append("Status: The round is not finished." + AbstractFillingGame.EOL);
			result.append(this.jogadas + " moves have been used until now." + AbstractFillingGame.EOL);
		}
		return result.toString();
	}



}
