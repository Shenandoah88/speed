package model;

import java.util.Queue;

public class PlayerHand {

    private Queue<Card2> hand;
    public Queue<Card2> getHand() {
        return hand;
    }
    public void setHand(Queue<Card2> hand) {
        this.hand = hand;
    }
}
