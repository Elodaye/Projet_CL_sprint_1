package joueur;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JoueurGUI extends JFrame implements PropertyChangeListener {  // interface graphique pour l'affichage du
    // score du joueur et de l'adversaire, et du dernier nombre joué par l'adversaire

    private int reponse;
    private int score;
    private int scoreAdv ;
    private int nbAdv ;
    private Joueur joueur;

    private JTextField jTextFieldScore;
    private JTextField jTextFieldScoreAdv;
    private JTextField jTextFieldNbAdv;
    private JTextField jTextFieldEndGame;
    private JTextField jTextFieldReponse;

    /**
     * Constructeur de la classe JoueuerGUI
     * @param unJoueur : Joueur associé à l'interface graphique
     */
    public JoueurGUI(Joueur unJoueur) {
        super(unJoueur.getName());
        joueur = unJoueur;

        // On vient ensuite "écouter" le joueur (c'est la classe JoueurGUI qui va
        // recevoir les notifications)
        joueur.getPcSupport().addPropertyChangeListener(this);

        score = joueur.getScore();
        scoreAdv = joueur.getScoreAdv();
        nbAdv = joueur.getNbAdv();
    }

    /**
     * @return Jlabel score
     */
    private JLabel createJLabelScore() {
        JLabel jLabelSommenpoche = new JLabel("Votre Score");
        jLabelSommenpoche.setPreferredSize(new java.awt.Dimension(150, 40));
        jLabelSommenpoche.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelSommenpoche;
    }

    /**
     * @return Jlabel score adverse
     */
    private JLabel createJLabelScoreAdv() {
        JLabel jLabelSommeAdv = new JLabel("Score de votre adversaire");
        jLabelSommeAdv.setPreferredSize(new java.awt.Dimension(150, 40));
        jLabelSommeAdv.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelSommeAdv;
    }

    /**
     * @return Jlabel nombre choisit par l'adversaire
     */
    private JLabel createJLabelNbAdv() {
        JLabel jLabelNbAdv = new JLabel("Nombre votre adversaire");
        jLabelNbAdv.setPreferredSize(new java.awt.Dimension(150, 40));
        jLabelNbAdv.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelNbAdv;
    }

    /**
     * @return Jlabel nombre choisit
     */
    private JLabel createJLabelReponse() {
        JLabel jLabelSommetraitee = new JLabel("Choix d'un entier entre 0 et 100");
        jLabelSommetraitee.setPreferredSize(new java.awt.Dimension(150, 40));
        jLabelSommetraitee.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelSommetraitee;
    }

    /**
     * @return JTextField score
     */
    public JTextField createJTextFieldScore() {
        if (jTextFieldScore == null) {
            jTextFieldScore = new JTextField();
            jTextFieldScore.setText(Integer.toString(this.getScore()));
            jTextFieldScore.setSize(new java.awt.Dimension(120, 37));
            jTextFieldScore.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldScore.setEditable(false);
        }
        return jTextFieldScore;
    }

    /**
     * Rencoit le text gagné ou perdu
     * @return JTextField endGame
     */
    public JTextField createJTextFieldEndGame(String s) {
        if (jTextFieldEndGame == null) {
            jTextFieldEndGame = new JTextField();
            if (this.getScore()==0) {
                jTextFieldEndGame.setText(s);
            } else {
                jTextFieldEndGame.setText(s);
            }
            jTextFieldEndGame.setPreferredSize( new Dimension( 200, 50 ) );
//            jTextFieldEndGame.setSize(new java.awt.Dimension(120, 37));
            jTextFieldScore.setFont(new java.awt.Font("Antique Olive", 0, 30));
            jTextFieldEndGame.setHorizontalAlignment(SwingConstants.CENTER);
            jTextFieldEndGame.setEditable(false);
        }
        return jTextFieldEndGame;
    }

    /**
     * @return JTextField nombre choisit par l'adversaire
     */
    public JTextField createJTextFieldNbAdv() {
        if (jTextFieldNbAdv == null) {
            jTextFieldNbAdv = new JTextField();
            jTextFieldNbAdv.setText(Integer.toString(this.getNbAdv()));
            jTextFieldNbAdv.setSize(new java.awt.Dimension(120, 37));
            jTextFieldNbAdv.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldNbAdv.setEditable(false);
        }
        return jTextFieldNbAdv;
    }

    /**
     * @return JTextField score adverse
     */
    public JTextField createJTextFieldScoreAdv() {
        if (jTextFieldScoreAdv == null) {
            jTextFieldScoreAdv = new JTextField();
            jTextFieldScoreAdv.setText(Integer.toString(this.getScoreAdv()));
            jTextFieldScoreAdv.setSize(new java.awt.Dimension(120, 37));
            jTextFieldScoreAdv.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldScoreAdv.setEditable(false);
        }
        return jTextFieldScoreAdv;
    }

    /**
     * @return JTextField reponse choisit
     */
    private JTextField createJTextFieldReponse() {
        if (jTextFieldReponse == null) {
            jTextFieldReponse = new JTextField();
            jTextFieldReponse.setSize(new java.awt.Dimension(140, 37));
            jTextFieldReponse.setBackground(new java.awt.Color(181, 217, 38));
            jTextFieldReponse.setForeground(new java.awt.Color(0, 0, 128));
            jTextFieldReponse.setFont(new java.awt.Font("Courier New", 1, 18));
            jTextFieldReponse.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
        }
        return jTextFieldReponse;
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

    /**
     * Crétion de la fenetre de fin de partie
     * @param s : text a afficher dans la fenetre
     */
    public void endGame(String s) {
        System.out.println(joueur);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            getContentPane().removeAll();
//            getContentPane().setForeground(new java.awt.Color(255, 30, 30));
            this.setLocation(150, 350);
            this.setVisible(true);
//            this.setFont(new java.awt.Font("Antique Olive", 0, 40));
//            getContentPane().setBackground(new java.awt.Color(255, 64, 64));
//            getContentPane().setLayout(new BorderLayout(3, 2));


            JPanel jPanel = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanel, BorderLayout.CENTER);
            jPanel.setOpaque(true);
//            jPanelouest.setFont(new java.awt.Font("Tahoma", 1, 40));
//            jPanel.setSize(250, 218);
            jPanel.add(createJTextFieldEndGame(s));
            jPanel.setBorder(new LineBorder(Color.WHITE, 4, false));


            pack();
            this.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Initialisation de la GUI pour l'interface client */
    public void initGUI() {
        System.out.println(joueur);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            getContentPane().setForeground(new java.awt.Color(255, 0, 128));
            this.setLocation(25, 350);
            this.setVisible(true);
            this.setFont(new java.awt.Font("Antique Olive", 0, 10));
            getContentPane().setBackground(new java.awt.Color(255, 128, 64));
            getContentPane().setLayout(new BorderLayout(3, 2));

            // Panel pour les boutons
            JPanel jPanelsud = new JPanel();
            getContentPane().add(jPanelsud, BorderLayout.SOUTH);
            jPanelsud.setPreferredSize(new java.awt.Dimension(392, 36));
            jPanelsud.setBackground(new java.awt.Color(255, 128, 128));
            // Valider
            JButton valider = new JButton("Valider");
            jPanelsud.add(valider);
            valider.setSize(200, 30);
            valider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    boolean connexionOk = true;
//                    TODO : le boolean est juste pour le test, il faut le modifier
                    if (connexionOk == false) {
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(getContentPane(),
                                "Pas de serveur ou nombre d'actions maximal atteint!\n Fermez le client.",
                                "Erreur transaction", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            reponse = Integer.parseInt(jTextFieldReponse.getText());
                            if (reponse>100 | reponse<0) {
                                // On fait planter le try
                                Integer.parseInt("10.2");
                            }
                            System.out.println("Vous avez choisi : " + reponse);
                            joueur.envoiReponse(jTextFieldReponse.getText());
                        } catch (Exception e) {
                            System.out.println("Veuillez choisir une réponse valide");
                        }
                    }
                }
            });

            JButton quitter = new JButton("Quitter");
            jPanelsud.add(quitter);
            quitter.setSize(200, 30);
            quitter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.exit(0);
                }
            });

            // Panel pour Score (ouest)
            JPanel jPanelouest = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelouest, BorderLayout.WEST);
            jPanelouest.setOpaque(true);
            jPanelouest.setFont(new java.awt.Font("Tahoma", 1, 13));
            jPanelouest.setSize(150, 118);
            jPanelouest.add(createJLabelScore());
            jPanelouest.add(createJTextFieldScore());
            jPanelouest.setBorder(new LineBorder(Color.WHITE, 4, false));

            // Panel pour Score adversaire (est)
            JPanel jPanelest = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelest, BorderLayout.EAST);
            jPanelest.setOpaque(true);
            jPanelest.setFont(new java.awt.Font("Tahoma", 1, 13));
            jPanelest.setSize(150, 118);

            jPanelest.add(createJLabelScoreAdv());
            jPanelest.add(createJTextFieldScoreAdv());
            jPanelest.setBorder(new LineBorder(Color.WHITE, 4, false));

            // Panel pour Reponse (centre)
            JPanel jPanelcentre = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelcentre, BorderLayout.CENTER);
            jPanelcentre.setOpaque(true);
            jPanelcentre.setForeground(new java.awt.Color(255, 128, 64));
            jPanelcentre.setBackground(new java.awt.Color(255, 128, 0));
            jPanelcentre.setBorder(new LineBorder(new java.awt.Color(255, 128, 0), 4, false));
            jPanelcentre.setPreferredSize(new java.awt.Dimension(200, 118));

            jPanelcentre.add(createJLabelReponse());
            jPanelcentre.add(createJTextFieldReponse());

            // Panel pour Reponse de l'adversaire (haut)
            JPanel jPanelnor = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelnor, BorderLayout.NORTH);
            jPanelnor.setOpaque(true);
            jPanelnor.setForeground(new java.awt.Color(255, 128, 64));
            jPanelnor.setBackground(new java.awt.Color(255, 128, 0));
            jPanelnor.setBorder(new LineBorder(new java.awt.Color(255, 128, 0), 4, false));
            jPanelnor.setPreferredSize(new java.awt.Dimension(200, 118));

            jPanelnor.add(createJLabelNbAdv());
            jPanelnor.add(createJTextFieldNbAdv());


            pack();
            this.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MAJ des differents parametres
     */
    @Override  // réécriture de la méthode propertyChange, appelée avec pcSupport.firePropertyChange dans Joueur
    public void propertyChange(PropertyChangeEvent evt) {
            jTextFieldScore.setText(Integer.toString(joueur.getScore()));  // mise à jour du score
            jTextFieldScoreAdv.setText(Integer.toString(joueur.getScoreAdv()));
            jTextFieldNbAdv.setText(Integer.toString(joueur.getNbAdv()));

        if (joueur.getScore()==0) {  // le joueur a perdu (son score est nul)
            System.out.println ("Vous avez perdu !! ");
            this.endGame("Perdu !!!");  // affichage de la fenêtre de défaite
        }

        else if (joueur.getScoreAdv() == 0 ) {
            System.out.println ("Vous avez gagné !! ");
            this.endGame("Gagné !!!");  // affichage de la fenêtre de victoire
        }
    }
}
