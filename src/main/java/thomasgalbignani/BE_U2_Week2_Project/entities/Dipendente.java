package thomasgalbignani.BE_U2_Week2_Project.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String cognome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "avatar_url")
    private String avatarURL;

}