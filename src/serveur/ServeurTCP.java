package serveur;

import serveur.protocoles.IContext;
import serveur.protocoles.IProtocole;
import serveur.protocoles.Protocole2joueurs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/** Serveur sur lequel les joueurs se connectent et jouent */
public class ServeurTCP extends Thread implements IContext {

	public int nbConnexions = 0;
	
	/** Maximum de connexions client autorisées */
	private int maxConnexions;
	
	private Socket clientSocket;
	private ArrayList<Socket> mesClients;
	private ArrayList<JoueurID> listJoueurs;

	private IProtocole protocole;
	
	private int numeroPort;


	public ServeurTCP(int unNumeroPort) {        
		numeroPort = unNumeroPort;
		maxConnexions = 2;
		protocole = new Protocole2joueurs();
		mesClients = new ArrayList<>();
		listJoueurs = new ArrayList<>();
	} 

	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket ( numeroPort );
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + numeroPort + ", " + e);
			System.exit(1);
		}

		/* On autorise maxConnexions traitements*/
		while (nbConnexions < maxConnexions) {
			try {
				System.out.println(" Attente du serveur pour la communication d'un client " );
				clientSocket = serverSocket.accept();
				nbConnexions ++;
				System.out.println("Nb automates : " + nbConnexions);
				System.out.println(clientSocket);
				mesClients.add(clientSocket);
			} catch (IOException e) {
				System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
				System.exit(1);
			}
		}

		System.out.println("Start game !!");
		System.out.println(mesClients);

		JoueurID joueur1 = new JoueurID(mesClients.get(0), "Louis");  // on crée 2 instances de JoueurID qui représentent les 2 joueurs. Chaque joueur est lié à une socket, représentant le lien avec un ClientTCP propre.
		JoueurID joueur2 = new JoueurID(mesClients.get(1),"Elodie" );

		listJoueurs.add(joueur1);
		listJoueurs.add(joueur2);

		ProcessusUnJoueur uneManche1 = new ProcessusUnJoueur(mesClients.get(0), joueur1, this ); // On lance un processus par joueur (avec un clientTCP par joueur, en lien avec ce ProcessusUnJoueur)
		uneManche1.start();

		System.out.println("Nbr joueurs : " + "premier");

		ProcessusUnJoueur uneManche2 = new ProcessusUnJoueur(mesClients.get(1), joueur2,  this ); //
		uneManche2.start();

		System.out.println("Nbr joueurs : " + "deuxieme");

		try {
			serverSocket.close();
			nbConnexions --;
		} catch (IOException e) {
			System.out.println("Could not close");
		}
	}

	public IProtocole getProtocole() {
		return protocole;
	}

	public ArrayList <serveur.JoueurID> getListJoueurs (){
		return listJoueurs ;
	}
}
