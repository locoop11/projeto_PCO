package types;

import java.util.Iterator;
import java.util.Arrays;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class Bottle implements Iterable<Filling> {

	public static int DEFAULT_SIZE = 5;

	public static String empty = "⬜";

	public static String EOL = System.lineSeparator();

	private Filling[] contents;
	private int size;

	/**
	 * Constructor for objects of class Bottle
	 * 
	 * 
	 */
	public Bottle() {
		this.size = DEFAULT_SIZE;
		this.contents = new Filling[size];
		for (int i = 0; i < size; i++) {
			this.contents[i] = null;
		}
	}

	/**
	 * Cosntructor for objects of class Bottle
	 * 
	 * @param size - Size of the bottle.
	 */
	public Bottle(int size) {
		this.size = size;
		this.contents = new Filling[size];
		for (int i = 0; i < size; i++) {
			this.contents[i] = null;
		}
	}

	/**
	 * Constructor for objects of class Bottle
	 * 
	 * @param content - Array of fillings that will be used to fill the bottle.
	 */
	public Bottle(Filling[] content) {
		this.size = content.length;
		this.contents = new Filling[size];
		for (int i = 0; i < size; i++) {
			this.contents[i] = content[i];
		}
	}

	/**
	 * Checks if the bottle is full.
	 * 
	 * @return - Returns true if the bottle is full, false otherwise.
	 */
	public boolean isFull() {
		for (Filling f : contents) {
			if (f == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the bottle is empty.
	 * 
	 * @return - Returns true if the bottle is empty, false otherwise.
	 */
	public boolean isEmpty() {
		for (Filling f : contents) {
			if (f != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the top filling of the bottle.
	 * 
	 * @return - Returns the top filling of the bottle.
	 * @exception ArrayIndexOutOfBoundsException - If the bottle is empty.
	 */
	public Filling top() {
		for (int i = size - 1; i >= 0; i--) {
			if (contents[i] != null) {
				return contents[i];
			}
		}
		throw new ArrayIndexOutOfBoundsException("");
	}

	/**
	 * Gets the top filling of the bottle.
	 * 
	 * @return - Returns the top filling of the bottle. Null if the bottle is empty.
	 */
	public Filling topContent() {
		for (int i = size - 1; i >= 0; i--) {
			if (contents[i] != null) {
				return contents[i];
			}
		}
		return null;
	}

	/**
	 * Gets the space available in the bottle.
	 * 
	 * @return - Returns the space available in the bottle.
	 */
	public int spaceAvailable() {
		int space = 0;
		for (Filling f : contents) {
			if (f == null) {
				space++;
			}
		}
		return space;
	}

	/**
	 * Removes the top filling of the bottle.
	 */
	public void pourOut() {
		for (int i = size - 1; i >= 0; i--) {
			if (contents[i] != null) {
				contents[i] = null;
				break;
			}
		}
	}

	/**
	 * Adds a filling to the bottle. The filling must be equal to the top filling of
	 * the bottle. Full bottles cannot receive fillings and null fillings cannot be
	 * added.
	 * 
	 * @param s - Filling to be added.
	 * @return - Returns true if the filling was added, false otherwise.
	 */
	public boolean receive(Filling s) {
		if (!isFull() && s != null) {
			Filling top = topContent();
			if (top == null || s.equals(top)) {
				for (int i = 0; i < size; i++) {
					if (contents[i] == null) {
						contents[i] = s;
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Gets the size of the bottle.
	 * 
	 * @return - Returns the size of the bottle.
	 */
	public int size() {
		return size;
	}

	/**
	 * Checks is the fillings of the bottle are all equal.
	 * 
	 * @return - Returns true if the fillings of the bottle are all equal, false
	 */
	public boolean isSingleFilling() {
		Filling first = null;
		for (Filling f : contents) {
			if (f != null) {
				if (first == null) {
					first = f;
				} else if (!first.equals(f)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Sets the content of the bottle to new Fillings.
	 * 
	 * @param newContent - Array of fillings to be used as the new content of the
	 */
	public void setContent(Filling[] newContent) {
		if (newContent.length == size) {
			this.contents = Arrays.copyOf(newContent, size);
		} else {
			throw new IllegalArgumentException("Invalid array length for setting bottle contents.");
		}
	}

	/**
	 * Gets the content of the bottle.
	 * 
	 * @return - Returns the content of the bottle as an array of Fillings.
	 */
	public Filling[] getContent() {
		return contents;
	}

	/**
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = size - 1; i >= 0; i--) {
			if (contents[i] == null) {
				sb.append(empty);
			} else {
				sb.append(contents[i].toString());
			}
			sb.append("    ");
		}
		sb.append(EOL);
		return sb.toString();
	}

	/**
	 * 
	 */
	public Iterator<Filling> iterator() {
		return new Iterator<Filling>() {
			private int currentIndex = -1;

			@Override
			public boolean hasNext() {
				int nextIndex = currentIndex + 1;
				return (nextIndex < size && contents[nextIndex] != null);
			}

			@Override
			public Filling next() {
				currentIndex = currentIndex + 1;
				return contents[currentIndex];
			}
		};
	}

}
