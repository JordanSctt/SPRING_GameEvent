package fr.greta.java.invitation.persistence.repository;

import fr.greta.java.invitation.persistence.entity.InvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, String> {

}
