public class Tes {
    public static void main(String[] args) {
        Game game = new Game();
        game.initGame();
        System.out.println(" ");

        game.listCard();
        System.out.println(" ");

        game.listPlayers();
        System.out.println(" ");

        game.viewPlayerinTurn();
        System.out.println(" ");

        game.initCard();
        System.out.println(game.tableCard.printCard());

        game.nextPlayer(2);
        game.drawOne(0);
        game.listCard();
    }
}
