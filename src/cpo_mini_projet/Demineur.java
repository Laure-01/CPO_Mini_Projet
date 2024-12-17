
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
public class Demineur {
    public static void main(String[] args) {
        // Créer une nouvelle partie de démineur avec une grille de 10x10, 10 bombes, et 3 vies
        Partie partie = new Partie(10, 10, 10, 3);
        
        // Démarrer la partie
        partie.demarrerPartie();
    }
}

    
