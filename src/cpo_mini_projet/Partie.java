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
import java.util.Scanner;

public class Partie {
    private GrilleDeJeu grilleDeJeu;
    private int nbViesInitiales;
    private int nbVies;
    private boolean jeuEnCours;

    // Constructeur : Initialise une partie avec une grille de jeu, un nombre de vies, et l'état du jeu
    public Partie(int nbLignes, int nbColonnes, int nbBombes, int nbViesInitiales) {
        // Créer la grille de jeu avec les dimensions et les bombes
        this.grilleDeJeu = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.nbViesInitiales = nbViesInitiales; // Sauvegarder le nombre initial de vies
        this.nbVies = nbViesInitiales; // Initialiser le nombre de vies avec la valeur définie
        this.jeuEnCours = true;
        grilleDeJeu.calculerBombesAdjacentes(); // Calculer les bombes adjacentes
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
            // Perdre une vie
            nbVies--; 
            System.out.println("Boom! Vous avez perdu une vie. Vies restantes : " + nbVies);
            
            // Vérifier si le joueur a perdu toutes ses vies
            if (nbVies <= 0) {
                System.out.println("Vous avez perdu toutes vos vies. Fin de la partie !");
                jeuEnCours = false; // Fin de la partie
                return; // Sortir de la méthode, fin du tour
            }
        } else {
            // Révéler la cellule si elle ne contient pas de bombe
            grilleDeJeu.revelerCellule(ligne, colonne);
            System.out.println("Vous avez révélé une cellule sûre.");
        }

        // Vérification de la victoire
        if (grilleDeJeu.toutesCellulesRevelees()) {
            System.out.println("Félicitations ! Vous avez gagné la partie !");
            jeuEnCours = false; // Fin de la partie
        }
    }

    // Vérifier si le joueur a perdu
    public boolean verifierDefaite() {
        return nbVies <= 0;
    }

    // Méthode principale qui démarre la partie
    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);
        while (jeuEnCours) {
            System.out.println(grilleDeJeu);
            System.out.print("Entrez la ligne : ");
            int ligne = scanner.nextInt();
            System.out.print("Entrez la colonne : ");
            int colonne = scanner.nextInt();

            // Jouer un tour
            tourDeJeu(ligne, colonne);

            // Vérifier la défaite
            if (verifierDefaite()) {
                System.out.println("Vous avez perdu !");
                break; // Fin de la partie si le joueur a perdu toutes ses vies
            }
        }

        // Afficher la grille finale
        System.out.println("Grille finale :");
        System.out.println(grilleDeJeu);

        // Résultat final
        if (grilleDeJeu.toutesCellulesRevelees()) {
            System.out.println("Vous avez gagné !");
        }

        // Fermer le scanner à la fin du jeu
        scanner.close();
    }
}
