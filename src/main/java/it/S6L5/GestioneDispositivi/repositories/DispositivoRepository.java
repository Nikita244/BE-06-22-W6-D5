package it.S6L5.GestioneDispositivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.S6L5.GestioneDispositivi.models.Dispositivo;


@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer>{
	

}
