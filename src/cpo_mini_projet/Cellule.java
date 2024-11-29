/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpo_mini_projet;

/**
 *
 * @author lenovo
 */
public class Cellule {
     private boolean presenceBombe;
     private boolean devoilee;
     private int nbBombesAdjacentes;

    public Cellule(boolean presenceBombe, boolean devoilee, int nbBombesAdjacentes) {
        this.presenceBombe = presenceBombe;
        this.devoilee = devoilee;
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }
     public boolean getPresenceBombe(){
         return presenceBombe;
     }
public int getNbBombesAdjacentes(){
         return nbBombesAdjacentes ;
}
public void placerBombe() {
        presenceBombe = true;
}
public void revelerCellule(){
    devoilee = true;
}
}
