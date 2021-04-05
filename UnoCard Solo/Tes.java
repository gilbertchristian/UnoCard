import java.util.Scanner;

public class Tes {
    private static boolean playing = true;
    public static void main(String[] args) {
        System.out.println("Selamat datang di permainan HIJI!\n");
        
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        game.initGame();
        game.initCard();
        System.out.println("\nPermainan bisa diakses dengan perintah berikut: ");
        System.out.println("[0] : Perintah permainan");
        System.out.println("[1] : Memulai giliran");
        System.out.println("[2] : Menampilkan kartu pemain");
        System.out.println("[3] : Melihat daftar pemain");
        System.out.println("[4] : Melihat giliran pemain");
        System.out.println("[5] : Bantuan permainan");
        System.out.println("[6] : Keluar dari permainan");
        

        while (playing){
            System.out.println("==============================================");
            System.out.print("Perintah: ");
            int command = input.nextInt();
            System.out.println("==============================================");

            switch (command){
                case 0 :
                    System.out.println("\nPermainan bisa diakses dengan perintah berikut: ");
                    System.out.println("[0] : Perintah permainan");
                    System.out.println("[1] : Memulai giliran");
                    System.out.println("[2] : Menampilkan kartu pemain");
                    System.out.println("[3] : Melihat daftar pemain");
                    System.out.println("[4] : Melihat giliran pemain");
                    System.out.println("[5] : Bantuan permainan");
                    System.out.println("[6] : Keluar dari permainan");
                    break;
                case 1 :
                    System.out.println("Sekarang giliran " + game.getCurrentPlayerName());
                    game.listCard();
                    game.showTableCard();
                    game.discard();
                    break;
                case 2 :
                    game.listCard();
                    break;
                case 3 :
                    game.listPlayers();
                    break;
                case 4 :
                    game.viewPlayerinTurn();
                    break;
                case 5 :
                    System.out.println("HIJI merupakan sebuah game adaptasi dari permainan kartu 'UNO'");
                    System.out.println("Teknis permainan: ");
                    System.out.println("- Jumlah pemain 2-6 orang.");
                    System.out.println("- Permainan dimulai dengan 1 kartu angka yang dipilih secara acak sebagai kartu awal.");
                    System.out.println("- Pemain yang memulai permainan dipilih secara acak.");
                    System.out.println("- Pemain mengeluarkan kartu berdasarkan warna yang sama dengan melihat kekuatan kartu yang dikeluarkan.");
                    System.out.println("");
                    System.out.println("Peraturan permainan ");
                    System.out.println("- Apabila pemain tidak memiliki kartu untuk dikeluarkan, pemain wajib mengambil 1 kartu dari deck.");
                    System.out.println("- Apabila kartu yang diambil bisa dikeluarkan, pemain diperbolehkan mengeluarkan kartu tersebut.");
                    System.out.println("- Apabila kartu yang diambil tidak bisa dikeluarkan, maka giliran permainan akan dilanjutkan ke pemain selanjutnya.");
                    System.out.println("");
                    System.out.println("Jenis Kartu ");
                    System.out.println("- Angka 0-9 → Kartu biasa yang dikeluarkan berdasarkan warnanya.");
                    System.out.println("- Draw 2    → Pemain selanjutnya mengambil 2 kartu. Apabila pemain tersebut mengeluarkan Draw 2 juga, maka pemain selanjutnya mengambil 4 kartu, dan seterusnya.");
                    System.out.println("- Draw 4    → Pemain selanjutnya mengambil 4 kartu. Pemain yang mengeluarkan kartu Draw 4 dapat memilih warna yang dimainkan selanjutnya.");
                    System.out.println("- Wildcard  → Pemain memilih warna yang dapat dikeluarkan pemain selanjutnya.");
                    System.out.println("- Reverse   → Urutan giliran pemain dibalik.");
                    System.out.println("- Skip      → Giliran pemain selanjutnya dilewati.");
                    System.out.println("");
                    System.out.println("Multiple Discard");
                    System.out.println("Pemain dapat mengeluarkan lebih dari 1 kartu bila terdapat kartu dengan jenis yang sama.");
                    System.out.println("- Draw 2    → pemain selanjutnya mengambil kartu sebanyak 2 kali jumlah kartu yang dikeluarkan.");
                    System.out.println("- Draw 4    → pemain selanjutnya mengambil kartu sebanyak 4 kali jumlah kartu yang dikeluarkan. Pemain yang mengeluarkan hanya dapat memilih satu warna.");
                    System.out.println("- Skip      → jumlah pemain yang dilewati sebanyak jumlah kartu yang dikeluarkan.");
                    System.out.println("- Reverse   → urutan pemain dibalik sebanyak kartu yang dikeluarkan.");
                    System.out.println("- Wildcard  → pemain hanya dapat memilih satu warna.");
                    System.out.println("");
                    break;
                case 6 : 
                    playing = false;
                    System.out.println("Terimakasih telah bermain");
                    break;
                default :
                    System.out.println("Kode tidak tersedia di permainan, coba lagi");
            }
        }

        input.close();
    }
}
