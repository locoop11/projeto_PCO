package types;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class Cup extends Bottle {

	public static final int CUP_SIZE = 1; 

	private static final int TIMES_OF_USAGES = 3;

	public static String empty = "⚪";

	private int usages;


	/**
	 * Default constructor
	 * constrói um copo vazio com tamanho DEFAULT_SIZE e TIMES_OF_USAGES
	 */
	public Cup() {
		super(CUP_SIZE);
        usages = 0;
	}

	public Cup(int size) {
		super(size);
        usages = 0;
	}

	public Cup(Filling[] content) {
		super(content);
		this.usages = 0;
	}


	/**
	 *  quando o número de usos excede 3, deixa de poder receber novos conteúdos
	 */
	public boolean receive(Filling s) {
		if (usages < TIMES_OF_USAGES) {
            boolean result = super.receive(s);
            if (result) {
                usages++;
            }
            return result;
        }
        return false;
	}
}