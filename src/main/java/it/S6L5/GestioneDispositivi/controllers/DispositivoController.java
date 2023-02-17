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

import it.S6L5.GestioneDispositivi.models.Dispositivo;
import it.S6L5.GestioneDispositivi.models.Utente;
import it.S6L5.GestioneDispositivi.services.DispositivoService;

@Controller
public class DispositivoController {
	
	@Autowired
	private DispositivoService dispServ;
	
	//---------------------------------GET ALL DISPOSITIVI
		@GetMapping("/dispositivi")
		public ResponseEntity<List<Dispositivo>> getAll() {
			List<Dispositivo> disp = dispServ.getAll();
			
			if( disp.isEmpty() ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(disp, HttpStatus.OK);
		}
		
		
		//---------------------------------GET DISPOSITIVI BY ID
		@GetMapping("dispositivi/{id}")
		public ResponseEntity<Object> getById(@PathVariable Integer id) {
			Optional<Dispositivo> dispObj = dispServ.getById(id);
			ResponseEntity<Object> check = checkExists(dispObj);
			if( check != null ) return check;
			
			return new ResponseEntity<>(dispObj.get(), HttpStatus.OK);
		}
		
		
		//---------------------------------PAGINATION
		//http://localhost:8080/dispositivi_page?page=0&size=1
		@GetMapping("dispositivi_page")
		public ResponseEntity<Object> getAll_page(Pageable pageable) {
			Page<Dispositivo> disp = dispServ.getAll_page(pageable);
			
			if( disp.isEmpty() ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(disp, HttpStatus.OK);
		}
		
		//---------------------------------POST DISPOSITIVO
		
		@PostMapping("dispositivi")
		public ResponseEntity<Object> create(@RequestBody Dispositivo d) {
			Dispositivo disp = dispServ.save(d);
			
			return new ResponseEntity<>(disp, HttpStatus.CREATED);
		}
		
		//---------------------------------PUT DISPOSITIVO BY ID
		@PutMapping("dispositivi/{id}")
		public ResponseEntity<Object> update(
				@PathVariable Integer id,
				@RequestBody Dispositivo _disp) {
			
			Optional<Dispositivo> dispObj = dispServ.getById(id);
			
			ResponseEntity<Object> check = checkExists(dispObj);
			if( check != null ) return check;
			
			Dispositivo disp = dispObj.get();
			disp.setTipo(_disp.getTipo());
			disp.setDisponibilita(_disp.getDisponibilita());
			
			dispServ.save(disp);
			
			return new ResponseEntity<>(disp, HttpStatus.CREATED);
		}
		
		//---------------------------------DELETE DISPOSITIVO BY ID
		
		@DeleteMapping("dispositivi/{id}")
		public ResponseEntity<Object> delete(@PathVariable Integer id) {
			Optional<Dispositivo> dispObj = dispServ.getById(id);
			ResponseEntity<Object> check = checkExists(dispObj);
			if( check != null ) return check;
			
			dispServ.delete(dispObj.get());
			return new ResponseEntity<>(
					String.format("Dispositivo con id %d cancellato correttamente!", id), HttpStatus.OK);
		}
		
		
		
		private ResponseEntity<Object> checkExists(Optional<Dispositivo> obj) {
			if( !obj.isPresent() ) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return null;
		}
		

}
