package deckOfCards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public Deck() {
		for(Suit s: Suit.values()) {
			for(Rank r: Rank.values()) {
				cards.add(new Card(r, s));
			}
		}
	}
	
	public void shuffle(Random randomNumberGenerator) {
		Collections.shuffle(cards, randomNumberGenerator);
	}
	
	public Card dealOneCard() {
		Card card1 = cards.get(0);
		cards.remove(0);
		return card1;
	}
}
