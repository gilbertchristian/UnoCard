import java.util.ArrayList;

public class Player{
    public String name;
    public ArrayList<Card> hand; //Kartu yang dimiliki masing2 player
   
    public Player(String name){
        this.name = name; 
        this.hand = new ArrayList<Card>(); //shuffle(Card)
    }

    // public void listCards (ArrayList<Card> hand){
    //     for (int i = 0; i < hand.size(); i++){
    //         System.out.println((i + 1) + ". " + hand.get(i));
    //     } 
    // }

    public void addCard(Card card){
        hand.add(card);
    }

    public void removeCard(int card){
        hand.remove(card);
        //int Card adalah index dari arraylist
    }

    public int jumlahKartu(){
        return hand.size();
        //Untuk dicek pas Declare HIJI
    }

    public Card currentCard(int id){
        return hand.get(id);
    }
    
}