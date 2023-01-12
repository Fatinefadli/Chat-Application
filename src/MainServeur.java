import java.util.Scanner;

public class MainServeur {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Donner le port d'ecoute de la boite � lettre : ");
        int port = 1000;//clavier.nextInt();
        System.out.println("J'�coute sur "+port);
        Serveur reception = new Serveur(port,1024);
        reception.receptASCII();
        //reception.receptFile_();
        reception.receptImg();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        reception.envoiMsgASCII("127.0.0.13", 2001, "ping");
    }

}
