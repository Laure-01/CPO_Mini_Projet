/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cpo_mini_projet;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

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
        
        // Ajouter des boutons pour chaque cellule de la grille
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Cellule cellule = grilleDeJeu.getMatriceCellules()[i][j];
                CelluleGraphique celluleGraphique = new CelluleGraphique(i, j, cellule);

                // Gérer les clics
                celluleGraphique.addActionListener(evt -> {
                    if (modeDrapeau) {
                        // Gestion du drapeau
                        if (cellule.isDrapeauPose()) {
                            cellule.enleverDrapeau();
                        } else {
                            cellule.poserDrapeau();
                        }
                        celluleGraphique.mettreAJourAffichage();
                    } else {
                        // Révéler la cellule
                        if (cellule.getPresenceBombe()) {
                            nbVies--;
                            if (nbVies <= 0) {
                               
                                initialiserJeu(); // Réinitialiser le jeu
                            } else {
                                JOptionPane.showMessageDialog(this, "Boom! Bombe révélée, vies restantes: " + nbVies, "Attention!", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            grilleDeJeu.revelerCellule(celluleGraphique.getLigne(), celluleGraphique.getColonne());
                        }
                        mettreAJourAffichage();
                        if (grilleDeJeu.toutesCellulesRevelees()) {
                            JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
                            initialiserJeu();
                        }
                    }
                });

                PanneauGrille.add(celluleGraphique);
            }
        }

        // Revalider et redessiner
        PanneauGrille.revalidate();
        PanneauGrille.repaint();
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
                    bouton.setIcon(new ImageIcon(getClass().getResource("/image/drapeau.png")));
                } else if (cellule.getDevoilee()) {
                    if (cellule.getPresenceBombe()) {
                        bouton.setIcon(new ImageIcon(getClass().getResource("/image/bombes.jpg")));
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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauGrille.setBackground(new java.awt.Color(255, 204, 255));
        PanneauGrille.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        PanneauGrille.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 540, 410));

        Drapeau.setBackground(new java.awt.Color(255, 204, 255));
        Drapeau.setText("Drapeau");
        Drapeau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrapeauActionPerformed(evt);
            }
        });
        Drapeau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DrapeauPropertyChange(evt);
            }
        });
        getContentPane().add(Drapeau, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 120, -1));

        jButton1.setBackground(new java.awt.Color(255, 204, 255));
        jButton1.setText("revenir au menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DrapeauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrapeauActionPerformed
        // TODO add your handling code here:
        //Basculer entre les modes Drapeau
            modeDrapeau = !modeDrapeau;  
            if (modeDrapeau) {
                Drapeau.setText("Mode Drapeau: Activé");
            } else {
                Drapeau.setText("Mode Drapeau: Désactivé");
            }
        
    }//GEN-LAST:event_DrapeauActionPerformed

    private void DrapeauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DrapeauPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_DrapeauPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       InterfaceBienvenue changerDifficulte = new InterfaceBienvenue(); // Créer une instance de l'interface
    changerDifficulte.setVisible(true); // Afficher la fenêtre
    this.dispose(); // Fermer la fenêtre actuelle
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
