package tn.esprit.spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idVol;
    @Temporal(TemporalType.DATE)
    Date dateDepart;
    @Temporal(TemporalType.DATE)
    Date dateArrive;
    @OneToMany(mappedBy = "vol")
    @JsonIgnore
    List<Reservation> reservations;
    @ManyToOne(cascade = CascadeType.ALL)
    Aeroport aDepart;
    @ManyToOne(cascade = CascadeType.ALL)
    Aeroport aArrive;


}
