public abstract class Card {
    private Color color;
    private Value value;

    public Card(Color color, Value value){
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

    public abstract void power();
}
