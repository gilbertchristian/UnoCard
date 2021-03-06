//package UnoCard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game {
    public int currentPlayer = 0;
    public Card tableCard;
    //private boolean declareHiji;
    
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> cardPile = new ArrayList<>(); 

    boolean gameDirection = true;
    int multi = 0;
    int counter2 = 1;
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
            for (int k = 0; k < 7 ; k++){
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
        tableCard = deckCard.getOneNormalCard();
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

    public boolean validMultiDiscard(Card card){
        return card.getColor() == getLastCardThrown().getColor() && card.getValue() == getLastCardThrown().getValue() || card.getColor() == Color.WILD;
    }

    public void isEmptyHand(){
        if (players.get(currentPlayer).hand.isEmpty()){
            System.out.println("Yey! " + getPlayerName(currentPlayer) + " Menang"); 
            System.out.println("Terimakasih telah bermain");
            System.exit(0);
        }
    }

    public boolean isOneCardLeft(int playerId){
        return getPlayerCardSize(playerId) == 1;
    }

    public void remove(Card card){
        cardPile.add(card);
        players.get(currentPlayer).hand.remove(card);
    }

    public void discard(){ //F03
        listCard();
        showTableCard();
        System.out.println("Kartu mana yang mau dibuang?");
        System.out.println("Tekan [-1] untuk draw 1 dan menyerah");
        int cardId = input.nextInt();
        if (cardId == -1){
            drawOne(0);
        }else if (cardId > 0){
            Card card = getPlayerCard(currentPlayer, cardId-1);
            //DISCARDNYA VALID
            if (multi==1){
                if (validMultiDiscard(card)){
                    remove(card);
                    //KARTU ABIS
                    isEmptyHand();
                    //KARTU TINGGAL SATU
                    declareHji();
                    specialDrawTwo();
                    checkValue(card);  
                }else{
                    System.out.println("Kartu tidak cocok, coba lagi");
                    discard();
                } 
            }else {
                if (validDiscard(card)){
                    remove(card);
                    //KARTU ABIS
                    isEmptyHand();
                    //KARTU TINGGAL SATU
                    declareHji();
                    specialDrawTwo();
                    checkValue(card);  
                }else{
                    System.out.println("Kartu tidak cocok, coba lagi");
                    listCard();
                    discard();
                } 
            }
        }else{
            System.out.println("Nomor kartu tidak valid, coba lagi");
            discard();
        }
        //MULTIDISCARD
        System.out.println("Masih mau buang kartu?");
        System.out.println("[1] Ya");
        System.out.println("[2] Tidak");
        multi = input.nextInt();
        if (multi==1){
            discard();
        }else{
            System.out.println("Giliran kamu selesai");
        }
    }

    public void declareHji(){
        System.out.println("Declare HIJI? [Please enter value 1 or 2]");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        long then = System.currentTimeMillis();
        int declare = input.nextInt();
        long now = System.currentTimeMillis();
        double waktu = ((now - then) / 1000d);
        if ( declare == 1){
            if (isOneCardLeft(currentPlayer)){
                if (waktu > 3d){
                    System.out.println("Waktu kamu: " + waktu);
                    System.out.println("Declare hiji gagal.");
                    drawTwo(0);
                }
                else{
                    System.out.println("Waktu kamu: " + waktu);
                    System.out.println("Declare HIJI berhasil");
                }
            }
            else{
                System.out.println("Belum boleh declare HIJI");
                drawTwo(0);
            }
        }
        else if (declare == 2){
            if (isOneCardLeft(currentPlayer)){
                System.out.println("kartu kamu tinggal 1, kamu seharusnya declare HIJI");
                drawTwo(0);
            } 
        }
    }

    public void checkValue(Card card){
        switch(card.getValue().name()) { //ENUM TO STRING
            case "WILD":
                setColor();
                break;
            case "REVERSE":
                gameDirection ^= true; //XOR, kalau true jadi false, kalau false jadi true
                break;
            case "SKIP":
                if (gameDirection){
                    System.out.println("Giliran " + players.get((currentPlayer+1) % players.size()).name + " hangus");
                    currentPlayer = (currentPlayer + 1) % players.size();
                    break;
                }else{
                    System.out.println("Giliran " + players.get((currentPlayer-1) % players.size()).name + " hangus"); 
                    currentPlayer = (currentPlayer - 1) % players.size();
                    if (currentPlayer==-1){
                        currentPlayer = players.size() - 1;
                    }
                    break;
                }
            case "DRAW_2":
                //drawTwo(1);
                break;
            case "DRAW_4":
                setColor();
                drawFour(1);
                break;
            default :
        }
    }

    public void specialDrawTwo(){
        if (cardPile.get(cardPile.size()-2).getValue() == Value.DRAW_2 && getLastCardThrown().getValue() == Value.DRAW_2){
            counter2+=1;
        }
        if (cardPile.get(cardPile.size()-2).getValue() == Value.DRAW_2 && getLastCardThrown().getValue() != Value.DRAW_2){
            draw(currentPlayer, 2*counter2);
            players.get(currentPlayer).addCard(getLastCardThrown());
            cardPile.remove(cardPile.size()-1);
            getLastCardThrown().setValue(Value.NONE);
            counter2 = 1;
        }
    }

    public void endOfTurn(){
        if (gameDirection){
            nextPlayer(1);
        }else{
            prevPlayer(1);
        }
    }

    public void setColor(){
        // try{
            System.out.println("Silahkan pilih warna");
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
                System.out.print("Silahkan masukkan no 1 - 4");
                setColor();
            }
        // }catch(InputMismatchException ex){
        //     System.out.println("Input should be an integer!");
        //     setColor();
        // }
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
        if (currentPlayer==-1){
            currentPlayer = players.size()-1;
        }
    }

}