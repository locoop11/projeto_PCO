package types;

public class HugoFillingGameImpl extends AbstractFillingGame {

    private Table table; // The game table.
    private int currentScore;
    private boolean roundFinished;

    // Constructor
    public HugoFillingGameImpl(Filling[] symbols, int numberOfUsedSymbols, int seed, int bottleSize) {
        // Initialize the table with the given parameters.
        this.table = new Table(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.currentScore = 0;
        this.roundFinished = false;
    }

    // Implement play method as per the game logic.
    @Override
    public void play(int x, int y) {
        table.pourFromTo(x, y);
    }

    // Implement isRoundFinished method.
    @Override
    public boolean isRoundFinished() {
        return roundFinished;
    }

    // Implement startNewRound method.
    @Override
    public void startNewRound() {
        table.regenerateTable(); // Regenerate the table for a new round.
        roundFinished = false;
        // Reset score or other round-specific state as needed.
    }

    // Implement provideHelp method.
    @Override
    public void provideHelp() {
        // Provide help to the player, e.g., suggest a move.
        // This could involve complex game-solving logic.
    }

    // Implement top method.
    @Override
    public Filling top(int x) {
        return table.top(x); // Get the top filling of bottle x.
    }

    // Implement singleFilling method.
    @Override
    public boolean singleFilling(int x) {
        return table.singleFilling(x); // Check if bottle x has a single type of filling.
    }

    // Implement score method.
    @Override
    public int score() {
        return currentScore; // Return the current score.
    }

    @Override
    public Bottle getNewBootle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNewBootle'");
    }

    @Override
    public void updateScore() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateScore'");
    }

    // Additional methods can be implemented as needed.
}
