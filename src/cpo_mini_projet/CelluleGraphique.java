/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;


import javax.swing.JButton;

/**
 *
 * @author lenovo
 */
public class CelluleGraphique extends JButton {
    private final int ligne;
    private final int colonne;
    private final Cellule cellule;

    public CelluleGraphique(int ligne, int colonne, Cellule cellule) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.cellule = cellule;
        this.setText("?"); // Par défaut, affichage non révélé
    }

    // Méthode pour mettre à jour l'affichage graphique en fonction de l'état de la cellule
    public void mettreAJourAffichage() {
        if (cellule.getDevoilee()) {
            if (cellule.getPresenceBombe()) {
                this.setText("B"); // Bombe
            } else if (cellule.getNbBombesAdjacentes() > 0) {
                this.setText(String.valueOf(cellule.getNbBombesAdjacentes())); // Nombre de bombes adjacentes
            } else {
                this.setText(" "); // Vide
            }
            this.setEnabled(false); // Désactiver le bouton une fois révélé
        } else {
            this.setText("?"); // Non révélé
        }
    }

    // Getters pour récupérer la position et la cellule associée
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Cellule getCellule() {
        return cellule;
    }
}