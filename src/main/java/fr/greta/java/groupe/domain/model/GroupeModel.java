package fr.greta.java.groupe.domain.model;


import fr.greta.java.event.persistence.entity.EventEntity;
import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.user.persistence.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;


public class GroupeModel {

    private String uuid;

    private String nom;

    private List<GameEntity> games = new ArrayList<>();

    private List<UserEntity> users = new ArrayList<>();

    private List<EventEntity> events;

    //----------------------------------------


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

}
