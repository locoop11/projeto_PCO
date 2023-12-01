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
		this.numSymbols = symbols.length < numberOfUsedSymbols ? symbols.length : numberOfUsedSymbols;
		this.numberOfBottles = this.numSymbols+DIFICULTY;
        this.bootleSize = bootleSize;
        this.bottles = new Bottle[this.numberOfBottles];
		this.symbols = symbols;
		if( seed == 1 ) {
			// If seed is 1, generate a test filling so that thes tests can be reproduced
			this.bottles = generateTestBottles(this.numberOfBottles, this.symbols, this.bootleSize, this.numSymbols);
		} else {
			for(int i = 0; i < numberOfBottles-DIFICULTY; i++) {
				Filling[] bottleContent = generateRandomFilling(this.symbols, this.numSymbols, this.bootleSize, seed);
				this.bottles[i] = new Bottle(bottleContent);
			}
		}
		//Create empty bottles
		for( int i = numberOfBottles - DIFICULTY ; i < numberOfBottles; i++) {
			this.bottles[i] = new Bottle(bootleSize);
		}

	}
	private static Bottle[] generateTestBottles(int numberOfBottles, Filling[] symbols, int bottleSize, int numsymbols) {
		Bottle[] bottles = new Bottle[numberOfBottles];
		for (int i=0; i<numberOfBottles-DIFICULTY; i++) {
			Filling[] bottleContent = new Filling[bottleSize];
			for (int j=0; j<bottleSize; j++) {
				int fillingIndex = (j+i)%numsymbols;
				bottleContent[j] = symbols[fillingIndex];
			}
			bottles[i] = new Bottle(bottleContent);
		}
		return bottles;
	}

	private static Filling[] generateRandomFilling(Filling[] symbols, int numSymbols, int bootleSize, int seed) {
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
				try {
                	source.pourOut();
                	Filling nextFillingToPour = source.top();
                	if (nextFillingToPour == null || !fillingToPour.equals(fillingToPour)) break;
					fillingToPour = nextFillingToPour;
				} catch (ArrayIndexOutOfBoundsException e) {
					// Source is empty
					break;
				}
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
		// transpose the botles in the table so the string botles are in columns not in rows in the output
		for(int i = 0; i < bootleSize; i++) {
			for(int j = 0; j < numberOfBottles; j++) {
				if(bottles[j].getContent()[i] != null) {
					result += bottles[j].getContent()[i].toString() + "    ";
				} else {
					result += empty + "    ";
				}
			}
			result += EOL;
		}

		return result;
	}

}
