public class Reverse extends Card{    
    
    public Reverse(Color color){
        super(color, Value.REVERSE);
    }

    public String printCard(){
        return super.getColor() + " " + "REVERSE";
    }
}
