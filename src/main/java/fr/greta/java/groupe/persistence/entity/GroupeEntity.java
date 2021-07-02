package fr.greta.java.groupe.persistence.entity;

import fr.greta.java.event.persistence.entity.EventEntity;
import fr.greta.java.invitation.persistence.entity.InvitationEntity;
import fr.greta.java.user.persistence.entity.UserEntity;
import fr.greta.java.game.persistence.entity.GameEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_groupe")
public class GroupeEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "groupes_games",
            joinColumns = @JoinColumn(name = "groupe_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<GameEntity> games = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "groupes_users",
            joinColumns = @JoinColumn(name = "groupe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "groupe")
    private List<EventEntity> events;

    @OneToMany(mappedBy = "groupe")
    private List<InvitationEntity> invitations;

    //------------------------------------------


    public GroupeEntity() {
    }

    public GroupeEntity(String nom, List<UserEntity> users) {
        this.nom = nom;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    public List<InvitationEntity> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<InvitationEntity> invitations) {
        this.invitations = invitations;
    }
}
