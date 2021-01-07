package fr.greta.java.videogames.persistence;


import fr.greta.java.videogames.domain.GameGenre;

import javax.persistence.*;


@Entity
@Table(name = "game")
public class GameEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    private int note;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private GameGenre genre;


    //----------------------------------------------


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public GameGenre getGenre() {
        return genre;
    }

    public void setGenre(GameGenre genre) {
        this.genre = genre;
    }
}
