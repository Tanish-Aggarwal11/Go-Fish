/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tanis
 */
public class GoFishPlayer extends Player {

    private GoFishGroupOfCards hand = new GoFishGroupOfCards(52); // default max size

    public GoFishPlayer(String name) {
        super(name);
    }

    public void addCardToHand(GoFishCard card) {
        hand.getCards().add(card); // using inherited GroupOfCards method
    }

    public GoFishGroupOfCards getHand() {
        return hand;
    }

    public void takeTurn(ArrayList<Player> players, CenterDeck deck) {
        Scanner in = new Scanner(System.in);
        System.out.println("\n" + getName() + ", it's your turn!");

        // Show hand
        System.out.println("Your hand: " + getHand().getCards());

        // Ask for a rank
        System.out.print("Choose a rank to ask (e.g., ACE, TWO, THREE...): ");
        String rankInput = in.nextLine().toUpperCase();

        GoFishCard.Value askedValue;
        try {
            askedValue = GoFishCard.Value.valueOf(rankInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid rank. Turn skipped.");
            return;
        }

        // Automatically get the next player in line
        int currentIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(this.getName())) {
                currentIndex = i;
                break;
            }
        }

        int nextIndex = (currentIndex + 1) % players.size();
        GoFishPlayer opponent = (GoFishPlayer) players.get(nextIndex);

        System.out.println("You are asking " + opponent.getName() + " for cards of: " + askedValue);

        // Search opponent's hand
        ArrayList<Card> opponentHand = opponent.getHand().getCards();
        ArrayList<GoFishCard> matchedCards = new ArrayList<>();

        for (Card card : opponentHand) {
            GoFishCard gfc = (GoFishCard) card;
            if (gfc.getValue() == askedValue) {
                matchedCards.add(gfc);
                break; 
            }
        }

        // If found, take cards
        if (!matchedCards.isEmpty()) {
            for (GoFishCard card : matchedCards) {
                opponentHand.remove(card);
                addCardToHand(card);
            }
            System.out.println("You got 1 card from " + opponent.getName() + "!");

        } else {
            System.out.println("Go Fish! Drawing from deck...");
            GoFishCard drawnCard = deck.drawCard();
            if (drawnCard != null) {
                addCardToHand(drawnCard);
                System.out.println("You drew: " + drawnCard);
            } else {
                System.out.println("Deck is empty!");
            }
        }

        checkForBook();
    }

    private ArrayList<GoFishBookOfCard> books = new ArrayList<>();

    public void checkForBook() {
        ArrayList<Card> handCards = getHand().getCards();

        for (int i = 0; i < handCards.size(); i++) {
            GoFishCard c1 = (GoFishCard) handCards.get(i);
            for (int j = i + 1; j < handCards.size(); j++) {
                GoFishCard c2 = (GoFishCard) handCards.get(j);
                if (c1.getValue() == c2.getValue()) {
                    books.add(new GoFishBookOfCard(c1, c2));
                    System.out.println(getName() + " made a book: " + c1.getValue());
                    handCards.remove(c2);
                    handCards.remove(c1);
                    return; // only one pair per turn
                }
            }
        }
    }

    public int getBookCount() {
        return books.size();
    }

    @Override
    public void play() {

    }

}
