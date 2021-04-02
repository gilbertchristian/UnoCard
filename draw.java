import HijiCard;
//ga yakin

import java.util.List;
import java.util.Stack;


public class cardPile{ //masi bingung

    private final Stack<Card> cards = new Stack<>();

    // narik satu kartu dari stack
    public Card drawCard(){
        return cards.pop();
    }

    // untuk banyak kartu yang di draw
    public int getSize(){
        return cards.size();
    }

}