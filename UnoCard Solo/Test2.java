import java.util.Scanner;

class Declare extends Thread {  

    public void run() {  
            try
                {  
                Thread.sleep(3000);  
                System.out.println("drawTwo"); //masuk fungsi drawTwo
                }

            catch(InterruptedException e) {         
                
            }  

        }  
    
    public static void main(String args[]) {  
        Declare d = new Declare();
        Scanner sc = new Scanner(System.in);  
        d.start();  
        try
            {
            String read = sc.nextLine();
            if (read.equals("Y")) {
                d.interrupt(); 
                throw new HijiException("Tergantung"); //Harusnya ngelempar kondisi sesuai masukan kalo declare (dot apa)
            }

            else if (read.equals("N")) {
                d.interrupt();
                throw new HijiException("Tergantung juga"); //Harusnya ngelempar kondisi sesuai masukan kalo ngga declare (dot apa)
            }

        catch(HijiException e)
            {
            System.out.println("Declare/Nggak"); //tergantung throw nya
            }  
    }  
} 