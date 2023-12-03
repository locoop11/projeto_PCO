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
        this.score = 0;
        startNewRound();
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
        startNewRound();
    }

    @Override
    public void provideHelp() {
        // Implementation specific to FinalScoringFillingGame
        // You can add custom logic here if needed
    }

    @Override
    public void updateScore() {
        if (isRoundFinished()) {
            int numberOfPlays = jogadas();
            if (numberOfPlays <= 10) {
                score += 1000;
            } else if (numberOfPlays <= 15) {
                score += 500;
            } else if (numberOfPlays <= 25) {
                score += 200;
            } else {
                score -= 100;
            }
        }
    }

    @Override
    public boolean isRoundFinished() {
        return areAllFilled();
    }

    @Override
    public int score() {
        return score;
    }

    @Override
    public Bottle getNewBootle() {
        // Assuming DEFAULT_SIZE is a constant
        Bottle newBottle = new Bottle();
        table.addBootle(newBottle);
        return newBottle;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

       result.append(table.toString());

        result.append("Status: ").append(isRoundFinished() ? "The round is finished." : "The round is not finished.").append(EOL);
        result.append(jogadas()).append(" moves have been used until now.").append(EOL);

        return "Score: " + score + EOL + result.toString();
    }



}
