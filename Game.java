import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import javax.smartcardio.Card;

//source : https://www.youtube.com/playlist?list=PLu_zq6omCvuQ_ZoKnE8-CE2nF113p9pxd

public class Game {
    private int currentPlayer;
    private String[] allPlayers;
    private Cards tableCard; //ga yakin
    private boolean declareHiji; //ga yakin
    
    //variable Cards tergantung variable di class card.java
    private ArrayList<ArrayList<Cards>> allPlayerCards //List semua kartu pemain
    private ArrayList<Cards> cardPile //List tumpukkan kartu buat discard-an

    boolean gameDirection; //clockwise = true, anticlockwise = false
    
    public Game(String[] player){
        //belom ada queue player + random firstplayer
        allPlayers = player;
        currentPlayer = 0;
        gameDirection = true;

        allPlayerCards = new ArrayList<ArrayList<Cards>>(); 
        cardPile = new ArrayList<Cards>();

        for (int i = 0; /*i < num_of_player*/; i++){ //ngisi kartu
            ArrayList<Cards> cardInHand = new ArrayList<Cards>(/*random 7 kartu*/);
            allPlayersCards.add(cardInHand); //array 7 kartu dimasukin ke array 
        }
    }

    public void StartGame(Game game){ //F01
        System.out.println("COMMANDS: ");
        System.out.println("F01: Start Game");
        System.out.println("F02: List Cards");
        System.out.println("F03: Discard");
        System.out.println("F04: Draw");
        System.out.println("F05: Declare HIJI");
        System.out.println("F06: List Players");
        System.out.println("F07: View Player in Turn");
        System.out.println("F08: Help");
        System.out.println("");

        for (int i = 0; i < player.length; i++){
            System.out.println("Player" + (i+1) + ": " + allPlayers[i]);
        }
        
        //shuffle dari list of color dan value
        Cards tableCard = new Cards(Collections.shuffle(Color), Collections.shuffle(Value));

    }

    public void ListsCard(int currentPlayer){ //F02
        for (int i = 0; i < getPlayerCardSize(currentPlayer); i++){
            System.out.println((i+1) + ". " + getPlayerCardId(currentPlayer, i));
        }
    }

    public void Discard(Cards card){ //F03
        if (card.getColor() == tableCard.getColor() || card.getValue() == tableCard.getValue()){ //kalau kartu cocok
            //discard
            if (getPlayerCardSize(currentPlayer) == 0){ //kalau kartu habis
                 System.out.println("Congratulation " + allPlayers[currentPlayer + 1] + "! You're the winner of this round."); 
            }else if (getPlayerCardSize(currentPlayer) == 1){
                if (declareHiji) {
                    //continue
                    //nextplayer
                }else{
                    //draw 2
                    //nextplayer
                }
            }else{
                if (declareHiji) {
                    //draw 2
                    //nextplayer
                }else{
                    //continue
                    //nextplayer
                }
                switch(card.getValue()) {
                    case "Normal":
                        discardCard(card);
                        nextPlayer();
                    case "Wild":
                        //continue
                        //nextplayer
                    case "Reverse":
                        gameDirection ^= true; //XOR, kalau true jadi false, kalau false jadi true
                        currentPlayer = allPlayers.length - 1;
                        //nextplayer
                    case "Skip":
                        //belom yakin itungannya
                        if (gameDirection){
                            System.out.println(allPlayers[currentPlayer + 1] + " has been skipped.");
                            currentPlayer = (currentPlayer + 1) % allPlayers.length; 
                        }else{
                            System.out.println(allPlayers[currentPlayer - 1] + " has been skipped."); 
                            currentPlayer = (currentPlayer - 1) % allPlayers.length; 
                        }
                        //nextplayer
                    case "Draw 2":
                        //draw2
                        //nextplayer
                    case "Draw 4":
                        //draw4
                        //nextplayer
                }
            } 
        }else{
            //draw 2
        }
    }

    public void discardCard (Cards card){
        for (int i = 0; i < getPlayerCardSize(currentPlayer); i++){
            if (getPlayerCardId(currentPlayer, i).getValue() == card.getValue()){
                cardPile.add(card);
                allPlayerCards.get(currentPlayer).remove(i);
            }
        }
    }

    public int getPlayerCardSize(int player_id){
        return allPlayerCards.get(player_id).size();
    }

    public int getPlayerCardId(int player_id, int card_id){
        return allPlayerCards.get(player_id).get(card_id);
    }

    public void nextPlayer(){
        currentPlayer += 1 % allPlayers.length;
    }
}