package fr.greta.java.videogames.domain;

public enum GameColonne {

    ID, TITRE, NOTE, COMMENTAIRE, GENRE;


    public String colonneEntity() {
        switch(this) {
            case TITRE:
                return "titre";
            case NOTE:
                return "note";
            case COMMENTAIRE:
                return "commentaire";
            case GENRE:
                return "genre";
            default:
                return "id";
        }
    }
}
