//package UnoCard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.InputMismatchException;

public class Game {
    public int currentPlayer = 0;
    public Card tableCard; //ga yakin
    //private boolean declareHiji; //ga yakin
    
    private ArrayList<Player> players = new ArrayList<>(); //List  pemain
    private ArrayList<Card> cardPile = new ArrayList<>(); //List tumpukkan kartu buat discard-an

    boolean gameDirection = true; //clockwise = true, anticlockwise = false

    public static final Deck deckCard = new Deck();

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
            for (int k = 0; k < 7; k++){
                p.addCard(deckCard.getOneRandomCard());
            }
        }
    }

    public void listCard(){ //F02
        System.out.println("Ini kartu " + players.get(currentPlayer).name + ": ");
        int i = 0;
        for (Card c : players.get(currentPlayer).hand){
            System.out.println("[" + (i+1) + "]" + c.printCard());
            i++;
        }
    }

    public void initCard(){
        tableCard = deckCard.getOneRandomCard();
        cardPile.add(tableCard);
    }

    public void listPlayers(){ //F06
        for (int i = 0; i < players.size(); i++){
            System.out.println("Pemain "+ (i+1) +": "+ players.get(i).name);
            System.out.println("Jumlah Kartu: "+ players.get(i).hand.size());
            if(currentPlayer == i){
                System.out.println("Sedang giliran\n");
            }else {
                System.out.println("Tidak sedang giliran\n");
            }
        }
    }

    public void viewPlayerinTurn (){ //F07
        System.out.println("Sedang giliran: " + getPlayerName(currentPlayer % players.size()));
        System.out.println("Giliran selanjutnya: " + getPlayerName((currentPlayer + 1) % players.size()));
    }

    public void draw(int playerId, int numofdrawcard){
        for (int i = 0; i < numofdrawcard; i++){
            Card drown = deckCard.getOneRandomCard();
            players.get(playerId).addCard(drown);
            System.out.println("Kartu terambil: " + drown.printCard());
        }
    }

    public void drawFour(int playerId){
        draw((currentPlayer + playerId)  % players.size(), 4);
    }
    
    public void drawTwo(int playerId){
        draw((currentPlayer + playerId)  % players.size(), 2);
    }

    public void drawOne(int playerId){
        draw((currentPlayer + playerId)  % players.size(), 1);
    }

    public void showTableCard(){
        System.out.println("Kartu terakhir: " + getLastCardThrown().printCard() + "\n");
    }

    public Card getLastCardThrown(){
        return cardPile.get(cardPile.size() - 1);
    }

    public boolean validDiscard(Card card){
        return card.getColor() == getLastCardThrown().getColor() || card.getValue() == getLastCardThrown().getValue() || card.getColor() == Color.WILD;
    }

    public boolean emptyHand(int playerId){
        return players.get(playerId).hand.isEmpty();
    }

    public boolean isOneCardLeft(int playerId){
        return getPlayerCardSize(playerId) == 1;
    }

    public void discard(){ //F03
        System.out.println("Kartu mana yang mau dibuang?");
        System.out.println("Menyerah? silahkan tekan [0] untuk draw 1");
        int cardId = input.nextInt();
        if (cardId == 0){
            drawOne(0);
            nextPlayer(1);
        } else {
            Card card = getPlayerCard(currentPlayer, cardId-1);
            //DISCARDNYA VALID
            if (validDiscard(card)){
                cardPile.add(card);
                players.get(currentPlayer).hand.remove(card);
            
                //KARTU ABIS
                if (emptyHand(currentPlayer)){
                    System.out.println("Congratulation " + getPlayerName(currentPlayer) + "! You're the winner of this round."); 
                }
                // //KARTU TINGGAL SATU
                // if (isOneCardLeft(currentPlayer)){ 
                //     //
                // }else{
                //     //
                // }
                checkValue(card);        
            }else{
                System.out.println("Kartu tidak cocok, coba lagi");
                listCard();
                discard();
            }
        }
    }

    public void checkValue(Card card){
        switch(card.getValue().name()) { //ENUM TO STRING
            case "ZERO"  :
                nextPlayer(1);
                break;
            case "ONE"  :
                nextPlayer(1);
                break;
            case "TWO"  :
                nextPlayer(1);
                break;
            case "THREE"  :
                nextPlayer(1);
                break;
            case "FOUR"  :
                nextPlayer(1);
                break;
            case "FIVE"  :
                nextPlayer(1);
                break;
            case "SIX"  :
                nextPlayer(1);
                break;
            case "SEVEN"  :
                nextPlayer(1);
                break;
            case "EIGHT"  :
                nextPlayer(1);
                break;
            case "NINE"  :
                nextPlayer(1);
                break;
            case "WILD":
                setColor();
                nextPlayer(1);
                break;
            case "REVERSE":
                gameDirection ^= true; //XOR, kalau true jadi false, kalau false jadi true
                Collections.reverse(players);
                break;
            case "SKIP":
                if (gameDirection){
                    System.out.println(players.get((currentPlayer+1) % players.size()).name + " has been skipped.");
                    nextPlayer(2);
                    break;
                }else{
                    System.out.println(players.get((currentPlayer-1) % players.size()).name + " has been skipped."); 
                    prevPlayer(2);
                    break;
                }
            case "DRAW_2":
                drawTwo(1);
                nextPlayer(1);
                break;
            case "DRAW_4":
                setColor();
                drawFour(1);
                nextPlayer(1);
                break;
            default :
        }
    }

    public void setColor(){
        try{
            System.out.println("[1] : RED");
            System.out.println("[2] : BLUE");
            System.out.println("[3] : GREEN");
            System.out.println("[4] : YELLOW");
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

    public String getPlayerName(int playerId){
        return players.get(playerId).name;
    }
    
    public String getCurrentPlayerName(){
        return players.get(currentPlayer).name;
    }

    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    public int getPlayerCardSize(int playerId){
        return players.get(playerId).jumlahKartu();
    }

    public Card getPlayerCard(int playerId, int cardId){
        return players.get(playerId).currentCard(cardId);
    }

    public void nextPlayer(int num){
        currentPlayer = (currentPlayer + num) % players.size();
    }

    public void prevPlayer(int num){
        currentPlayer = (currentPlayer - num) % players.size();
    }

    
}