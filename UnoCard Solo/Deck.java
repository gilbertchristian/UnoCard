import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> decks = new ArrayList<>();
    private ArrayList<Card> normaldecks = new ArrayList<>();
    
    Color[] listColor = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
    Value[] listValue = { Value.ZERO, Value.ONE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX, Value.SEVEN, Value.EIGHT, Value.NINE};
    
    public Deck(){   
        for (Color color : listColor) {
            decks.add(new DrawTwo(color));
            decks.add(new Reverse(color));
            decks.add(new Skip(color));
            for (Value value : listValue) {
                decks.add(new Normal(color, value));
                normaldecks.add(new Normal(color, value));
            }
        }
        decks.add(new DrawFour());
        decks.add(new Wild());
    }

    public Card getOneNormalCard(){
        Collections.shuffle(normaldecks);
        return normaldecks.get(0);
    }

    public Card getOneRandomCard(){
        Collections.shuffle(decks);
        return decks.get(0);
    }
}
