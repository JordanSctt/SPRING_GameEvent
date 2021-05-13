package fr.greta.java.invitation.persistence.repository;

import fr.greta.java.invitation.persistence.entity.InvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, String> {

    Optional<InvitationEntity> findByUserIdAndGroupeId(String userId, String groupeId);

}
