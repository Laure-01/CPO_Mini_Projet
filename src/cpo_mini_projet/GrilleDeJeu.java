/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

import javax.swing.JOptionPane;

public class GrilleDeJeu {
    private Cellule[][] matriceCellules;
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;
    private int nbVies;

    // Constructeur : initialise une grille vide avec le nombre de lignes, de colonnes, et de bombes
    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        this.matriceCellules = new Cellule[nbLignes][nbColonnes];

        // Initialiser chaque cellule
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }

        // Placer les bombes aléatoirement dans la grille
        placerBombesAleatoirement();

        // Calculer les bombes adjacentes
        calculerBombesAdjacentes();
    }

    // Getters pour les attributs
    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    public Cellule[][] getMatriceCellules() {
        return matriceCellules;
    }
    
    public boolean getPresenceBombe(int ligne, int colonne) {
        
     
// Vérification des indices valides
        
      
if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
            return false; // Si la position est hors des limites, retourner false
        }
        return matriceCellules[ligne][colonne].getPresenceBombe();
        }

        

    // Méthode pour placer les bombes aléatoirement
    private void placerBombesAleatoirement() {
        int bombesPlacees = 0;
        while (bombesPlacees < nbBombes) {
            int ligne = (int) (Math.random() * nbLignes);
            int colonne = (int) (Math.random() * nbColonnes);

            if (!matriceCellules[ligne][colonne].getPresenceBombe()) {
                matriceCellules[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    // Méthode pour calculer le nombre de bombes adjacentes pour chaque cellule
    public void calculerBombesAdjacentes() {
        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int colonne = 0; colonne < nbColonnes; colonne++) {
                Cellule cellule = matriceCellules[ligne][colonne];
                if (!cellule.getPresenceBombe()) {
                    int nbBombesAdj = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (i == 0 && j == 0) continue;
                            int nouvelleLigne = ligne + i;
                            int nouvelleColonne = colonne + j;
                            if (nouvelleLigne >= 0 && nouvelleLigne < nbLignes && nouvelleColonne >= 0 && nouvelleColonne < nbColonnes) {
                                if (matriceCellules[nouvelleLigne][nouvelleColonne].getPresenceBombe()) {
                                    nbBombesAdj++;
                                }
                            }
                        }
                    }
                    cellule.setNbBombesAdjacentes(nbBombesAdj);
                }
            }
        }
    }

    // Méthode pour révéler une cellule à une position donnée avec propagation
    public void revelerCellule(int ligne, int colonne) {
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
            return;
        }

        Cellule cellule = matriceCellules[ligne][colonne];

        if (cellule.getDevoilee() || cellule.isDrapeauPose()) {
            return;
        }

        cellule.revelerCellule();

        if (cellule.getPresenceBombe()) {
            System.out.println("Boom! Vous avez révélé une bombe. Fin de la partie !");
            afficherInterfaceDefaite();
            return;
        }

        if (cellule.getNbBombesAdjacentes() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        revelerCellule(ligne + i, colonne + j);
                    }
                }
            }
        }
    }

    // Nouvelle méthode pour propager la révélation
    public void propagerRevelation(int ligne, int colonne) {
        if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
            return;
        }

        Cellule cellule = matriceCellules[ligne][colonne];

        if (cellule.getDevoilee() || cellule.getPresenceBombe() || cellule.isDrapeauPose()) {
            return;
        }

        cellule.revelerCellule();

        if (cellule.getNbBombesAdjacentes() > 0) {
            return;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    propagerRevelation(ligne + i, colonne + j);
                }
            }
        }
    }

    private void afficherInterfaceDefaite() {
        java.awt.EventQueue.invokeLater(() -> new InterfaceDefaite().setVisible(true));
    }

    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe() && !matriceCellules[i][j].getDevoilee()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder grilleAffichage = new StringBuilder();

        grilleAffichage.append("   ");
        for (int col = 0; col < nbColonnes; col++) {
            grilleAffichage.append(col).append(" ");
        }
        grilleAffichage.append("\n");

        grilleAffichage.append("  ").append("-".repeat(nbColonnes * 2)).append("\n");

        for (int i = 0; i < nbLignes; i++) {
            grilleAffichage.append(i).append(" |");
            for (int j = 0; j < nbColonnes; j++) {
                grilleAffichage.append(matriceCellules[i][j].toString()).append(" ");
            }
            grilleAffichage.append("\n");
        }

        grilleAffichage.append("  ").append("-".repeat(nbColonnes * 2)).append("\n");

        return grilleAffichage.toString();
    }
}
