/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

/**
 *
 * @author lenovo
 */
public class Cellule {
    private boolean presenceBombe;
    private boolean devoilee;
    private int nbBombesAdjacentes;
    private boolean drapeauPose; // Attribut pour gérer l'état du drapeau

    public Cellule(boolean presenceBombe, boolean devoilee, int nbBombesAdjacentes) {
        this.presenceBombe = presenceBombe;
        this.devoilee = devoilee;
        this.nbBombesAdjacentes = nbBombesAdjacentes;
        this.drapeauPose = false; // Initialiser le drapeau à false
    }

    // Méthodes pour gérer le drapeau
    public boolean isDrapeauPose() {
        return drapeauPose;
    }

    public void poserDrapeau() {
        this.drapeauPose = true;
    }

    public void enleverDrapeau() {
        this.drapeauPose = false;
    }

    // Méthode pour savoir si la cellule est dévoilée
    public boolean getDevoilee() {
        return devoilee;
    }

    // Méthode pour obtenir la présence de la bombe
    public boolean getPresenceBombe() {
        return presenceBombe;
    }

    // Méthode pour obtenir le nombre de bombes adjacentes
    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    // Méthode pour poser la bombe sur la cellule
    public void placerBombe() {
        presenceBombe = true;
    }

    // Méthode pour dévoiler la cellule
    public void revelerCellule() {
        if (!drapeauPose) { // On ne peut pas révéler si un drapeau est posé
            devoilee = true;
        }
    }

    // Méthode pour modifier le nombre de bombes adjacentes
    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    // Méthode toString() pour afficher l'état de la cellule sous forme de texte
    @Override
    public String toString() {
        if (drapeauPose) {
            return "F"; // Si un drapeau est posé, on affiche "F" (pour Flag)
        } else if (!devoilee) {
            return "?"; // Non dévoilée
        } else if (presenceBombe) {
            return "B"; // Bombe dévoilée
        } else if (nbBombesAdjacentes > 0) {
            return String.valueOf(nbBombesAdjacentes); // Nombre de bombes adjacentes
        } else {
            return " "; // Aucune bombe ni adjacente
        }
    }
}


