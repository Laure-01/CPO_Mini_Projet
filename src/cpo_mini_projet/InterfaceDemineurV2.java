/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cpo_mini_projet;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author saint
 */
public class InterfaceDemineurV2 extends javax.swing.JFrame {
private  GrilleDeJeu grilleDeJeu;
private int nbVies;
    private  int nbLignes =10;
    private  int nbColonnes =10;
    private  int nbBombes =10;
    private boolean modeDrapeau = false; // Variable pour suivre l'état du mode drapeau
    /**
     * Creates new form InterfaceDemineurV2
     */
   public InterfaceDemineurV2(int difficulte) {
        initComponents();
        ChoisirDifficulte(difficulte); // Appliquer la difficulté choisie
        initialiserJeu(); // Initialiser le jeu avec les paramètres de la difficulté
    }
   

    InterfaceDemineurV2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   // Méthode pour initialiser le jeu
    private void initialiserJeu() {
        // Étape 1 : Réinitialiser le panneau de la grille
        PanneauGrille.removeAll(); // Retirer tous les composants (les boutons existants)
        
        // Étape 2 : Créer une nouvelle instance de la grille en fonction des nouvelles dimensions
        grilleDeJeu = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        grilleDeJeu.calculerBombesAdjacentes();
        
        // Étape 3 : Reconfigurer le Layout pour correspondre à la nouvelle taille de grille
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes)); // On redéfinit le layout avec les nouvelles dimensions
        
        // Étape 4 : Ajouter de nouveaux boutons (CelluleGraphique) pour chaque cellule de la grille
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Cellule cellule = grilleDeJeu.getMatriceCellules()[i][j]; // Récupération de la cellule logique
                CelluleGraphique celluleGraphique = new CelluleGraphique(i, j, cellule); // Création du bouton graphique

                // Ajout d'un ActionListener pour gérer les clics
                celluleGraphique.addActionListener(evt -> {
                    if (modeDrapeau) {
                        // Si le mode drapeau est activé, poser ou enlever un drapeau sur la cellule
                        if (cellule.isDrapeauPose()) {
                            cellule.enleverDrapeau();
                        } else {
                            cellule.poserDrapeau();
                        }
                        celluleGraphique.mettreAJourAffichage(); // Mettre à jour l'affichage de la cellule
                    } else {
                        // Si le mode drapeau n'est pas activé, révéler la cellule
                        grilleDeJeu.revelerCellule(celluleGraphique.getLigne(), celluleGraphique.getColonne());
                        celluleGraphique.mettreAJourAffichage();
                    }
                });

                PanneauGrille.add(celluleGraphique); // Ajout du bouton graphique au panneau
            }
        }

        // Étape 5 : Revalider et redessiner le panneau
        PanneauGrille.revalidate(); // Assure-toi que le layout est correctement recalculé
        PanneauGrille.repaint(); // Redessiner le panneau
    }

    
// Méthode pour mettre à jour l'affichage après un clic
    private void mettreAJourAffichage() {
        Cellule[][] cellules = grilleDeJeu.getMatriceCellules();
        
        // Parcourir chaque cellule de la grille et mettre à jour son affichage
        for (int i = 0; i < cellules.length; i++) {
            for (int j = 0; j < cellules[i].length; j++) {
                Cellule cellule = cellules[i][j];
                // Récupérer le bouton correspondant
                CelluleGraphique bouton = (CelluleGraphique) PanneauGrille.getComponent(i * nbColonnes + j);

                if (cellule.isDrapeauPose()) {
                    bouton.setText("F"); // Afficher un "F" si un drapeau est posé
                } else if (cellule.getDevoilee()) {
                    if (cellule.getPresenceBombe()) {
                        bouton.setText("B"); // Affiche une bombe
                    } else {
                        int nbBombesAdjacentes = cellule.getNbBombesAdjacentes();
                        bouton.setText(nbBombesAdjacentes > 0 ? String.valueOf(nbBombesAdjacentes) : "");
                    }
                    bouton.setEnabled(false); // Désactiver le bouton après la révélation
                }
            }
        }
    }


    
// Méthode qui définit les paramètres de la grille en fonction de la difficulté
    public void ChoisirDifficulte(int difficulte) {
        switch (difficulte) {
            case 1: // Facile
                nbLignes = 8;
                nbColonnes = 8;
                nbBombes = 10;
                nbVies = 2;
                break;
            case 2: // Moyen
                nbLignes = 12;
                nbColonnes = 12;
                nbBombes = 20;
                nbVies = 1;
                break;
            case 3: // Difficile
                nbLignes = 16;
                nbColonnes = 16;
                nbBombes = 40;
                nbVies=1;
                break;
            
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauGrille = new javax.swing.JPanel();
        Drapeau = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauGrille.setBackground(new java.awt.Color(255, 204, 255));
        PanneauGrille.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        PanneauGrille.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        Drapeau.setText("\"Mode Drapeau: Désactivé\"");
        getContentPane().add(Drapeau, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Drapeau.setText("Mode Drapeau: Désactivé");
        //Drapeau.addActionListener(evt -> {
            //modeDrapeau = !modeDrapeau;  // Bascule entre mode drapeau et normal
         //   if (modeDrapeau) {
           //     Drapeau.setText("Mode Drapeau: Activé");
        //    } else {
          //      Drapeau.setText("Mode Drapeau: Désactivé");
           // }
        //});
    /**
     * @param args the command line arguments
     */

               
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceDemineurV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            // Remplacer ici 1 par le niveau de difficulté choisi
            new InterfaceDemineurV2(1).setVisible(true); 
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Drapeau;
    private javax.swing.JPanel PanneauGrille;
    // End of variables declaration//GEN-END:variables
}
