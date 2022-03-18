package serveur.protocoles;

import serveur.JoueurID;
import java.io.*;
import java.util.ArrayList;

/**
 * Protocole de gestion du jeu pour 2 joueurs. Pour le moment toutes les règles sont implémentées mais il n'y a que deux joueurs de pris en compte.
 */
public class Protocole2joueurs implements IProtocole {
    @Override
    public void execute(IContext c, InputStream unInput, OutputStream unOutput, JoueurID joueur, ArrayList <serveur.JoueurID> listJ) {
        ArrayList<Integer> reponses = new ArrayList<Integer>();
        BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
        PrintStream os = new PrintStream(unOutput);

        try {
            System.out.println("Entree");
            String inputLine;
            int tour = 0;

            while ((inputLine = is.readLine()) != null) {
                reponses.add(Integer.parseInt(inputLine));
                tour += 1;
                System.out.println(" Reponse Recue du joueur " + joueur.getNom() + " : "  + reponses.get(tour-1));
                joueur.setNb (reponses.get(tour-1));
                joueur.setReady (true);

                if (listJ.get(0).getReady() && listJ.get(1).getReady() ) { // si on a bien reçu une réponse des 2 joueurs

                    int moy = (int) (0.4 * (listJ.get(0).getNb() + listJ.get(1).getNb()));

                    if (Math.abs(moy - listJ.get(0).getNb()) == Math.abs(moy - listJ.get(1).getNb())) {  // on applique les règles du jeu
                        listJ.get(0).setEtat("perdant");
                        listJ.get(1).setEtat("perdant");
                        listJ.get(1).setScore (listJ.get(1).getScore()-1);  // les joueurs on joué le même nombre
                        listJ.get(0).setScore (listJ.get(0).getScore()-1);
                    }
                    else if (listJ.get(0).getNb() == 100 && listJ.get(1).getNb() == 0) {
                        listJ.get(0).setEtat("gagnant");
                        listJ.get(1).setEtat("perdant");
                        listJ.get(1).setScore (listJ.get(1).getScore()-1);  // si on joueur a joué 100 et l'autre 0, celui qui a joué 100 gagne
                    }
                    else if (listJ.get(0).getNb() == 0 && listJ.get(1).getNb() == 100) {
                        listJ.get(1).setEtat("gagnant");
                        listJ.get(0).setEtat("perdant");
                        listJ.get(0).setScore (listJ.get(0).getScore()-1);
                    }
                    else if (Math.abs(moy - listJ.get(0).getNb()) < Math.abs(moy - listJ.get(1).getNb())) {
                        listJ.get(0).setEtat("gagnant");  // le joueur le plus proche de la valeur obtenue gagne
                        listJ.get(1).setEtat("perdant");
                        listJ.get(1).setScore (listJ.get(1).getScore()-1);
                        if (Math.abs(moy - listJ.get(0).getNb()) == 0) {
                            listJ.get(1).setScore (listJ.get(1).getScore()-1);  // si on joueur a joué la valeur pile obtenue après passage à la moyenne, il fait perdre 2 points à l'adversaire
                        }
                    }
                    else {
                        listJ.get(1).setEtat("gagnant");
                        listJ.get(0).setEtat("perdant");
                        listJ.get(0).setScore (listJ.get(0).getScore()-1);
                        if (Math.abs(moy - listJ.get(1).getNb()) == 0) {
                            listJ.get(0).setScore (listJ.get(0).getScore()-1); // impossible à 2 joueurx : perte de 2 points pour les perdants si un joueur avait joué exactement le bon nombre
                        }
                    }
                    System.out.println("La moyenne est de : " + ". " + listJ.get(0).getNom() + " : " + listJ.get(0).getEtat() + ",  " + listJ.get(1).getNom() + " : "+ listJ.get(1).getEtat() + ",  " + listJ.get(0).getNb());

                    listJ.get(0).setReady(false);  // fin du tour, on réattend la réponse des 2 joueurs
                    listJ.get(1).setReady(false);

                    listJ.get(0).result(moy, listJ.get(1).getNb(),  listJ.get(1).getScore());  // on appelle la méthode résult de JoueurID, qui va envoyer un flux de données à chaque joueur
                    listJ.get(1).result(moy, listJ.get(0).getNb(),  listJ.get(0).getScore() );  // le flux contient les nouveaux scores et le nombre joué par l'adversaire

                }
            }
            os.println("Le serveur a pris en compte votre reponse : " + reponses);
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
