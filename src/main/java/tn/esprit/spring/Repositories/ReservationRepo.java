package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.ClassPlace;
import tn.esprit.spring.Entities.EtatReservation;
import tn.esprit.spring.Entities.Reservation;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, String> {

    List<Reservation> findByVolIdVolAndPlaceClassAndEtatReservation(int idVol, ClassPlace classPlace, EtatReservation etatReservation);
}
