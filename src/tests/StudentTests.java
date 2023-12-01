package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import blackjack.BlackjackModel;
import blackjack.GameResult;
import blackjack.HandAssessment;
import deckOfCards.Card;
import deckOfCards.Rank;
import deckOfCards.Suit;

public class StudentTests {
	@Test
	public void testGameAssessment() {
		Random random = new Random(3723);
		BlackjackModel game = new BlackjackModel();
		game.createAndShuffleDeck(random);
		game.initialPlayerCards();
		game.initialDealerCards();
		game.playerTakeCard();
		game.dealerTakeCard();
		ArrayList<Card> playerCards = game.getPlayerCards();
		ArrayList<Card> dealerCards = game.getDealerCards();
		assertTrue(playerCards.get(0).equals(new Card(Rank.QUEEN, Suit.HEARTS)));
		assertTrue(playerCards.get(1).equals(new Card(Rank.SIX, Suit.DIAMONDS)));
		assertTrue(playerCards.get(2).equals(new Card(Rank.EIGHT, Suit.HEARTS)));
		
		assertTrue(dealerCards.get(0).equals(new Card(Rank.THREE, Suit.CLUBS)));
		assertTrue(dealerCards.get(1).equals(new Card(Rank.NINE, Suit.SPADES)));
		assertTrue(dealerCards.get(2).equals(new Card(Rank.FIVE, Suit.CLUBS)));	
		assertTrue(game.gameAssessment().equals(GameResult.PLAYER_LOST));
		assertTrue(!game.dealerShouldTakeCard());
	}
	
	@Test
	public void testGameAssessment1() {
		ArrayList<Card> playerCards = new ArrayList<>();   
		ArrayList<Card> dealerCards = new ArrayList<>();
		playerCards.add(new Card(Rank.TWO, Suit.HEARTS));
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.NINE, Suit.HEARTS));
		
		//dealerCards.add(new Card(Rank.THREE, Suit.CLUBS));
		dealerCards.add(new Card(Rank.TEN, Suit.SPADES));
		dealerCards.add(new Card(Rank.ACE, Suit.CLUBS));

		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment().equals(GameResult.PUSH)); 
	}
	@Test
	public void testPossibleHandAssessment() {
		ArrayList<Integer> value = new ArrayList<Integer>();
		ArrayList<Card> playerCards = new ArrayList<>();  
		playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		playerCards.add(new Card(Rank.ACE, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.EIGHT, Suit.HEARTS));
		playerCards.add(new Card(Rank.FIVE, Suit.HEARTS));
		value = BlackjackModel.possibleHandValues(playerCards);
		Integer v = 7, u = 17;
		assertTrue(value.get(0).equals(v));
		assertTrue(value.get(1).equals(u));
	}
	@Test
	public void testassessHand() {
		ArrayList<Card> playerCards = new ArrayList<>();  
		playerCards.add(new Card(Rank.TWO, Suit.HEARTS));
		playerCards.add(new Card(Rank.KING, Suit.DIAMONDS));
		playerCards.add(new Card(Rank.QUEEN, Suit.HEARTS));
		assertTrue(BlackjackModel.assessHand(playerCards).equals(HandAssessment.BUST));
	}
	@Test
	public void testdealerShouldTakeCard() {
		ArrayList<Card> playerCards = new ArrayList<>();  
		playerCards.add(new Card(Rank.JACK, Suit.HEARTS));
		playerCards.add(new Card(Rank.SEVEN, Suit.DIAMONDS));
		//playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(playerCards);
		assertTrue(!game.dealerShouldTakeCard());
	}
}
