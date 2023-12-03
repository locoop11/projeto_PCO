package testeGame;

import types.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FinalScoringGamePlay {

	
	
	public String EOL = Table.EOL;
	
		

	@Test
	void test1() {
		
		FinalScoringFillingGame game;
		
		Filling[] symbols = Emojis.values();
		int numberOfUsedSymbols = 3;
		int seed =1;
		int bootleSize = 4;
		

		game = new FinalScoringFillingGame(symbols, numberOfUsedSymbols, seed, bootleSize);

		game.play(2,3);
		
		
		String actual = game.toString();
		String expected = "Score: 0" + EOL +
				  "😡    😒    ⬜    ⬜    ⬜    ⬜    " + EOL
				+ "😒    😃    ⬜    😃    ⬜    ⬜    " + EOL
				+ "😡    😒    ⬜    😃    ⬜    ⬜    " + EOL
				+ "😡    😡    😒    😃    ⬜    ⬜    " + EOL
				+ "Status: The round is not finished." + EOL
				+ "1 moves have been used until now." + EOL;

		assertEquals(expected, actual);

	}
	
	@Test
	void test2() {
		
		
		Filling[] symbols = Emojis.values();
		int numberOfUsedSymbols = 3;
		int seed =1;
		int bootleSize = 4;
		
		FinalScoringFillingGame game;

		game = new FinalScoringFillingGame(symbols, numberOfUsedSymbols, seed, bootleSize);

		game.play(2,3);
		game.play(0,2);
		game.play(0,4);
		game.play(2,0);
		game.play(1,4);
		game.play(1,3);
		game.play(1,4);
		game.play(1,3);
		
		
		String actual = game.toString();
		String expected = "Score: 2000" + EOL +
				    "⬜    ⬜    ⬜    😃    ⬜    ⬜    " + EOL
				  + "😡    ⬜    ⬜    😃    😒    ⬜    " + EOL
				  + "😡    ⬜    ⬜    😃    😒    ⬜    " + EOL
				  + "😡    😡    😒    😃    😒    ⬜    " + EOL
				+ "Status: The round is finished." + EOL
				+ "8 moves have been used until now." + EOL;

		assertEquals(expected, actual);

	}

}
