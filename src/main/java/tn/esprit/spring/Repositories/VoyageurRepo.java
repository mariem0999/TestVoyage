package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.EtatReservation;
import tn.esprit.spring.Entities.Voyageur;

import java.util.List;

@Repository
public interface VoyageurRepo extends JpaRepository<Voyageur,Integer> {

    List<Voyageur> findByReservationsVolIdVolAndReservationsEtatReservation(int volId, EtatReservation etatReservation);
}
