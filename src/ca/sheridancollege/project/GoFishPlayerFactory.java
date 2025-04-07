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
public class GoFishPlayerFactory {
    public static ArrayList<Player> createPlayers(int count) {
        ArrayList<Player> players = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter name of player " + i + ": ");
            players.add(new GoFishPlayer(in.nextLine()));
        }
        return players;
    }   
}
