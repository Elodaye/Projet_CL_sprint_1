package serveur;

import java.io.IOException;
import java.net.Socket;

/** Processus permettant la gestion d'un joueur */
public class ProcessusUnJoueur extends Thread {
    private Socket clientSocket;
    private ServeurTCP monServeurTCP;
    private JoueurID joueur;

    public  ProcessusUnJoueur(Socket uneSocket,JoueurID joueura, ServeurTCP unServeur) {
        super("ServeurThread");
        clientSocket = uneSocket;
        System.out.println("[ProcessusUneManche] CLIENT : " + clientSocket);
        monServeurTCP = unServeur;
        joueur = joueura;
    }

    public void run() {
        try {

            monServeurTCP.getProtocole().execute(monServeurTCP,  clientSocket.getInputStream() , clientSocket.getOutputStream() , joueur, monServeurTCP.getListJoueurs());  // getlistJoueurs
            // on execute le protocole associé à la situation : ici nous n'avons implémenté que le cas ou l'on est avec 2 Joueur.
            // en appliquant le principe O, pour joueur avec 3/4 ou même 5 joeurs, on pourra créer une nouvelle classe qui implémentera l'interface IProtocole, et définira la méthode execute pour ce protocle.
            // ainsi, on ne modifiera pas les classes déjà implémentées pour une situation, c'est à dire la classe Protocole2Joueurs.

            System.out.println("ProcessusUneManche fait");
        } catch (IOException e) {
            System.err.println("[ProcessusUneManche] Exception : " + e );
            e.printStackTrace();
        }
    }
}
