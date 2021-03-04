package fr.greta.java.game.domain;

import java.util.ArrayList;
import java.util.List;

public class SearchGame {

    private List<GameColonne> colonnes = new ArrayList<>();
    private String texte;

    //-------------------------------

    public List<GameColonne> getColonnes() {
        return colonnes;
    }

    public void setColonnes(List<GameColonne> colonnes) {
        this.colonnes = colonnes;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
