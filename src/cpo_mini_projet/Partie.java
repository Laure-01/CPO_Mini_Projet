/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class Partie {
    private GrilleDeJeu grilleDeJeu;
    private int nbVies;
    private boolean jeuEnCours;

    // Constructeur : Initialise une partie avec une grille de jeu, un nombre de vies, et l'état du jeu
    public Partie(int nbLignes, int nbColonnes, int nbBombes, int nbVies) {
        GrilleDeJeu matp = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.nbVies = nbVies;
        this.jeuEnCours = true;
        grilleDeJeu.calculerBombesAdjacentes();
    }
    
    
    // Méthode pour vérifier si le joueur a gagné la partie
    public boolean verifierVictoire() {
        // Si toutes les cellules sûres sont révélées, le joueur a gagné
        return grilleDeJeu.toutesCellulesRevelees();
    }
    
    
    // Méthode pour gérer un tour de jeu
    public void tourDeJeu(int ligne, int colonne) {
        // Vérifier si la cellule contient une bombe
        if (grilleDeJeu.getPresenceBombe(ligne, colonne)) {
            nbVies--; // On perd une vie
            System.out.println("Boom! Vous avez perdu une vie. Vies restantes : " + nbVies);
            }
        // Perte d'une vie
            System.out.println("Oh non ! Vous avez révélé une bombe. Il vous reste " + nbVies + " vies.");
            if (nbVies <= 0) {
                System.out.println("Vous avez perdu toutes vos vies. Fin de la partie !");
        } else {
            grilleDeJeu.revelerCellule(ligne, colonne);
            System.out.println("Vous avez révélé une cellule sûre.");
            }
            // Vérification de la victoire
        if (grilleDeJeu.toutesCellulesRevelees()) {
            System.out.println("Félicitations ! Vous avez gagné la partie !");
        }
    }
    // Vérifier si le joueur a perdu
    public boolean verifierDefaite() {
        return nbVies <= 0;
    }

    
    // Méthode principale qui démarre la partie
   public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);
        while (!grilleDeJeu.toutesCellulesRevelees() && !verifierDefaite()) {
            System.out.println(grilleDeJeu);
            System.out.print("Entrez la ligne : ");
            int ligne = scanner.nextInt();
            System.out.print("Entrez la colonne : ");
            int colonne = scanner.nextInt();

            // Jouer un tour
            tourDeJeu(ligne, colonne);
        }

        // Afficher la grille finale
        System.out.println("Grille finale :");
        System.out.println(grilleDeJeu);

        // Résultat final
        if (grilleDeJeu.toutesCellulesRevelees()) {
            System.out.println("Vous avez gagné !");
        } else if (verifierDefaite()) {
            System.out.println("Vous avez perdu !");
        }
        // Fermer le scanner à la fin du jeu
        scanner.close();
    
    }
}

