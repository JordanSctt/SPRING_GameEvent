package fr.greta.java.game.persistence.entity;


import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.user.persistence.entity.UserEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "_game")
public class GameEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String titre;

    private String genre;

    @ManyToMany(mappedBy = "games")
    List<GroupeEntity> groupes;

    @ManyToMany(mappedBy = "games")
    List<UserEntity> users;

    //----------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
