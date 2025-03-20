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
public class GoFish extends Game {

    public GoFish(String name) {
        super(name);
    }

    @Override
    public void play() {
        int numOfPlayers;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Please enter the numbr of players from 2-6");
            numOfPlayers = in.nextInt();
            if (!(numOfPlayers < 2 || numOfPlayers > 6)) {
                System.out.println("Please enter a valid number of players i.e. between 2-6");
            }
        } while (!(numOfPlayers < 2 || numOfPlayers > 6));
        
        GoFish game = new GoFish("Game1");
        game.setPlayers(initializePlayers(numOfPlayers));
    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public ArrayList<Player> initializePlayers(int numOfPlayers) {
        ArrayList<Player> players = new ArrayList();
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println("Enter name of player " + i + ": ");
            String name = in.nextLine();
            players.add(new GoFishPlayer(name));
        }
        return players;
    }
    
}
