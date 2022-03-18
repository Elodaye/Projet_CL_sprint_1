package joueur;

import java.beans.PropertyChangeSupport;

public class Joueur implements IJoueur {

    private int port = 6666;
    private int score = 9;
    private int scoreAdv = 9;
    private int nbAdv = 100;
    private ClientTCP monClientTCP;
    private String name;
    private PropertyChangeSupport pcSupport;

    /**
     * Constructeur de la classe joueur
     * @param name : nom du joueur
     */
    public Joueur(String name) {
        monClientTCP = new ClientTCP("localhost", port);
        monClientTCP.connecterAuServeur();
        pcSupport = new PropertyChangeSupport(this);
        this.name = name;
    }

    /**
     * Methode permettant d'envoyer un message au serveur (par appel au methode du clientTCP)
     * @param reponse
     */
    public void envoiReponse(String reponse) {
        System.out.println("envoieReponse : ok");
        String msgServeur = monClientTCP.transmettreChaine(reponse);

        int dernierScore = getScore();
        score = Integer.parseInt(String.valueOf(msgServeur.charAt(20))) ;
        System.out.println (score);

        int dernierScoreAdv = getScore();
        scoreAdv = Integer.parseInt(String.valueOf(msgServeur.charAt(msgServeur.length() - 1)));

        int dernierNbAdv = getNbAdv();

        if (String.valueOf(msgServeur.charAt(52)).equals(" ")) {   // on lit le nombre joué par l'adversaire (à 1,2 ou 3 chiffres)
            if (String.valueOf(msgServeur.charAt(51)).equals(" ")) {
                nbAdv = Integer.parseInt(String.valueOf(msgServeur.charAt(50)));
            } else {
                nbAdv = 10 * Integer.parseInt(String.valueOf(msgServeur.charAt(50))) + Integer.parseInt(String.valueOf(msgServeur.charAt(51)));
            }
        } else {
            nbAdv = 100;
        }

        pcSupport.firePropertyChange("somme", dernierScore, getScore());  // on notifie l'interface graphique associée au joueur du changement de valeurs à afficher
        pcSupport.firePropertyChange("sommeAdv", dernierScoreAdv, getScoreAdv());
        pcSupport.firePropertyChange("nbAdv", dernierNbAdv, getNbAdv());

    }


    public String getName() {
        return name;
    }

    public PropertyChangeSupport getPcSupport() {
        return pcSupport;
    }

    public int getScore() {
        return score;
    }

    public int getScoreAdv() {
        return scoreAdv;
    }

    public int getNbAdv() {
        return nbAdv;
    }

    @Override   // redéfinition de la méthode String de Joueur, pour afficher le score
    public String toString() {
        return "Score : " + score;
    }

}
