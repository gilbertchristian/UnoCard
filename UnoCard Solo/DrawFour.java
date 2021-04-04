public class DrawFour extends Card{    
    
    public DrawFour(){
        super(Color.WILD, Value.DRAW_4);
    }

    public String printCard(){
        return super.getColor() + " " + "DRAW_4";
    }
    
}
