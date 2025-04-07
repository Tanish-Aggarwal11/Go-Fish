/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author tanis
 */
public class GoFishBookOfCard {
    
    private final GoFishCard[] book = new GoFishCard[2];

    public GoFishBookOfCard(GoFishCard c1, GoFishCard c2) {
        this.book[0] = c1;
        this.book[1] = c2;
    }

    @Override
    public String toString() {
        return book[0] + " & " + book[1];
    }
    
}
