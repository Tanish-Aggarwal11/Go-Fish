/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Collections;

/**
 *
 * @author tanis
 */
public class CenterDeck extends GoFishGroupOfCards {

     public CenterDeck() {
        super(52);
        initializeDeck();
        shuffle(); // use inherited method
    }

    private void initializeDeck() {
        for (GoFishCard.Suit suit : GoFishCard.Suit.values()) {
            for (GoFishCard.Value value : GoFishCard.Value.values()) {
                getCards().add(new GoFishCard(value, suit));
            }
        }
    }

    public GoFishCard drawCard() {
        if (!getCards().isEmpty()) {
            return (GoFishCard) getCards().remove(0);
        }
        return null;
    }
    
}
