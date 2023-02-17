package it.S6L5.GestioneDispositivi.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import it.S6L5.GestioneDispositivi.models.Utente;
import it.S6L5.GestioneDispositivi.services.UtenteService;

@Controller
public class UtenteController {

	@Autowired
	private UtenteService utServ;
	
	//---------------------------------GET ALL UTENTI
	@GetMapping("/dipendenti")
	public ResponseEntity<List<Utente>> getAll() {
		List<Utente> utenti = utServ.getAll();
		
		if( utenti.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(utenti, HttpStatus.OK);
	}
	
	
	//---------------------------------GET UTENTE BY ID
	@GetMapping("dipendenti/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		Optional<Utente> personObj = utServ.getById(id);
		ResponseEntity<Object> check = checkExists(personObj);
		if( check != null ) return check;
		
		return new ResponseEntity<>(personObj.get(), HttpStatus.OK);
	}
	
	
	//---------------------------------GET UTENTE BY USERNAME
	@GetMapping("dipendenti_byusername/{username}")
	public ResponseEntity<List<Utente>> getByUsername(@PathVariable String username) {
		List<Utente> dipendenti = utServ.getByUsername(username);
		
		if( dipendenti.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(dipendenti, HttpStatus.OK);
	}
	
	//---------------------------------PAGINATION
	//http://localhost:8080/dipendenti_page?page=0&size=1
	
	@GetMapping("dipendenti_page")
	public ResponseEntity<Object> getAll_page(Pageable pageable) {
		Page<Utente> utenti = utServ.getAll_page(pageable);
		
		if( utenti.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(utenti, HttpStatus.OK);
	}
	
	//---------------------------------POST UTENTE 
	
	@PostMapping("dipendenti")
	public ResponseEntity<Object> create(@RequestBody Utente u) {
		Utente utente = utServ.save(u);
		
		return new ResponseEntity<>(utente, HttpStatus.CREATED);
	}
	
	//---------------------------------PUT UTENTE BY ID
	@PutMapping("dipendenti/{id}")
	public ResponseEntity<Object> update(
			@PathVariable Integer id,
			@RequestBody Utente _utente) {
		
		Optional<Utente> utenteObj = utServ.getById(id);
		
		ResponseEntity<Object> check = checkExists(utenteObj);
		if( check != null ) return check;
		
		Utente utente = utenteObj.get();
		utente.setUsername( _utente.getUsername() );
		utente.setNome( _utente.getNome() );
		utente.setCognome( _utente.getCognome() );
		utente.setEmail( _utente.getEmail() );
		utente.setPassword( _utente.getPassword() );
		utServ.save(utente);
		
		return new ResponseEntity<>(utente, HttpStatus.CREATED);
	}
	
	//---------------------------------DELETE UTENTE BY ID
	
	@DeleteMapping("dipendenti/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		Optional<Utente> personObj = utServ.getById(id);
		ResponseEntity<Object> check = checkExists(personObj);
		if( check != null ) return check;
		
		utServ.delete(personObj.get());
		return new ResponseEntity<>(
				String.format("Dipendente con id %d cancellato correttamente!", id), HttpStatus.OK);
	}
	
	
	private ResponseEntity<Object> checkExists(Optional<Utente> obj) {
		if( !obj.isPresent() ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return null;
	}
	
}
