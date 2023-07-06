package tn.esprit.spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation implements Serializable {
    @Id
    @Column(length = 100)
    String idReservation;
    @Temporal(TemporalType.DATE)
    Date dateReservation;
    @Enumerated(EnumType.STRING)
    ClassPlace placeClass;
    @Enumerated(EnumType.STRING)
    EtatReservation etatReservation;
    @ManyToOne
    @JsonIgnore
    Voyageur voyageur;
    @ManyToOne
    @JsonIgnore
    Vol vol;


}
