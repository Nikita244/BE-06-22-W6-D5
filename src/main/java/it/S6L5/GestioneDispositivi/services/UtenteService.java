package it.S6L5.GestioneDispositivi.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.S6L5.GestioneDispositivi.models.Utente;
import it.S6L5.GestioneDispositivi.repositories.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository repo;
	
	public Optional<Utente> getById(int id){
		return repo.findById(id);
	}
	
	public Utente save(Utente obj) {
		return repo.save(obj);
	}
	
	public List<Utente> getAll() {
		return repo.findAll();
	}
	
	public Page<Utente> getAll_page(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public List<Utente> getByUsername(String fn) {
		return repo.findByUserName(fn);
	}
	
	public void delete(Utente u) {
		repo.delete(u);
	}
	
}
