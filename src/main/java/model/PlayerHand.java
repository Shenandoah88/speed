package model;

import java.util.Queue;

public class PlayerHand {

    private Queue<Card> hand;
    public Queue<Card> getHand() {
        return hand;
    }
    public void setHand(Queue<Card> hand) {
        this.hand = hand;
    }
}
