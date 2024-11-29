/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

/**
 *
 * @author lenovo
 */
public class Demineur {
    public static void main(String []args) {
        // Création d'une cellule avec une bombe et 2 bombes adjacentes
        Cellule cellule1 = new Cellule(true, false, 2);
        System.out.println("Cellule 1 (initiale): " + cellule1);

        // Création d'une cellule sans bombe et sans bombes adjacentes
        Cellule cellule2 = new Cellule(false, false, 0);
        System.out.println("Cellule 2 (initiale): " + cellule2);

        // Révéler la cellule 2
        cellule2.revelerCellule();
        System.out.println("Cellule 2 (révélée): " + cellule2);

        // Révéler la cellule 1
        cellule1.revelerCellule();
        System.out.println("Cellule 1 (révélée): " + cellule1);

        // Créer une autre cellule sans bombe et avec 3 bombes adjacentes
        Cellule cellule3 = new Cellule(false, false, 3);
        System.out.println("Cellule 3 (initiale): " + cellule3);

        // Révéler la cellule 3
        cellule3.revelerCellule();
        System.out.println("Cellule 3 (révélée): " + cellule3);

        // Test de cellule avec 0 bombes adjacentes et vide
        Cellule cellule4 = new Cellule(false, false, 0);
        System.out.println("Cellule 4 (avant révélation): " + cellule4);
        cellule4.revelerCellule();
        System.out.println("Cellule 4 (révélée): " + cellule4);
    }
    }
