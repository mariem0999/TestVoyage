package tn.esprit.spring.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entities.*;
import tn.esprit.spring.Repositories.AeroportRepo;
import tn.esprit.spring.Repositories.ReservationRepo;
import tn.esprit.spring.Repositories.VolRepo;
import tn.esprit.spring.Repositories.VoyageurRepo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Services implements IServices {

    @Autowired
    AeroportRepo aeroportRepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    VolRepo volRepo;
    @Autowired
    VoyageurRepo voyageurRepo;

    @Override
    public String ajouterVolEtAeroport(Vol vol) {
        if (vol.getAArrive().getCodeAITA() == vol.getADepart().getCodeAITA()) {
            return "Vérifier les aéroports saisis";
        }
        //Vol est le parent
        //Aeroport est la child ==> Cascade
        return "La vol est ajouté avec succès ! L'id => " + volRepo.save(vol).getIdVol();
    }

    @Override
    public List<Voyageur> ajouterVoyageurs(List<Voyageur> voyageurs) {
        log.info("Voyageurs ajoutés avec succes");
        return voyageurRepo.saveAll(voyageurs);
    }

    @Override
    public String reserverVol(int voyageurId, int volId, ClassPlace classPlace) {
        String str = "";
        Vol vol = volRepo.findById(volId).get();
        Voyageur voyageur = voyageurRepo.findById(voyageurId).get();
        Reservation reservation;
        if (classPlace.toString().equals("BUSINESS")) { // Business ==> Max place 2
            if (reservationRepo.findByVolIdVolAndPlaceClassAndEtatReservation(vol.getIdVol(), ClassPlace.BUSINESS, EtatReservation.CONFIRMEE).size() <= 2) {
                reservation = Reservation.builder().
                        idReservation(ClassPlace.BUSINESS.toString().substring(0, 3) + "-" + vol.getIdVol() + "-" + voyageur.getIdVoyageur()).
                        dateReservation(new Date()).etatReservation(EtatReservation.ENCOURS).
                        placeClass(ClassPlace.BUSINESS).vol(vol).voyageur(voyageur).build();
                reservation = reservationRepo.save(reservation);
                str = "L'affectation du voyageur est effectué avec succés.L'ID de la réservation est: " + reservation.getIdReservation();
            } else {
                str = "La classe Business du vol " + vol.getIdVol() + " est complet";
            }
        } else if (classPlace.toString().equals("ECONOMIQUE")) { //Economique ==> Max place 3
            if (reservationRepo.findByVolIdVolAndPlaceClassAndEtatReservation(vol.getIdVol(), ClassPlace.ECONOMIQUE, EtatReservation.CONFIRMEE).size() <= 3) {
                reservation = Reservation.builder().
                        idReservation(ClassPlace.ECONOMIQUE.toString().substring(0, 3) + "-" + vol.getIdVol() + "-" + voyageur.getIdVoyageur()).
                        dateReservation(new Date()).etatReservation(EtatReservation.ENCOURS).
                        placeClass(ClassPlace.BUSINESS).vol(vol).voyageur(voyageur).build();
                reservation = reservationRepo.save(reservation);
                str = "L'affectation du voyageur est effectué avec succés.L'ID de la réservation est: " + reservation.getIdReservation();
            } else {
                str = "La classe Economique du vol " + vol.getIdVol() + " est complet";
            }
        }
        return str;
    }

    @Override
    public Reservation confirmerReservation(String resId) {
        Reservation res = reservationRepo.findById(resId).get();
        res.setEtatReservation(EtatReservation.CONFIRMEE);
        return reservationRepo.save(res);
    }

    @Override
    public Map<Integer, List<Voyageur>> getVoyageurByVol() {
        Map<Integer, List<Voyageur>> map = new HashMap<>();
        for (Vol v : volRepo.findByDateDepart(new Date())) {
            map.put(v.getIdVol(), voyageurRepo.findByReservationsVolIdVolAndReservationsEtatReservation(v.getIdVol(), EtatReservation.CONFIRMEE));
        }
        return map;
    }

    @Override
    @Scheduled(cron = "* * * * * *")
    public void annulerReservation() {
        for (Reservation res : reservationRepo.findAll()) {
            if (calculDiff(res.getVol().getDateDepart(), new Date()) <= 1 && res.getEtatReservation() == EtatReservation.ENCOURS) {
                res.setEtatReservation(EtatReservation.ANNULEE);
                reservationRepo.save(res);
                log.info("Reservation annulé: " + res.getIdReservation() + "\n");
            }
        }
    }

    public long calculDiff(Date dateDepart, Date dateArrivee) {
        long diffInMillies = dateArrivee.getTime() - dateDepart.getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
