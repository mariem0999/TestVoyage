package tn.esprit.spring.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Voyageur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idVoyageur;
    String nom;
    String prenom;
    @Temporal(TemporalType.DATE)
    Date dateNaissance;
    @OneToMany(mappedBy = "voyageur")
    List<Reservation> reservations;

}
