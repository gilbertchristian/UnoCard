//package UnoCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;

public class Game {
    public int currentPlayer;
    private Card tableCard; //ga yakin
    private boolean declareHiji; //ga yakin
    
    private ArrayList<Player> players = new ArrayList<>(); //List  pemain
    public ArrayList<Card> cardPile = new ArrayList<>(); //List tumpukkan kartu buat discard-an

    boolean gameDirection; //clockwise = true, anticlockwise = false

    public static final Deck deckCard = new Deck();

    //random
    Random ran = new Random();

    Scanner input = new Scanner(System.in);

    public void initGame (){ //F01 CLEAR
        int playerNum;
        String playerName;

        //JUMLAH PEMAIN
        System.out.print("Masukkan jumlah pemain: ");
        playerNum = input.nextInt();
        
        //APABILA JUMLAH PEMAIN TIDAK 2-6
        while (playerNum<2 || playerNum>6){
            System.out.println("Maaf, jumlah pemain minimal 2 orang dan maksimal 6 orang.");
            System.out.print("Silakan masukkan jumlah pemain yang sesuai: ");
            playerNum = input.nextInt();
        }
        
        //NAMA PEMAIN
        int i = 0;
        while (i < playerNum){
            System.out.print("Masukkan nama pemain " + (i+1) + ": ");
            playerName = input.next();
            System.out.print("");
            players.add(new Player(playerName));
            i++;
        }
        Collections.shuffle(players);
    
        //BAGI BAGI KARTU
        for (Player p : players){
            // System.out.println(p.name);
            // int count = 0;
            for (int k = 0; k < 7; k++){
                p.addCard(deckCard.getOneRandomCard());
                // System.out.println(count + " " + p.hand.get(k).printCard());
                // count++;
            }
        }

        //input.close();
    }

    public void listCard(){ //F02
        System.out.println("Ini kartu player " + currentPlayer + ": ");
        for (int i = 0; i < players.get(currentPlayer).hand.size(); i++){
            System.out.println((i+1) + ". " + players.get(0).hand.get(i).printCard());
        }
    }

    public void initCard(){
        tableCard = deckCard.getOneRandomCard();
        cardPile.add(tableCard);
    }

    public Card getLastCardThrown(){
        return cardPile.get(cardPile.size() - 1);
    }

    public boolean validDiscard(Card card){
        return card.getColor() == getLastCardThrown().getColor() || card.getValue() == getLastCardThrown().getValue();
    }

    public boolean emptyHand(int playerId){
        return players.get(playerId).hand.isEmpty();
    }

    public boolean isOneCardLeft(int playerId){
        return getPlayerCardSize(playerId) == 1;
    }

    public void discard(int cardId){ //F03
        int cycle = 0;
        Card card = getPlayerCard(currentPlayer, cardId);
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
                //
            }else{
                //
            }
            
            checkValue(card);        
        }else{
            drawOne(0);
            discard(cardId);
            cycle += 1;
            if(cycle > 1){
                nextPlayer(1);
            }
        }
    }

    public void draw(){
        Card drown = deckCard.getOneRandomCard();
        players.get(currentPlayer).addCard(drown);

    }

    public void checkValue(Card card){
        switch(card.getValue().name()) { //ENUM TO STRING
            case "Normal":
                nextPlayer(1);
                break;
            case "Wild":
                setColor();
                nextPlayer(1);
                break;
            case "Reverse":
                gameDirection ^= true; //XOR, kalau true jadi false, kalau false jadi true
                if (gameDirection == true){
                    nextPlayer(1);
                    break;
                }else{
                    prevPlayer(1);
                    break;
                }
            case "Skip":
                if (gameDirection == true){
                    System.out.println(players.get(currentPlayer) + " has been skipped.");
                    nextPlayer(2);
                    break;
                }else{
                    System.out.println(players.get(currentPlayer) + " has been skipped."); 
                    prevPlayer(2);
                    break;
                }
            case "Draw 2":
                drawTwo(1);
                nextPlayer(1);
                break;
            case "Draw 4":
                setColor();
                drawFour(1);
                nextPlayer(1);
                break;
            default :
        }
    }

    public void setColor(){
        try{
            int pick = input.nextInt();
            if (pick == 1){
                getLastCardThrown().setColor(Color.RED);
            } else if (pick == 2){
                getLastCardThrown().setColor(Color.BLUE);
            } else if (pick == 3){
                getLastCardThrown().setColor(Color.GREEN);
            } else if (pick == 4){
                getLastCardThrown().setColor(Color.YELLOW);
            } else {
                System.out.print("Input is not valid! Pick another one:");
                setColor();
            }
        }catch(InputMismatchException ex){
            System.out.println("Input should be an integer!");
            setColor();
        }
    }

    public Color toColor (String color){
        if (color == "RED"){
            return Color.RED;
        } else if (color == "BLUE"){
            return Color.BLUE;
        } else if (color == "YELLOW"){
            return Color.YELLOW;
        } else {
            return Color.GREEN;
        }
    }

    public String getPlayerName(int playerId){
        return players.get(playerId).name;
    }

    public int getPlayerCardSize(int playerId){
        return players.get(playerId).jumlahKartu();
    }

    public Card getPlayerCard(int playerId, int cardId){
        return players.get(playerId).currentCard(cardId);
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

    public void draw(int playerId, int numofdrawcard){
        for (int i = 0; i < numofdrawcard; i++){
            Card drown = deckCard.getOneRandomCard();
            players.get(playerId).addCard(drown);
        }

    }
    
    //buat draw 4
    public void drawFour(int playerId){
        draw(currentPlayer + playerId, 4);
    }
    
    //buat draw 2
    public void drawTwo(int playerId){
        draw(currentPlayer + playerId, 2);
    }

    public void drawOne(int playerId){
        draw(currentPlayer + playerId, 1);
    }

}