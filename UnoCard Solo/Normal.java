public class Normal extends Card{
    
    public Normal(Color color, Value value){
        super(color, value);
    }

    public String printCard(){
        return super.getColor() + " " + super.getValue();
    }
}
