package fr.greta.java.groupe.facade.dto;

import fr.greta.java.user.facade.dto.UserDTO;
import fr.greta.java.user.persistence.entity.UserEntity;

import java.util.List;

public class GroupeDTO {

    private String uuid;

    private String nom;

    private List<UserDTO> users;

    //-----------------------------------


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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
