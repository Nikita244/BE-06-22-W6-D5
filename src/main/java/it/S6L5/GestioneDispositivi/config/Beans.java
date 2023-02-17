package it.S6L5.GestioneDispositivi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.S6L5.GestioneDispositivi.models.DispDispositivo;
import it.S6L5.GestioneDispositivi.models.Dispositivo;
import it.S6L5.GestioneDispositivi.models.Role;
import it.S6L5.GestioneDispositivi.models.RoleType;
import it.S6L5.GestioneDispositivi.models.TipoDispositivo;
import it.S6L5.GestioneDispositivi.models.Utente;

@Configuration
public class Beans {
	
	@Bean
	@Scope("prototype")
	public Utente utente(String username, String nome, String cognome, String email, String password) {
		return Utente.builder()
					.username(username)
					.nome(nome)
					.cognome(cognome)
					.email(email)
					.password(password)
					.build();
	}
	
	@Bean
	@Scope("prototype")
	public Dispositivo dispositivo(TipoDispositivo tipo, DispDispositivo disp ) {
		return Dispositivo.builder()
				.tipo(tipo)
				.disponibilita(disp)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleType rt) {
		return Role.builder()
				.type(rt)
				.build();
	}
}
