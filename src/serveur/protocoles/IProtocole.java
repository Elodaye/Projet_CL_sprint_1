package serveur.protocoles;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import serveur.JoueurID;

/**
 * Une API qui représente un protocole
 */
public interface IProtocole {

	public void execute(IContext c, InputStream anInputStream, OutputStream anOutputStream, JoueurID joueur,  ArrayList<JoueurID> listJ);
	// méthode implémentée dans chacun des protocoles
}
