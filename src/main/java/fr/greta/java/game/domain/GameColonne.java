package fr.greta.java.game.domain;

public enum GameColonne {

    ID, TITRE, GENRE;


    public String colonneEntity() {
        switch(this) {
            case TITRE:
                return "titre";
            case GENRE:
                return "genre";
            default:
                return "id";
        }
    }
}
