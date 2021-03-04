package fr.greta.java.game.facade.dto;

public class SearchRequestDTO {

    private String texte;
    private boolean rechercheParTitre;
    private boolean rechercheParGenre;

    //-------------------------------------


    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isRechercheParTitre() {
        return rechercheParTitre;
    }

    public void setRechercheParTitre(boolean rechercheParTitre) {
        this.rechercheParTitre = rechercheParTitre;
    }

    public boolean isRechercheParGenre() {
        return rechercheParGenre;
    }

    public void setRechercheParGenre(boolean rechercheParGenre) {
        this.rechercheParGenre = rechercheParGenre;
    }
}
