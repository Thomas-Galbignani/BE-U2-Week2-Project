package thomasgalbignani.BE_U2_Week2_Project.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Viaggio {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false)
    private String destinazione;

    @Column(name = "data_viaggio", nullable = false)
    private LocalDate dataViaggio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato = StatoViaggio.IN_PROGRAMMA;

}
