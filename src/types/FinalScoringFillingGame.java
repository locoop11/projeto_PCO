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

    /**
     * Provides help by adding a new bottle to the table abd updates score
     */
    @Override
    public void provideHelp() {
        super.provideHelp();
        if (this.score - 100 > 0) {
            this.score -= 100;
        } else {
            this.score = 0;
        }
    }

    /**
     * Updates score according to the number of moves used. If the number of moves
     * is less than 10, the score is 1000. If the number of moves is between 10 and
     * 15, the score is 500. If the number of moves is between 15 and 25, the score
     * is 200. If none of the previous conditions are met, the score is 0.
     * 
     * 
     */
    @Override
    public void updateScore() {
        if (this.jogadas <= 10) {
            this.score = 1000;
        } else if (this.jogadas > 10 && this.jogadas <= 15) {
            this.score = 500;
        } else if (this.jogadas >= 15 && this.jogadas < 25) {
            this.score = 200;
        } else {
            this.score = 0;
        }
    }

    /**
     * Checks if the round is finished. If it is, updates the score. A round is
     * finished when all bottles are SingleFilled.
     * 
     * 
     */

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
