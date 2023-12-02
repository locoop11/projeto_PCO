package types;

import java.util.Iterator;
import java.util.Arrays;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class Bottle implements Iterable<Filling>{


	public static int DEFAULT_SIZE = 5;

	public static String empty = "⬜";

	public static String EOL = System.lineSeparator();


	private Filling[] contents;
    private int size;


	/**
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
	 * 
	 * @param size
	 */
	public Bottle(int size) {
		this.size = size;
        this.contents = new Filling[size];
        for (int i = 0; i < size; i++) {
            this.contents[i] = null;
        }
	}


	/**
	 * @param content
	 */
	public Bottle(Filling[] content ) {
		this.size = content.length;
        this.contents = new Filling[size];
        for(int i = 0; i < size; i++) {
			this.contents[i] = content[i];
		}
	}

	/**
	 * @return
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
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * 
	 */
	public void pourOut() {
		for (int i = size -1; i >= 0; i--) {
            if (contents[i] != null) {
                contents[i] = null;
                break;
            }
        }
	}





	/**
	 * 
	 * @param s
	 * @return
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
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * @return
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

	public void setContent(Filling[] newContent) {
	    if (newContent.length == size) {
	        this.contents = Arrays.copyOf(newContent, size);
	    } else {
	        throw new IllegalArgumentException("Invalid array length for setting bottle contents.");
	    }
	}

	/**
	 * 
	 * @return
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
