package launchPattern;

import joueur.Joueur;
import joueur.JoueurGUI;

public class MainJoueur2 {

	public static void main(String[] args) {
		Joueur joueur2 = new Joueur("Elodie");
		JoueurGUI monJoueurGUI = new JoueurGUI(joueur2);
		monJoueurGUI.initGUI();
	}

}
