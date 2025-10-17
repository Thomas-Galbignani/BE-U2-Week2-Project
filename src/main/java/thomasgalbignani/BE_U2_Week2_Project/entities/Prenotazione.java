package thomasgalbignani.BE_U2_Week2_Project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "preferenza_volo", length = 100)
    private String preferenzaVolo;

    @Column(name = "preferenza_alloggio", length = 100)
    private String preferenzaAlloggio;

}
