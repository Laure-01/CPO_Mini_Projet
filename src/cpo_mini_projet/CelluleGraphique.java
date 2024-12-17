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
                        setIcon(new ImageIcon(getClass().getResource("/image/drapeau.png"))); // Afficher l'icône du drapeau
                    }
                }
            }
        });
    }

    // Méthode pour mettre à jour l'affichage graphique en fonction de l'état de la cellule
    public void mettreAJourAffichage() {
        if (cellule.isDrapeauPose()) {
            // Si un drapeau est posé, afficher l'icône du drapeau
            setIcon(new ImageIcon(getClass().getResource("/image/drapeau.png")));
        } else if (cellule.getDevoilee()) {
            if (cellule.getPresenceBombe()) {
                // Charger l'image depuis src/image/bombe.jpg
                java.net.URL bombeURL = getClass().getResource("/image/bombe.jpg");
                if (bombeURL != null) {
                    // Créer l'ImageIcon et redimensionner l'image pour qu'elle s'ajuste à la taille du bouton
                    ImageIcon bombeIcone = new ImageIcon(bombeURL);
                    Image image = bombeIcone.getImage(); // Obtenir l'image
                    // Redimensionner l'image pour s'adapter au bouton tout en maintenant son ratio d'aspect
                    Image redimensionnee = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH); 
                    this.setIcon(new ImageIcon(redimensionnee)); // Définir l'icône redimensionnée
                } else {
                    System.err.println("Erreur : Image 'bombe.jpg' non trouvée dans 'src/image/' !");
                }
            } else if (cellule.getNbBombesAdjacentes() > 0) {
                this.setText(String.valueOf(cellule.getNbBombesAdjacentes())); // Nombre de bombes adjacentes
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

