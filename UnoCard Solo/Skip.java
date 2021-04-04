public class Skip extends Card{    
    
    public Skip(Color color){
        super(color, Value.SKIP);
    }

    public String printCard(){
        return super.getColor() + " " + "SKIP";
    }

}
