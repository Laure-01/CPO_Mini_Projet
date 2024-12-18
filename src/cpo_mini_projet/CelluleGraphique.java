/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CelluleGraphique extends JButton {
    private final int ligne;
    private final int colonne;
    private final Cellule cellule;

    public CelluleGraphique(int ligne, int colonne, Cellule cellule) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.cellule = cellule;
        this.setText("?"); // Par défaut, affichage non révélé
        this.setPreferredSize(new Dimension(50, 50)); // Taille fixe du bouton (ajustez selon l'image)
        this.setMargin(new Insets(0, 0, 0, 0)); // Enlever les marges pour que l'image soit parfaitement centrée

        // Ajout d'un MouseListener pour gérer les clics droits (pose/enlèvement de drapeau)
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // Clic droit
                    if (cellule.isDrapeauPose()) {
                        cellule.enleverDrapeau(); // Enlever le drapeau
                        setIcon(null); // Enlever l'icône du drapeau
                    } else {
                        cellule.poserDrapeau(); // Poser le drapeau
                        setIcon(new ImageIcon(getClass().getResource("/image/drapeau.jpg"))); // Afficher l'icône du drapeau
                    }
                }
            }
        });
    }

    // Méthode pour mettre à jour l'affichage graphique en fonction de l'état de la cellule
    public void mettreAJourAffichage() {
        if (cellule.isDrapeauPose()) {
            // Si un drapeau est posé, afficher l'icône du drapeau
            java.net.URL drapeauURL = getClass().getResource("/image/drapeau.jpg");
            if (drapeauURL != null) {
                ImageIcon drapeauIcone = new ImageIcon(drapeauURL);
                Image image = drapeauIcone.getImage();
                Image redimensionnee = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(redimensionnee));
            } else {
                System.err.println("Erreur : Image 'drapeau.jpg' non trouvée !");
            }
        } else if (cellule.getDevoilee()) {
            if (cellule.getPresenceBombe()) {
                // Charger l'image de la bombe
                java.net.URL bombeURL = getClass().getResource("/image/bombes.jpg");
                if (bombeURL != null) {
                    ImageIcon bombeIcone = new ImageIcon(bombeURL);
                    Image image = bombeIcone.getImage();
                    Image redimensionnee = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(redimensionnee));
                } else {
                    System.err.println("Erreur : Image 'bombes.jpg' non trouvée !");
                }
            } else if (cellule.getNbBombesAdjacentes() > 0) {
                this.setText(String.valueOf(cellule.getNbBombesAdjacentes())); // Afficher le nombre de bombes adjacentes
            } else {
                this.setText(" "); // Vide
            }
            this.setEnabled(false); // Désactiver le bouton une fois révélé
        } else {
            this.setText("?"); // Non révélé
            this.setIcon(null); // Pas d'image si non révélé
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


