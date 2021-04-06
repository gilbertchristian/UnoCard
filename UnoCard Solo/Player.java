import java.util.ArrayList;

public class Player{
    public String name;
    public ArrayList<Card> hand; //Kartu yang dimiliki masing2 player
   
    public Player(String name){
        this.name = name; 
        this.hand = new ArrayList<Card>();
    }

    public void addCard(Card card){
        hand.add(card);
    }

    public void removeCard(int card){
        hand.remove(card);
    }

    public int jumlahKartu(){
        return hand.size();
    }

    public Card currentCard(int id){
        return hand.get(id);
    }
}