package it.S6L5.GestioneDispositivi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import it.S6L5.GestioneDispositivi.models.Utente;

@Repository
public interface UtenteRepository extends JpaRepository <Utente, Integer> {
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM dipendenti WHERE username = :un"
		)
		List<Utente> findByUserName(@Param("un") String un);
		
}
