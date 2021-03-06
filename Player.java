//package UnoCard;

/**
 * Player Class
 * player ketika dibuat pasti memiliki nama dan kartu serta urutan main
 * listCards untuk mengetahui kartu yang dimiliki player
 */

import java.util.ArrayList;
import java.util.*;

public class Player{
    public String name;
    public ArrayList<Card> hand; //Kartu yang dimiliki masing2 player

    public Player(String name){
        this.name = name; 
        this.hand = new ArrayList<Card>(7); //shuffle(Card)
    }
    
    //Asumsi: Urutan pemain di main program lengkap dengan shufflenya

    public void listCards (ArrayList<Card> hand){
        for (int i = 0; i < hand.size(); i++){
            System.out.println((i + 1) + ". " + hand.get(i));
        } 
    }
    
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

    /*public listPlayer(){
        
        for (int i = 0; i < totalPlayer; i++){
            System.out.println("Pemain " + (i + 1) + ": " + listurutan.get(0));
            System.out.println("Jumlah Kartu: " + player.jumlahKartu());
            System.out.println(player.status); //Sedang giliran/Tidak sedang giliran
        }
    }*/
    
}