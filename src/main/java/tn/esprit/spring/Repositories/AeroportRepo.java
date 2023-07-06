package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.Aeroport;

@Repository
public interface AeroportRepo extends JpaRepository<Aeroport, Integer> {
}
