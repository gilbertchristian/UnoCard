public abstract class Card {
    private Color color;
    private Value value;

    protected Card(Color color, Value value){
        this.color = color;
        this.value = value;
    }

    public Color getColor(){
        return this.color;
    }

    public Value getValue(){
        return this.value;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setValue(Value value){
        this.value = value;
    }

    public abstract String printCard();
}
