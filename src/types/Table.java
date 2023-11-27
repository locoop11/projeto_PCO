package types;

import java.util.Random;


//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class Table {

	public static String EOL = System.lineSeparator();

	public static final String empty = "⬜";

	public static final int DIFICULTY = 3;

	public static final int DEFAULT_BOTTLE_SIZE = 5; 


	private Bottle[] bottles;
    private int numberOfBottles;
    private Filling[] symbols;
	private int numSymbols;
    private int bootleSize;
	private int seed;


	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 */
	public Table(Filling[] symbols, int numberOfUsedSymbols, int seed, int bootleSize) {
		int numSymbols = symbols.length < numberOfUsedSymbols ? symbols.length : numberOfUsedSymbols;
		this.numSymbols = numSymbols;
        this.bootleSize = DIFICULTY;
        this.bottles = new Bottle[DEFAULT_BOTTLE_SIZE];
		this.symbols = symbols;
		this.numberOfBottles = DEFAULT_BOTTLE_SIZE;

		for(int i = 0; i < numberOfBottles; i++) {
			Filling[] bottleContent = generateRandomFilling(symbols, this.numSymbols, bootleSize, seed);
			this.bottles[i] = new Bottle(bottleContent);
		}
        this.numberOfBottles = DEFAULT_BOTTLE_SIZE;
	}

	public static Filling[] generateRandomFilling(Filling[] symbols, int numSymbols, int bootleSize, int seed) {
		Random random = new Random(seed);
		Filling[] bottleContent = new Filling[bootleSize];
		for(int i = 0; i < bootleSize; i++) {
			int randomIndex = random.nextInt(numSymbols);
			bottleContent[i] = symbols[randomIndex];
		}
		return bottleContent;
	}


	/**
	 * 
	 */
	public void regenerateTable() {
		for(int i = 0; i < numberOfBottles; i++) {
			Filling[] bottleContent = generateRandomFilling(symbols, this.numSymbols, bootleSize, seed);
			this.bottles[i] = new Bottle(bottleContent);
		}
	}

	/**
	 * 
	 * @param x
	 * @return
	 */
	public boolean singleFilling(int x) {
		return bottles[x].isSingleFilling();
	}

	/**
	 * 
	 * @param x
	 * @return
	 */
	public boolean isEmpty(int x) {
		return bottles[x].isEmpty();
	}

	/**
	 * 
	 * @param x
	 * @return
	 */
	public boolean isFull(int x) {
		return bottles[x].isFull();
	}

	/**
	 * 
	 * @return
	 */
	public boolean areAllFilled() {
		for (Bottle bottle : bottles) {
            if (!bottle.isFull()) {
                return false;
            }
        }
        return true;
	}


	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void pourFromTo(int i, int j) {
		Bottle source = bottles[i];
        Bottle destination = bottles[j];
        if (source != null && destination != null && !destination.isFull() && !source.isEmpty()) {
            Filling fillingToPour = source.top();
            while (destination.receive(fillingToPour)) {
                source.pourOut();
                Filling nextFillingToPour = source.top();
                if (nextFillingToPour == null || !fillingToPour.equals(fillingToPour)) break;
				fillingToPour = nextFillingToPour;
            }
        }		
	}

	/**
	 * Adds a botle to the botles array
	 * @param bottle
	 */
	public void addBootle(Bottle bottle) {
		Bottle[] newBotles = new Bottle[numberOfBottles + 1];
		for(int i = 0; i < numberOfBottles; i++) {
			newBotles[i] = bottles[i];
		}
		newBotles[numberOfBottles] = bottle;
	}

	/**
	 * 
	 * @return
	 */
	public int getSizeBottles() {
		return DIFICULTY;
	}


	/**
	 * 
	 * @param x
	 * @return
	 */
	public Filling top(int x) {
		return bottles[x].top();
	}

	/**
	 * 
	 */
	public String toString() {
		String result = "";
		for(int i = 0; i < numberOfBottles; i++) {
			result += bottles[i].toString() + EOL;
		}
		return result;
	}

}
