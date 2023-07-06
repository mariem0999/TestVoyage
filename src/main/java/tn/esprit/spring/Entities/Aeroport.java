package tn.esprit.spring.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Aeroport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAeroport;
    String nom;
    @Column(unique = true,length = 3)
    String codeAITA;
    long telephone;

}
