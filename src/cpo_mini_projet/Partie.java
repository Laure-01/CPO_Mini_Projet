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
        this.grilleDeJeu = new GrilleDeJeu(nbLignes, nbColonnes, nbBombes);
        this.nbVies = nbVies;
        this.jeuEnCours = true;
    }
    // Méthode d'initialisation de la partie
    public void initialiserPartie() {
        // Recalcule les bombes adjacentes après avoir placé les bombes
        grilleDeJeu.calculerBombesAdjacentes();
        // L'état du jeu est toujours en cours au début
        jeuEnCours = true;
        // Réinitialisation des vies (par exemple à 3 vies)
        nbVies = 3; // Vous pouvez ajuster cette valeur selon vos besoins
        System.out.println("La partie est maintenant commencée !");
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
            System.out.println(grilleDeJeu.Devoile(ligne, colonne));
            }
    
        

        // Vérifier si le joueur a gagné (toutes les cellules sûres révélées)
        if (verifierVictoire()) {
            jeuEnCours = false;
            System.out.println("Félicitations! Vous avez gagné.");
        }
    }
    // Méthode pour obtenir l'état de la grille sous forme textuelle
    public void afficherGrille() {
        System.out.println(grilleDeJeu.toString());
    }

    // Méthode pour obtenir le nombre de vies restantes
    public int getNbVies() {
        return nbVies;
    }

    // Méthode pour vérifier si le jeu est toujours en cours
    public boolean isJeuEnCours() {
        return jeuEnCours;
    }
    // Méthode principale qui démarre la partie
    public void demarrerPartie() {
        Scanner scanner = new Scanner(System.in);
         // Boucle du jeu tant que le jeu est en cours
        while (jeuEnCours) {
            afficherGrille();
            System.out.println("Entrez la ligne et la colonne à révéler (ex: 2 3) : ");
            
            // Lire les coordonnées de la cellule à révéler
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();
            
            // Appeler la méthode tourDeJeu
            tourDeJeu(ligne, colonne);
        }

        // Fermer le scanner à la fin du jeu
        scanner.close();
    
    }
}

