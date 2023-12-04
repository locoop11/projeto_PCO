/**
 * 
 * Grupo 11
 * 
 * Hugo Silva - nº60253
 * Mariana Foz - nº58955
 * Natacha Neves - nº58649
 * 
 */

package types;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Table {

    public static String EOL = System.lineSeparator();

    public static final String empty = "⬜";

    public static final int DIFICULTY = 3;

    public static final int DEFAULT_BOTTLE_SIZE = 5;

    private Bottle[] bottles;
    private Map<Filling, Integer> symbolsUsedCount;
    private int numberOfUsedSymbols;
    private Filling[] contents;
    private int bottleSize;
    private Random random;

    /**
     * Constructor for the Table class.
     *
     * @param contents            Array of possible symbols.
     * @param numberOfUsedSymbols Number of symbols to be used in the table.
     * @param seed                Seed for randomization.
     * @param bottleSize          Size of the bottle.
     */
    public Table(Filling[] contents, int numberOfUsedSymbols, int seed, int bottleSize) {
        this.random = new Random(seed);
        this.numberOfUsedSymbols = numberOfUsedSymbols;
        this.contents = contents;
        this.bottleSize = bottleSize;

        bottles = new Bottle[numberOfUsedSymbols + DIFICULTY];
        symbolsUsedCount = new HashMap<>();

        for (int i = 0; i < numberOfUsedSymbols; i++) {
            Filling[] bottleContents = new Filling[bottleSize];

            for (int j = 0; j < bottleSize; j++) {
                int numSymbols = Math.min(numberOfUsedSymbols, contents.length);
                Filling symbol = contents[random.nextInt(numSymbols)];

                int usedCount = symbolsUsedCount.getOrDefault(symbol, 0);
                if (usedCount < bottleSize) {
                    bottleContents[j] = symbol;
                    symbolsUsedCount.put(symbol, usedCount + 1);
                } else {
                    j--;
                }
            }

            bottles[i] = new Bottle(bottleContents);
        }

        for (int i = numberOfUsedSymbols; i < numberOfUsedSymbols + DIFICULTY; i++) {
            Filling[] emptyBottle = new Filling[bottleSize];
            for (int j = 0; j < bottleSize; j++) {
                emptyBottle[j] = null;
            }
            bottles[i] = new Bottle(emptyBottle);
        }
    }

    /**
     * Regenerates the contents of all bottles on the table using random symbols
     * from the given contents array.
     * Note that it uses the setContent method in the Bottle class to set the
     * regenerated contents for each bottle.
     */
    /**
     * Regenerates the contents of all bottles on the table.
     */
    public void regenerateTable() {

        symbolsUsedCount = new HashMap<>();

        for (int i = 0; i < numberOfUsedSymbols; i++) {
            Filling[] bottleContents = bottles[i].getContent();

            for (int j = 0; j < bottleSize; j++) {
                int numSymbols = Math.min(numberOfUsedSymbols, contents.length);
                Filling symbol = contents[random.nextInt(numSymbols)];

                int usedCount = symbolsUsedCount.getOrDefault(symbol, 0);
                if (usedCount < bottleSize) {
                    bottleContents[j] = symbol;
                    symbolsUsedCount.put(symbol, usedCount + 1);
                } else {
                    j--;
                }
            }

            bottles[i].setContent(bottleContents);
        }

        for (int i = numberOfUsedSymbols; i < numberOfUsedSymbols + DIFICULTY; i++) {
            Filling[] emptyBottle = new Filling[bottleSize];
            for (int j = 0; j < bottleSize; j++) {
                emptyBottle[j] = null;
            }
            bottles[i].setContent(emptyBottle);
        }
    }

    /**
     * Checks if a specific bottle has a single filling.
     *
     * @param x The index of the bottle.
     * @return True if the bottle has a single filling, false otherwise.
     */
    public boolean singleFilling(int x) {
        if (x >= 0 && x < bottles.length) {
            return bottles[x].isSingleFilling();
        }
        return false;
    }

    /**
     * Checks if a specific bottle is empty.
     *
     * @param x The index of the bottle.
     * @return True if the bottle is empty, false otherwise.
     */
    public boolean isEmpty(int x) {
        if (x >= 0 && x < bottles.length) {
            return bottles[x].isEmpty();
        }
        return false;
    }

    /**
     * Checks if a specific bottle is full.
     *
     * @param x The index of the bottle.
     * @return True if the bottle is full, false otherwise.
     */
    public boolean isFull(int x) {
        if (x >= 0 && x < bottles.length) {
            return bottles[x].isFull();
        }
        return false;
    }

    /**
     * Checks if all non-empty bottles on the table have the same contents.
     *
     * @return True if all non-empty bottles have the same contents, false
     *         otherwise.
     */
    public boolean areAllFilled() {
        for (Bottle bottle : bottles) {
            if (!bottle.isEmpty() && !bottle.isSingleFilling()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pours the contents from one bottle to another.
     *
     * @param fromIndex The index of the source bottle.
     * @param toIndex   The index of the destination bottle.
     */
    public void pourFromTo(int fromIndex, int toIndex) {
        if (fromIndex >= 0 && fromIndex < bottles.length && toIndex >= 0 && toIndex < bottles.length) {
            Bottle fromBottle = bottles[fromIndex];
            Bottle toBottle = bottles[toIndex];

            Filling topContent = fromBottle.topContent();

            if (topContent != null && toBottle.receive(topContent)) {
                fromBottle.pourOut();
            }
        }
    }

    /**
     * Adds a new bottle to the table.
     *
     * @param bottle The bottle to be added.
     */
    public void addBootle(Bottle bottle) {
        bottles = Arrays.copyOf(bottles, bottles.length + 1);
        bottles[bottles.length - 1] = bottle;

    }

    /**
     * Gets the size of bottles on the table.
     *
     * @return The size of each bottle on the table.
     */
    public int getSizeBottles() {
        return bottleSize;
    }

    /**
     * Gets the top filling of a specific bottle.
     *
     * @param x The index of the bottle.
     * @return The top filling of the specified bottle.
     * @throws ArrayIndexOutOfBoundsException if the bottle is empty.
     */
    public Filling top(int x) {
        if (x >= 0 && x < bottles.length) {
            if (!bottles[x].isEmpty()) {
                return bottles[x].topContent();
            } else {
                throw new ArrayIndexOutOfBoundsException("Attempted to get top content of an empty bottle.");
            }
        }
        throw new ArrayIndexOutOfBoundsException("Invalid bottle index.");
    }

    /**
     * *
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

    public Bottle[] getBottles() {
        return bottles;
    }

}
