import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

//source : https://www.youtube.com/playlist?list=PLu_zq6omCvuQ_ZoKnE8-CE2nF113p9pxd

public class Game {
    private int currentPlayer;
    //private String[] allPlayers;
    private Card tableCard; //ga yakin
    private boolean declareHiji; //ga yakin
    
    //variable Cards tergantung variable di class card.java
    private ArrayList<Player> players; //List  pemain
    private ArrayList<Card> cardPile; //List tumpukkan kartu buat discard-an

    boolean gameDirection; //clockwise = true, anticlockwise = false
    
    private int playerNum;
    private String playerName;
    private String[] temp;

    // public Game(String[] player){

    //     //belom ada queue player + random firstplayer
    //     allPlayers = player;
    //     currentPlayer = 0;
    //     gameDirection = true;

    //     allPlayerCards = new ArrayList<ArrayList<Cards>>(); 
    //     cardPile = new ArrayList<Cards>();

    //     for (int i = 0; /*i < num_of_player*/; i++){ //ngisi kartu
    //         ArrayList<Cards> cardInHand = new ArrayList<Cards>(/*random 7 kartu*/);
    //         allPlayersCards.add(cardInHand); //array 7 kartu dimasukin ke array 
    //     }
    // }
    
    public void initGame (){
        // JUMLAH PEMAIN
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan jumlah pemain: ");
        playerNum = input.nextInt();

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
        //tableCard masukin ke cardPile

    }

    public void ListsCard(int currentPlayer){ //F02
        for (int i = 0; i < getPlayerCardSize(currentPlayer); i++){
            System.out.println((i+1) + ". " + getPlayerCardId(currentPlayer, i));
        }
    }

    public void Discard(Cards card){ //F03
        //tableCard ganti jadi cardPile tapi getTop nya aja, tapi bingung
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
                    drawtwocards(currentPlayer);
                    //nextplayer
                }
            }else{
                if (declareHiji) {
                    //draw 2
                    drawtwocards(currentPlayer);
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
                        drawtwocards(currentPlayer);
                        //nextplayer
                    case "Draw 4":
                        //draw4
                        drawfourcards(currentPlayer);
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

    public int getPlayerCardSize(int player_id){ //getter
        return allPlayerCards.get(player_id).size();
    }

    public int getPlayerCardId(int player_id, int card_id){ //getter 
        return allPlayerCards.get(player_id).get(card_id);
    }

    public void nextPlayer(){
        currentPlayer += 1 % allPlayers.length;
    }

    public void listPlayers(ArrayList<Player> player){ //F06
        for (i=0; i<player.size(); i++){
            System.out.println("Pemain "+ (i+1) +": "+ player[i].name)
            System.out.println("Jumlah Kartu: "+ player[i].ListsCard.size())
            if(currentPlayer(player) == player[i]){  //nama method blom bener
                System.out.println("Sedang giliran");
            }else {
                System.out.println("Tidak sedang giliran");
            }
        }
    }

    private void drawtwocards(Player player){
        drawCards(player, 2);
    }

    private void drawfourcards(Player player){
        drawCards(player, 4);
    }

    private List<Card> drawCards(Player player, int numberofdraw){
        int num = Math.num(numberofcard, cardPile.getSize());
        var drawnCards = new ArrayList<Card>();
    
        for (int i = 0; i < num; i++){
            var drawnCard = cardPile.drawCard();
            drawnCards.add(drawnCard);
    
            player.addToHandCards(drawnCard);
        }
        
    }
}