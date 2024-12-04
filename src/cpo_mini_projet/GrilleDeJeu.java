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
    // Classe GrilleDeJeu (ajout ou modification de méthode)

public void calculerBombesAdjacentes() {
    for (int i = 0; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            // Si la cellule n'est pas une bombe
            if (!matriceCellules[i][j].getPresenceBombe()) {
                int bombesAdjacentes = compterBombesAdjacentes(i, j);
                // Mise à jour de l'attribut nbBombesAdjacentes
                matriceCellules[i][j] = new Cellule(false, false, bombesAdjacentes);
            }
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
                continue; // Ignorer la cellule actuelle
            }

            int nouvelleLigne = ligne + i;
            int nouvelleColonne = colonne + j;

            // Vérifier si les indices sont dans les limites de la grille
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
    
    public void revelerCellule(int ligne, int colonne) {
    // Vérification des indices valides
    if (ligne < 0 || ligne >= nbLignes || colonne < 0 || colonne >= nbColonnes) {
        return ; // Sortir si hors des limites de la grille
    }

    // Récupérer la cellule
    Cellule cellule = matriceCellules[ligne][colonne];

    // Vérifier si la cellule est déjà dévoilée
    if (cellule.getDevoilee()) {
        return; // Ne rien faire si la cellule est déjà révélée
    }

    // Révéler la cellule
    cellule.revelerCellule();

    // Vérifier si la cellule contient une bombe
    if (cellule.getPresenceBombe()) {
        System.out.println("Boom! Vous avez révélé une bombe. Fin de la partie !");
        return; // La partie se termine
    }

    // Si la cellule ne contient pas de bombe et a 0 bombes adjacentes, propagation
    if (cellule.getNbBombesAdjacentes() == 0) {
        // Appeler récursivement revelerCellule pour les cellules adjacentes
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) { // Exclure la cellule actuelle
                    revelerCellule(ligne + i, colonne + j);
                }
            }
        }
    }
}
    
    public boolean getPresenceBombe(int i, int j) {
    // Vérifier si les indices sont valides
    if (i < 0 || i >= nbLignes || j < 0 || j >= nbColonnes) {
        throw new IllegalArgumentException("Indices hors limites !");
    }
    return matriceCellules[i][j].getPresenceBombe();
}

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


