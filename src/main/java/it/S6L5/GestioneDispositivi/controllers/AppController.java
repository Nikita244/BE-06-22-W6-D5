package it.S6L5.GestioneDispositivi.controllers;

import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PropertySource("classpath:application.properties")
public class AppController {
	
	@GetMapping("/")
	@ResponseBody
	public String homepage() {
		return "Sono la Home Page";
	}
	
	//ROTTA SOTTO LOGIN CHE ACCETTA SIA USER CHE ADMIN
	@PostMapping("about")
	@ResponseBody
	public String about() {
		return "benvenuto nella pagina di Gestione Dispositivi";
	}
	
	/*@PostMapping("login_success")
	@ResponseBody
	public String login_success() {
		return "Login effettuato correttamente";
	}*/
	
	
	//ROTTA RISERVATA A UTENTE CON PRIVILEGIO ADMIN
	@GetMapping("info")
	@PreAuthorize("hasRole('ADMIN')")
	public String index() {
		return "index";
	}
}
