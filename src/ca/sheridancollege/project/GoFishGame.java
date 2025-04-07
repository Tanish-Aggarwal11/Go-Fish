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
public class GoFishGame extends Game {

    public GoFishGame(String name) {
        super(name);
    }

    @Override
    public void play() {
        Scanner in = new Scanner(System.in);
        boolean playAgain;

        do {
            int numPlayers = InputHelper.getValidPlayerCount();
            setPlayers(GoFishPlayerFactory.createPlayers(numPlayers));
            CenterDeck deck = new CenterDeck();
            dealCards(deck);

            // Check for initial books
            for (Player p : getPlayers()) {
                ((GoFishPlayer) p).checkForBook();
            }

            boolean gameOver = false;

            while (!gameOver) {
                for (Player p : getPlayers()) {
                    GoFishPlayer gp = (GoFishPlayer) p;

                    if (gp.getHand().getCards().isEmpty() && !deck.getCards().isEmpty()) {
                        gp.addCardToHand(deck.drawCard());
                    }

                    if (!gp.getHand().getCards().isEmpty()) {
                        gp.takeTurn(getPlayers(), deck);
                    }

                    gameOver = deck.getCards().isEmpty()
                            && getPlayers().stream().allMatch(pl -> ((GoFishPlayer) pl).getHand().getCards().isEmpty());

                    if (gameOver) {
                        break;
                    }
                }
            }

            declareWinner();

            // Ask to play again
            System.out.print("\nWould you like to play again? (Y/N): ");
            String input = in.nextLine().trim().toUpperCase();
            playAgain = input.equals("Y");
            if (playAgain) {
                getPlayers().clear();
            }

        } while (playAgain);

    }

    @Override
    public void declareWinner() {
        Player winner = getPlayers().get(0);
        int maxBooks = ((GoFishPlayer) winner).getBookCount();

        for (Player p : getPlayers()) {
            int books = ((GoFishPlayer) p).getBookCount();
            if (books > maxBooks) {
                winner = p;
                maxBooks = books;
            }
        }

        System.out.println("\n Game Over! Winner is " + winner.getName() + " with " + maxBooks + " books!");
    }

    public void dealCards(CenterDeck deck) {
        ArrayList<Player> players = getPlayers();
        int cardsPerPlayer = (players.size() == 2) ? 7 : 5;

        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                GoFishCard card = deck.drawCard();
                if (card != null) {
                    ((GoFishPlayer) player).addCardToHand(card);
                }
            }
        }
        for (Player player : players) {
            ((GoFishPlayer) player).checkForBook();
        }

    }

    public static void main(String[] args) {
        GoFishGame game = new GoFishGame("Go Fish");
        game.play();
    }

}

class InputHelper {

    public static int getValidPlayerCount() {
        Scanner in = new Scanner(System.in);
        int count;
        while (true) {
            System.out.print("Enter number of players (2–6): ");
            count = in.nextInt();
            if (count >= 2 && count <= 6) {
                break;
            }
            System.out.println("Invalid input. Try again.");
        }
        return count;
    }
}
