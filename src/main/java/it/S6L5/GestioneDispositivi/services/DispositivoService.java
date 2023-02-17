package it.S6L5.GestioneDispositivi.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.S6L5.GestioneDispositivi.models.Dispositivo;
import it.S6L5.GestioneDispositivi.repositories.DispositivoRepository;

@Service
public class DispositivoService {
	
	@Autowired
	private DispositivoRepository repo;
	
	public Optional<Dispositivo> getById(int id){
		return repo.findById(id);
	}
	
	public Dispositivo save(Dispositivo obj) {
		return repo.save(obj);
	}
	
	public List<Dispositivo> getAll() {
		return repo.findAll();
	}
	
	public Page<Dispositivo> getAll_page(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	
	public void delete(Dispositivo u) {
		repo.delete(u);
	}
}
