package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {

	private ArrayList<Card> dealerCards;
	private ArrayList<Card> playerCards;
	private Deck deck;

	public ArrayList<Card> getDealerCards() {
		ArrayList<Card> dealerCardsCopy = new ArrayList<Card>();
		for(int i=0 ; i < dealerCards.size(); i++) {
			dealerCardsCopy.add(dealerCards.get(i));
		}
		return dealerCardsCopy;
	}

	public ArrayList<Card> getPlayerCards() {
		ArrayList<Card> playerCardsCopy = new ArrayList<Card>();
		for(int i=0 ; i < playerCards.size(); i++) {
			playerCardsCopy.add(playerCards.get(i));
		}
		return playerCardsCopy;
	}

	public void setDealerCards(ArrayList<Card> cards) {
		dealerCards = new ArrayList<Card>();
		for(Card i: cards) {
			dealerCards.add(i);
		}
	}

	public void setPlayerCards(ArrayList<Card> cards) {
		playerCards = new ArrayList<Card>();
		for(Card i: cards) {
			playerCards.add(i);
		}
	}

	public void createAndShuffleDeck(Random random) {
		deck = new Deck();
		deck.shuffle(random);
	}

	public void initialDealerCards() {
		dealerCards = new ArrayList<Card>();
		dealerCards.add(deck.dealOneCard());
		dealerCards.add(deck.dealOneCard());
	}

	public void initialPlayerCards() {
		playerCards = new ArrayList<Card>();
		playerCards.add(deck.dealOneCard());
		playerCards.add(deck.dealOneCard());
	}

	public void playerTakeCard() {
		playerCards.add(deck.dealOneCard());
	}

	public void dealerTakeCard() {
		dealerCards.add(deck.dealOneCard());
	}

	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {
		int total = 0;
		int trial = 0;
		boolean v = false;
		ArrayList<Integer> value = new ArrayList<Integer>();
		for (int i = 0; i < hand.size(); i++) {
			total = total + hand.get(i).getRank().getValue();
		}
		trial = total + 11 - 1;
		if(total <= 21) {
			value.add(total);
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getRank() == Rank.ACE && trial <= 21) {
					v = true;
				}
			}
			if(v) {
				value.add(trial);
			}
		}
		if(total > 21) {
			value.add(total);
		}
		return value;
	}

	public static HandAssessment assessHand(ArrayList<Card> hand) {
		if (hand == null || hand.size() < 2) {
			return HandAssessment.INSUFFICIENT_CARDS;
		}
		if (hand.size() == 2) {
			if ((hand.get(0).getRank().getValue() == 1 && hand.get(1).getRank().getValue() == 10) || 
				(hand.get(1).getRank().getValue() == 1 && hand.get(0).getRank().getValue() == 10)) {
				return HandAssessment.NATURAL_BLACKJACK;
			}
		}
		for (int i = 0; i < possibleHandValues(hand).size(); i++) {
			if (possibleHandValues(hand).get(i) > 21) {
				return HandAssessment.BUST;
			}
		}
		return HandAssessment.NORMAL;
	}

	public GameResult gameAssessment() {
		if(assessHand(playerCards) == HandAssessment.BUST) {
			return GameResult.PLAYER_LOST;
		}
		if(assessHand(dealerCards) == HandAssessment.BUST) {
			return GameResult.PLAYER_WON;
		}
		ArrayList<Integer> valuesDealer = possibleHandValues(dealerCards);
		ArrayList<Integer> valuesPlayer = possibleHandValues(playerCards);
		if(valuesPlayer.size() == 1 && valuesDealer.size() == 1) {
			if(valuesPlayer.get(0) < valuesDealer.get(0)) {
				return GameResult.PLAYER_LOST;
			}
			if(valuesPlayer.get(0) == valuesDealer.get(0)) {
				return GameResult.PUSH;
			}
			if(valuesPlayer.get(0) > valuesDealer.get(0)) {
				return GameResult.PLAYER_WON;
			}
		}
		if(valuesPlayer.size() == 1 && valuesDealer.size() == 2) {
			if(assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK) {
				if(valuesPlayer.get(0) == 21) {
				return GameResult.PUSH;
				}
				else {
					return GameResult.PLAYER_LOST;
				}
			}
			if(valuesPlayer.get(0) < valuesDealer.get(1)) {
				return GameResult.PLAYER_LOST;
			}
			if(valuesPlayer.get(0) == valuesDealer.get(1)) {
				return GameResult.PUSH;
			}
			if(valuesPlayer.get(0) > valuesDealer.get(1)) {
				return GameResult.PLAYER_WON;
			}
		}
		if(valuesPlayer.size() == 2 && valuesDealer.size() == 1) {
			if(assessHand(playerCards) == HandAssessment.NATURAL_BLACKJACK) {
				return GameResult.NATURAL_BLACKJACK;
			}
			if(valuesPlayer.get(1) < valuesDealer.get(0)) {
				return GameResult.PLAYER_LOST;
			}
			if(valuesPlayer.get(1) == valuesDealer.get(0)) {
				return GameResult.PUSH;
			}
			if(valuesPlayer.get(1) > valuesDealer.get(0)) {
				return GameResult.PLAYER_WON;
			}
		}
		if(valuesPlayer.size() == 2 && valuesDealer.size() == 2) {
			if(assessHand(playerCards) == HandAssessment.NATURAL_BLACKJACK){
				if(assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK) {
					return GameResult.PUSH;
				}
				else {
					return GameResult.NATURAL_BLACKJACK;
				}
			}
			if(valuesPlayer.get(1) == 21) {
				if(assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK) {
					return GameResult.PUSH;
				}
			}
			if(valuesPlayer.get(1) < valuesDealer.get(1)) {
				return GameResult.PLAYER_LOST;
			}
			if(valuesPlayer.get(1) == valuesDealer.get(1)) {
				return GameResult.PUSH;
			}
			if(valuesPlayer.get(1) > valuesDealer.get(1)) {
				return GameResult.PLAYER_WON;
			}
		}
		return GameResult.PLAYER_LOST;
	}

	public boolean dealerShouldTakeCard() {
		ArrayList<Integer> values = possibleHandValues(dealerCards);
		if(values.size() == 1) {
			if(values.get(0) >= 17) {
				return false;
			}
			if(values.get(0) <= 16) {
				return true;
			}
		}
		if(values.size() == 2) {
			if(values.get(0) <= 7 && values.get(1) <= 17) {
				return true;
			}
		}
		return false;
	}
}
