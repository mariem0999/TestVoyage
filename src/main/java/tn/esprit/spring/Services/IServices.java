package tn.esprit.spring.Services;

import tn.esprit.spring.Entities.ClassPlace;
import tn.esprit.spring.Entities.Reservation;
import tn.esprit.spring.Entities.Vol;
import tn.esprit.spring.Entities.Voyageur;

import java.util.List;
import java.util.Map;

public interface IServices {

    String ajouterVolEtAeroport(Vol vol);
    List<Voyageur> ajouterVoyageurs(List<Voyageur> voyageurs);
    String reserverVol(int voyageurId, int volId, ClassPlace classPlace);
    Reservation confirmerReservation(String resId);
    Map<Integer,List<Voyageur>> getVoyageurByVol();
    void annulerReservation();
}
