package types;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class BettingFillingGame extends AbstractFillingGame{
	 	private int bet;
	    private int maxjogadas;
	    private int score;
	    private int playsLeft;



	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 * @param score
	 * @param bet
	 * @param maxjogadas
	 */
	public BettingFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, 
			int bottleSize, int score, int bet, int maxjogadas) {
		
		super(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.score = score;
        this.bet = bet;
        this.maxjogadas = maxjogadas;
        this.playsLeft = maxjogadas;
		
	
	}

	
	/**
	 * 
	 */
	@Override
	public void provideHelp() {
        if (jogadas < maxjogadas) {
            Bottle newBottle = new Cup(); // Assuming Cup is a subclass of Bottle
            table.addBootle(newBottle);
            this.playsLeft--;
            this.jogadas++;
        }
    }


	

	/**
	 * 
	 */
	@Override
	public int score() {
        return this.score;
    }

	/**
	 * 
	 */
	@Override
	public boolean isRoundFinished() {
		this.playsLeft--;
        return areAllFilled() || this.jogadas >= maxjogadas;
        
    }


	/**
	 * 
	 * @return
	 */
	public int numberOfPlaysLeft() {
		return this.playsLeft;
	}


	/**
	 * 
	 * @return
	 */
	@Override
	public Bottle getNewBootle() {
        return new Cup();
    }

	/**
	 * 
	 * @return
	 */
	@Override
	public void updateScore() {
        if (isRoundFinished()) {
            int numberOfjogadas = this.jogadas();
            if (numberOfjogadas <= this.maxjogadas) {
                this.score += 2 * this.bet;
            } else {
                this.score -= 2* this.bet;
            }
        }
    }

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		
		res.append(super.toString());
		if (this.finished) {
			int firstLineIndex = res.indexOf(System.lineSeparator());
			res.replace(0, firstLineIndex, "Score: " + this.score);
			res.append("Status: This round is finished." + AbstractFillingGame.EOL);
			res.append(this.jogadas + " moves were used." + AbstractFillingGame.EOL);
		} else {
			res.append("Status: " + this.jogadas + " moves have been used until now. You still have " 
					+ numberOfPlaysLeft() + " moves left." + AbstractFillingGame.EOL);
		}
		
		return res.toString();
	        
	}
	        }
