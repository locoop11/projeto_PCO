package types;

public abstract class AbstractFillingGame implements FillingGame {

	public static String EOL = System.lineSeparator();
	protected Table table;
	protected int numberOfRounds;
	protected Bottle[] bottles;
	public static final String empty = "⬜";
	protected int jogadas;

	

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
		return jogadas;
	}
	
	//public int getNumberOfRounds() {
      //  return numberOfRounds;
    //}

	/**
	 * 
	 * @return
	 */
	
	public int getBottlesSize() {
		return table.getSizeBottles();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	 @Override
	 public void play(int x, int y) {
	        if (x >= 0 && x < table.getSizeBottles() && y >= 0 && y < table.getSizeBottles()) {
	            table.pourFromTo(x, y);
	            jogadas++;
	        }
	 }

	/**
	 * 
	 */
	@Override
	public void provideHelp() {
        Bottle newBottle = getNewBootle();
        table.addBootle(newBottle);
    }

	/**
	 * 
	 * @return
	 */
	public abstract Bottle getNewBootle(); //.----------------------------------------------------------------

	/**
	 * 
	 * @return
	 */
	public abstract void updateScore(); //.----------------------------------------------------------------

	/**
	 * 
	 * @param x
	 * @return
	 */
	@Override
	public Filling top(int x) {
		return table.top(x);
	}

	@Override
	public boolean singleFilling(int x) {
	    return table.singleFilling(x);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public abstract boolean isRoundFinished(); //.----------------------------------------------------------------


	/**
	 * 
	 * @return
	 */
	@Override
	public abstract int score(); //.----------------------------------------------------------------

	/**
	 * 
	 */
	 @Override
	    public void startNewRound() {
	        table.regenerateTable();
	        numberOfRounds++;
	    }

	/**
	 * 
	 * @return
	 */
	 @Override
	  public boolean areAllFilled() {
	      return table.areAllFilled();
	   }

	
	/**
	 * 
	 * @return
	 */
	public String toString() {
        StringBuilder result = new StringBuilder();

        int maxBottleSize = 0;
        for (Bottle bottle : bottles) {
            maxBottleSize = Math.max(maxBottleSize, bottle.size());
        }

        for (int row = maxBottleSize - 1; row >= 0; row--) {
            for (Bottle bottle : bottles) {
                Filling[] contents = bottle.getContent();
                if (row < contents.length && contents[row] != null) {
                    result.append(contents[row].toString());
                } else {
                    result.append((bottle instanceof Cup && row >= bottle.size()) ? Cup.empty : empty);
                }
                result.append("    ");
            }
            result.append(EOL);
        }

        return result.toString();
    }
	

}
