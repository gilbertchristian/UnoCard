//package UnoCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;

//source : https://www.youtube.com/playlist?list=PLu_zq6omCvuQ_ZoKnE8-CE2nF113p9pxd

public class Game {
    public int currentPlayer;
    //private String[] allPlayers;
    private Card tableCard; //ga yakin
    private boolean declareHiji; //ga yakin
    
    private ArrayList<Player> players; //List  pemain
    private ArrayList<Card> cardPile; //List tumpukkan kartu buat discard-an

    boolean gameDirection; //clockwise = true, anticlockwise = false
    
    private int playerNum;
    private String playerName;
    private String[] temp;

    //random
    Random ran = new Random();

    public void initGame (){
        // JUMLAH PEMAIN
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan jumlah pemain: ");
        playerNum = input.nextInt();
        
        //APABILA JUMLAH PEMAIN TIDAK 2-6
        while (playerNum<2 || playerNum>6){
            System.out.println("Maaf, jumlah pemain minimal 2 orang dan maksimal 6 orang.");
            System.out.println("Silakan masukkan jumlah pemain yang sesuai: ");
            playerNum = input.nextInt();
        }
        
        //NAMA PEMAIN
        System.out.print("Masukkan nama pemain: ");
        int i =  0;
        while (playerNum > 0){
            playerName = input.nextLine();
            temp[i] = playerName;
            System.out.print("");
            i++;
            playerNum--;
        }

        //RANDOM URUTAN PEMAIN
        int min = 0;
        int max = temp.length;
        int randPlayer = (int)Math.floor(Math.random()*(max-min+1)+min);
        while (playerNum < temp.length){
            Player player = new Player(temp[randPlayer]);
            players.add(player);
            playerNum++;
            randPlayer = randPlayer + 1 % temp.length;
        }

        input.close();
    }

    public void listCard(){ //F02
        for (int i = 0; i < getPlayerCardSize(currentPlayer); i++){
            System.out.println((i+1) + ". " + getPlayerCard(currentPlayer, i).toString());
        }
    }

    public void initCard(){
        //GIMANA RANDOM ENUM?
        Card tableCard = new Card(/*color*/, /*value*/);
        cardPile.add(tableCard);
    }

    public Card getLastCardThrown(){
        return cardPile.get(cardPile.size() - 1);
    }

    public boolean validDiscard(Card card){
        return card.getColor() == getLastCardThrown().getColor() || card.getValue() == getLastCardThrown().getValue();
    }

    public boolean emptyHand(int player_id){
        return players.get(player_id).hand.isEmpty();
    }

    public boolean isOneCardLeft(int player_id){
        return getPlayerCardSize(currentPlayer) == 1;
    }

    public void discard(int card_id){ //F03
        Card card = getPlayerCard(currentPlayer, card_id);
        //DISCARDNYA VALID
        if (validDiscard(card)){
            cardPile.add(card);
            players.get(currentPlayer).hand.remove(card);
        
            //KARTU ABIS
            if (emptyHand(currentPlayer)){
                 System.out.println("Congratulation " + getPlayerName(currentPlayer) + "! You're the winner of this round."); 
            }
            //KARTU TINGGAL SATU
            if (isOneCardLeft(currentPlayer)){ 
                // if (declareHiji) {
                //     //continue
                //     //nextplayer
                // }else{
                //     //draw 2
                //     drawTwo(currentPlayer);
                //     //nextplayer
                // }
            }else{
                // if (declareHiji) {
                //     //draw 2
                //     drawTwo(currentPlayer);
                //     //nextplayer
                // }else{
                //     //continue
                //     //nextplayer
                // }
            }
            switch(card.getValue().name()) { //ENUM TO STRING
                case "Normal":
                    nextPlayer(1);
                case "Wild":
                    //milih warna
                    nextPlayer(1);
                case "Reverse":
                    gameDirection ^= true; //XOR, kalau true jadi false, kalau false jadi true
                    if (gameDirection == true){
                        nextPlayer(1);
                    }else{
                        prevPlayer(1);
                    }
                case "Skip":
                    if (gameDirection == true){
                        System.out.println(players.get(currentPlayer) + " has been skipped.");
                        nextPlayer(2);
                    }else{
                        System.out.println(players.get(currentPlayer) + " has been skipped."); 
                        prevPlayer(2);
                    }
                case "Draw 2":
                    //draw2
                    drawTwo(currentPlayer);
                    nextPlayer(1);
                case "Draw 4":
                    //draw4
                    drawFour(currentPlayer);
                    nextPlayer(1);
                }   
        }else{
            //draw 1
            //rekursif tapi cuma bisa sekali 
        }
    }

    public String getPlayerName(int player_id){
        return players.get(player_id).name;
    }

    public int getPlayerCardSize(int player_id){
        return players.get(player_id).jumlahKartu();
    }

    public Card getPlayerCard(int player_id, int card_id){
        return players.get(player_id).currentCard(card_id);
    }

    public void nextPlayer(int num){
        currentPlayer = currentPlayer + num % players.size();
    }

    public void prevPlayer(int num){
        currentPlayer = currentPlayer - num % players.size();
    }

    public void listPlayers(){ //F06
        for (int i = 0; i < players.size(); i++){
            System.out.println("Pemain "+ (i+1) +": "+ getPlayerName(i));
            System.out.println("Jumlah Kartu: "+ getPlayerCardSize(i));
            if(currentPlayer == i){
                System.out.println("Sedang giliran");
            }else {
                System.out.println("Tidak sedang giliran");
            }
        }
    }

    public void viewPlayerinTurn (){ //F07
        System.out.println("Sedang giliran: " + getPlayerName(currentPlayer));
        System.out.println("Giliran selanjutnya: " + getPlayerName(currentPlayer + 1));
    }

    public void draw(int currentPlayer, int numofdrawcard){
        String card;
        
        //loop
        for (int i = 0; i < numofdrawcard; i++){
            //random draw
            card = namalistcard[ran.nextInt(namalistcard.length)];
        
            //masukin ke tangan
            listCard.add(card);
        }

    }
    
    //buat draw 4
    public void drawFour(int currentPlayer){
        draw(player, 4);
    }
    
    //buat draw 2
    public void drawTwo(int currentPlayer){
        draw(player, 2);
    }

}