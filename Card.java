public class Card{
    enum Color{
        Red, Blue, Green, Yellow, Wild, values;

        private static final Color[] colors = Color.values();
        public static Color getColor (int i){
            return Color.colors[i];
        }
    }
    enum Value{
        Zero, One, Two, Trhee, Four, Five, Six, Seven, Eight, Nine, DrawTwoo, Skip, Reverse, Wild, Wild_Four;

        private static final Value[] values = Value.values();
        public static Value getValue (int i){
            return Value.values[i];
        }    
    }
    private final Color color;
    private final Value value;

    public Card (final Color color, final Value value){
        this.color = color;
        this.value = value;
    }

    public Color getColor(){
        return this.color;
    }
    public Value getValue(){
        return this.value;
    }
    public String toString(){
        return value + " " + color;
    }
}
