public class DrawTwo extends Card{    
    
    public DrawTwo(Color color){
        super(color, Value.DRAW_2);
    }

    public String printCard(){
        return super.getColor() + " " + "DRAW_2";
    }
    
}
