import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private Random rand = new Random();
    public ArrayList<Card> deck;


    Color[] listColor = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };
    Value[] listValue = { Value.ZERO, Value.ONE, Value.TWO, Value.THREE, Value.FOUR, Value.FIVE, Value.SIX, Value.SEVEN, Value.EIGHT, Value.NINE};
    
    {   
        for (Color color : listColor) {
            deck.add(new DrawTwo(color));
            deck.add(new Reverse(color));
            deck.add(new Skip(color));
            for (Value value : listValue) {
                deck.add(new Normal(color, value));
            }
        }
        deck.add(new DrawFour());
        deck.add(new Wild());
    }

    public Color getOneRandomColor(){
        return listColor[rand.nextInt((listColor.length))];
    }

    public Value getOneRandomValue(){
        return listValue[rand.nextInt((listValue.length))];
    }

    public Card getOneRandomCard(){
        Collections.shuffle(deck);
        return deck.get(0);
    }
}
