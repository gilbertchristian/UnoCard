import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private SecureRandom rand = new SecureRandom();
    private ArrayList<Card> decks = new ArrayList<>();
    
    Color[] listColor = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
    Value[] listValue = { Value.ZERO, Value.ONE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX, Value.SEVEN, Value.EIGHT, Value.NINE};
    
    public Deck(){   
        for (Color color : listColor) {
            decks.add(new DrawTwo(color));
            decks.add(new Reverse(color));
            decks.add(new Skip(color));
            for (Value value : listValue) {
                decks.add(new Normal(color, value));
            }
        }
        decks.add(new DrawFour());
        decks.add(new Wild());
    }

    public Color getOneRandomColor(){
        return listColor[rand.nextInt((listColor.length))];
    }

    public Value getOneRandomValue(){
        return listValue[rand.nextInt((listValue.length))];
    }

    public Card getOneRandomCard(){
        Collections.shuffle(decks);
        return decks.get(0);
    }
}
