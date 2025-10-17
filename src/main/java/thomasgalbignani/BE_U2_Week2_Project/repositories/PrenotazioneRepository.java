package thomasgalbignani.BE_U2_Week2_Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thomasgalbignani.BE_U2_Week2_Project.entities.Prenotazione;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    Optional<Prenotazione> findByDipendenteIdAndViaggioDataViaggio(UUID dipendenteId, LocalDate dataViaggio);
}