package fr.greta.java.user.persistence.entity;

import fr.greta.java.event.persistence.entity.EventEntity;
import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String login;

    private String email;

    private String password;

    private String role;

    @ManyToMany(mappedBy = "users")
    List<GroupeEntity> groupes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<EventEntity> events = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<GameEntity> games = new ArrayList<>();

    //----------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GroupeEntity> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<GroupeEntity> groupes) {
        this.groupes = groupes;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    public List<GameEntity> getGames() {
        return games;
    }

    public void setGames(List<GameEntity> games) {
        this.games = games;
    }
}
