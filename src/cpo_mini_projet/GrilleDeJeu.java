/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

/**
 *
 * @author saint
 */
public class GrilleDeJeu {
    // Attributs privés
    private Cellule[][] matriceCellules;
    private int nbLignes;
    private int nbColonnes;
    private int nbBombes;

    // Constructeur : initialise une grille vide avec le nombre de lignes, de colonnes, et de bombes
    public GrilleDeJeu(int nbLignes, int nbColonnes, int nbBombes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbBombes = nbBombes;
        this.matriceCellules = new Cellule[nbLignes][nbColonnes];

        // Initialiser chaque cellule
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule(false, false, 0);
            }
        }

        // Placer les bombes aléatoirement dans la grille
        placerBombesAleatoirement();
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

    // Méthode pour placer les bombes aléatoirement
    private void placerBombesAleatoirement() {
        int bombesPlacees = 0;
        while (bombesPlacees < nbBombes) {
            int ligne = (int) (Math.random() * nbLignes);
            int colonne = (int) (Math.random() * nbColonnes);

            // Vérifier si une bombe est déjà présente
            if (!matriceCellules[ligne][colonne].getPresenceBombe()) {
                matriceCellules[ligne][colonne].placerBombe();
                bombesPlacees++;
            }
        }
    }

    // Méthode pour calculer le nombre de bombes adjacentes pour chaque cellule
    public void calculerBombesAdjacentes() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (!matriceCellules[i][j].getPresenceBombe()) {
                    int bombesAdjacentes = compterBombesAdjacentes(i, j);
                    matriceCellules[i][j] = new Cellule(false, false, bombesAdjacentes);
                }
            }
        }
    }

    // Méthode pour compter les bombes adjacentes à une cellule donnée
    private int compterBombesAdjacentes(int ligne, int colonne) {
        int bombes = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nouvelleLigne = ligne + i;
                int nouvelleColonne = colonne + j;

                if (nouvelleLigne >= 0 && nouvelleLigne < nbLignes &&
                    nouvelleColonne >= 0 && nouvelleColonne < nbColonnes &&
                    matriceCellules[nouvelleLigne][nouvelleColonne].getPresenceBombe()) {
                    bombes++;
                }
            }
        }
        return bombes;
    }

    // Méthode pour afficher la grille (utilisée pour les tests)
    public void afficherGrille() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (matriceCellules[i][j].getPresenceBombe()) {
                    System.out.print("B ");
                } else {
                    System.out.print(matriceCellules[i][j].getNbBombesAdjacentes() + " ");
                }
            }
            System.out.println();
        }
    }
}