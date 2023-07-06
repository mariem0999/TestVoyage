package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.Vol;

import java.util.Date;
import java.util.List;

@Repository
public interface VolRepo extends JpaRepository<Vol,Integer> {
    List<Vol> findByDateDepart(Date d);
}
