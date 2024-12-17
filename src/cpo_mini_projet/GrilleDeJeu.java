/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

import javax.swing.JOptionPane;

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

    // Méthode pour placer les bombes aléatoirement
    private void placerBombesAleatoirement() {
        int bombesPlacees = 0;
        while (bombesPlacees < nbBombes) {
            int ligne = (int) (Math.random() * nbLignes);
            int colonne = (int) (Math.random() * nbColonnes);

            // Vérifier si une bombe est déjà présente à cette position
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
                        if (i == 0 && j == 0) continue; // Ne pas compter la cellule elle-même
                        int nouvelleLigne = ligne + i;
                        int nouvelleColonne = colonne + j;
                        if (nouvelleLigne >= 0 && nouvelleLigne < nbLignes && nouvelleColonne >= 0 && nouvelleColonne < nbColonnes) {
                            if (matriceCellules[nouvelleLigne][nouvelleColonne].getPresenceBombe()) {
                                nbBombesAdj++;
                            }
                        }
                    }
                }
                cellule.setNbBombesAdjacentes(nbBombesAdj); // Mettre à jour le nombre de bombes adjacentes
            }
        }
    }
}

    private void initialiserGrille() {
    matriceCellules = new Cellule[nbLignes][nbColonnes];
    
    for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            // Créer chaque cellule en utilisant le constructeur sans paramètres
            matriceCellules[i][j] = new Cellule();
        }
    }
}


    // Méthode privée pour compter les bombes adjacentes à une cellule
    private int compterBombesAdjacentes(int ligne, int colonne) {
        int bombes = 0;

        // Parcourir les 8 directions autour de la cellule
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Ignorer la cellule elle-même
                }

                int nouvelleLigne = ligne + i;
                int nouvelleColonne = colonne + j;

                // Vérifier si les indices sont dans les limites de la grille
                if (nouvelleLigne >= 0 && nouvelleLigne < nbLignes &&
                    nouvelleColonne >= 0 && nouvelleColonne < nbColonnes &&
                    matriceCellules[nouvelleLigne][nouvelleColonne].getPresenceBombe()) {
                    bombes++; // Ajouter 1 si une bombe est présente
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
                    System.out.print("\"/images/bombe.png\" ");
                } else {
                    System.out.print(matriceCellules[i][j].getNbBombesAdjacentes() + " ");
                }
            }
            System.out.println();
        }
    }

    // Méthode pour révéler une cellule à une position donnée
// Méthode pour révéler une cellule à une position donnée
public void revelerCellule(int ligne, int colonne) {
    // Vérifier si les indices sont valides
    if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
        return; // Sortir si hors des limites
    }

    // Récupérer la cellule actuelle
    Cellule cellule = matriceCellules[ligne][colonne];

    // Si la cellule est déjà révélée ou marquée d'un drapeau, ne rien faire
    if (cellule.getDevoilee() || cellule.isDrapeauPose()) {
        return;
    }

    // Révéler la cellule
    cellule.revelerCellule();

    // Si la cellule contient une bombe
    if (cellule.getPresenceBombe()) {
        System.out.println("Boom! Vous avez révélé une bombe. Fin de la partie !");
        afficherInterfaceDefaite();
        return;
    }

    // Si la cellule n'a **aucune bombe adjacente**, propager la révélation
    if (cellule.getNbBombesAdjacentes() == 0) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Ne pas traiter la cellule elle-même
                }
                int nouvelleLigne = ligne + i;
                int nouvelleColonne = colonne + j;

                // Récurser pour les cellules adjacentes
                revelerCellule(nouvelleLigne, nouvelleColonne);
            }
        }
    }
}



// Méthode pour afficher l'interface de défaite
private void afficherInterfaceDefaite() {
    // Créer et afficher la fenêtre de défaite
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new InterfaceDefaite().setVisible(true);
        }
    });
}

    public boolean getPresenceBombe(int i, int j) {
        // Vérifier si les indices sont valides
        if (i < 0 || i >= nbLignes || j < 0 || j >= nbColonnes) {
            throw new IllegalArgumentException("Indices hors limites !");
        }
        return matriceCellules[i][j].getPresenceBombe();
    }

    // Méthode pour vérifier si toutes les cellules sûres ont été révélées
    public boolean toutesCellulesRevelees() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                // Si la cellule ne contient pas de bombe et n'est pas dévoilée
                if (!matriceCellules[i][j].getPresenceBombe() && !matriceCellules[i][j].getDevoilee()) {
                    return false; // Il reste des cellules sûres non dévoilées
                }
            }
        }
        return true; // Toutes les cellules sûres ont été révélées
    }

    // Méthode pour afficher la grille sous forme de chaîne de caractères
    @Override
    public String toString() {
        StringBuilder grilleAffichage = new StringBuilder();

        // Ajouter une ligne d'indices pour les colonnes
        grilleAffichage.append("   ");
        for (int col = 0; col < nbColonnes; col++) {
            grilleAffichage.append(col).append(" ");
        }
        grilleAffichage.append("\n");

        // Bordure supérieure
        grilleAffichage.append("  ").append("-".repeat(nbColonnes * 2)).append("\n");

        // Parcourir chaque ligne
        for (int i = 0; i < nbLignes; i++) {
            // Ajouter un indice pour la ligne
            grilleAffichage.append(i).append(" |");

            // Parcourir chaque colonne
            for (int j = 0; j < nbColonnes; j++) {
                grilleAffichage.append(matriceCellules[i][j].toString()).append(" ");
            }

            // Fin de ligne
            grilleAffichage.append("\n");
        }

        // Bordure inférieure
        grilleAffichage.append("  ").append("-".repeat(nbColonnes * 2)).append("\n");

        return grilleAffichage.toString();
    }
} 