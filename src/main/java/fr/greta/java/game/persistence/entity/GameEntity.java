package fr.greta.java.game.persistence.entity;


import fr.greta.java.groupe.persistence.entity.GroupeEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "_game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    private String titre;

    private String genre;

    @ManyToMany(mappedBy = "games")
    List<GroupeEntity> groupes;

    //----------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<GroupeEntity> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<GroupeEntity> groupes) {
        this.groupes = groupes;
    }
}
