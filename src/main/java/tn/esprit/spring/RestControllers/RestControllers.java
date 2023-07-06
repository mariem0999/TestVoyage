package tn.esprit.spring.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Entities.ClassPlace;
import tn.esprit.spring.Entities.Reservation;
import tn.esprit.spring.Entities.Vol;
import tn.esprit.spring.Entities.Voyageur;
import tn.esprit.spring.Services.IServices;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("restControllers")
public class RestControllers {
    @Autowired
    IServices iServices;

    @PostMapping("ajouterVolEtAeroport")
    public String ajouterVolEtAeroport(@RequestBody Vol v) {
        return iServices.ajouterVolEtAeroport(v);
    }

    @PostMapping("ajouterVoyageur")
    public List<Voyageur> ajouterVoyageur(@RequestBody List<Voyageur> voyageurs) {
        return iServices.ajouterVoyageurs(voyageurs);
    }

    @PostMapping("reserverVol")
    public String reserverVol(@RequestParam int voyageurId, @RequestParam int volId, @RequestParam ClassPlace classPlace) {
        return iServices.reserverVol(voyageurId, volId, classPlace);
    }

    @GetMapping("afficcherVoyageurParVol")
    public Map<Integer, List<Voyageur>> afficcherVoyageurParVol() {
        return iServices.getVoyageurByVol();
    }

    @PutMapping("confimerReservation")
    public Reservation confirmerReservation(@RequestParam String resId) {
        return iServices.confirmerReservation(resId);
    }
}