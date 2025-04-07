/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author tanis
 */
public class GoFishGroupOfCards extends GroupOfCards {

    public GoFishGroupOfCards(int size) {
        super(size);
    }
public void addCard(Card card) {
    getCards().add(card);
}

public Card drawTopCard() {
    if (!getCards().isEmpty()) {
        return getCards().remove(0);
    }
    return null;
}

public boolean isEmpty() {
    return getCards().isEmpty();
}


    
    
}
