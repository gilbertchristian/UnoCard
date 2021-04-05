//package UnoCard;

import java.util.Scanner;

public class Main {
    //private static boolean playing;

    public static void main(String[] args) {
        System.out.println("Welcome to HIJI-Card!");
        Scanner input = new Scanner(System.in);
        boolean playing = true;
        while (playing){
            String command = input.next();     
            Game game = new Game();
            switch(command.toUpperCase()){
                case "F01" :
                    game.initGame();
                    break;
                case "F02" :
                    game.listCard();
                    break;
                case "F03" :
                    System.out.print("Pilih nomor kartu yang ingin dikeluarkan: ");
                    //int cardId = input.nextInt();
                    //game.discard(cardId);
                    break;
                case "F04" :
                case "F05" :
                case "F06" :
                    game.listPlayers();
                    break;
                case "F07" :
                    //game.viewPlayerinTurn();
                    break;
                case "F08":
                    // tampilkan deskripsi aturan permainan
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
                case "Menu":
                    // tampilkan bantuan program
                    System.out.println("Gunakan menu berikut:");
                    System.out.println("Start Game \t\t untuk memulai permainan");
                    System.out.println("List Card \t\t untuk melihat kartu yang ada di tangan");
                    System.out.println("Discard \t\t untuk mengeluarkan kartu");
                    System.out.println("Draw \t\t\t untuk mengambil satu kartu dari deck");
                    System.out.println("List Players \t\t untuk melihat semua nama pemain, jumlah kartu di tangan, dan giliran");
                    System.out.println("View Player in Turn \t untuk melihat pemain yang sedang dalam giliran");
                    System.out.println("Help \t\t\t untuk menampilkan deskripsi aturan permainan");
                    System.out.println("Menu \t\t\t untuk menampilkan menu bantuan");
                    break;
                case "Exit":
                    playing = false;
                    break;
                default:
                    // tampilkan ucapan argumen tidak dikenali
                    System.out.println("Menu tidak ada, silahkan ketik 'Menu' untuk bantuan");
            }
        }
        input.close();  
    }
}
