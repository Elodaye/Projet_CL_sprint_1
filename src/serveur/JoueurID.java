package serveur;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class JoueurID {

    private Socket clientSocket;
    private int score ;
    private int nb ;
    private String nom;
    private String etat;
    private boolean ready;

    /**
     * Constructeur de class
     * @param socket : Socket du joueur
     * @param nom : nom du joueur
     */
    public JoueurID (Socket socket, String nom){
        clientSocket = socket;
        score = 9;
        nb = 0;
        this.nom = nom;
        etat = "neutre";
        ready = false;
    }

    public void setNb(int nbe){
        nb = nbe;
    }

    public int getNb(){
       return nb;
    }

    /**
     * Méthode permettant l'affichage des informations de la manche
     */
    public void result (int moy, int nb_adv, int score_adv)
    // on appelle la méthode résult de JoueurID, qui va envoyer un flux de données à chaque joueur
    // le flux contient les nouveaux scores et le nombre joué par l'adversaire

    {
        try {
            OutputStream unOutput = clientSocket.getOutputStream();
            PrintStream os = new PrintStream(unOutput);
            os.println("Vous êtes " + etat + " ! " +  String.valueOf(score) + " Votre adversaire avait joué " + String.valueOf(nb_adv) + "  la moyenne était " + String.valueOf(moy) + " L'adversaire a un score de " + String.valueOf(score_adv) );
        }

        catch (IOException e) {
            System.err.println("Quelque chose ne va pas : " + e );
            e.printStackTrace();
        }

    }

    public String getNom (){
        return nom;
    }


    public void setReady(boolean rea){
        ready = rea;
    }

    public boolean getReady(){
        return ready;
    }

    public void setScore(int sco){
        score = sco;
    }

    public int getScore(){
        return score;
    }

    public void setEtat(String eta){
        etat = eta;
    }

    public String getEtat(){
        return etat;
    }
}
