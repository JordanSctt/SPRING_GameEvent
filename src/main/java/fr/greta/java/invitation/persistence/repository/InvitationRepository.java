package fr.greta.java.invitation.persistence.repository;

import fr.greta.java.groupe.persistence.entity.GroupeEntity;
import fr.greta.java.invitation.persistence.entity.InvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, String> {

    /*@Query("select i FROM InvitationEntity i " +
            "LEFT JOIN FETCH i.groupe g " +
            "LEFT JOIN FETCH i.user u where u.id :userId")
    @Query("select i FROM InvitationEntity LEFT JOIN FETCH i.groupe g WHERE i.user_id :userId")
    List<InvitationEntity> findInvitationsGroupesByUserId(String userId);*/

}
