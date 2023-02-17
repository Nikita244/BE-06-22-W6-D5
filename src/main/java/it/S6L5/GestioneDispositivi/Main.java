package it.S6L5.GestioneDispositivi;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import it.S6L5.GestioneDispositivi.config.Beans;
import it.S6L5.GestioneDispositivi.models.DispDispositivo;
import it.S6L5.GestioneDispositivi.models.Dispositivo;
import it.S6L5.GestioneDispositivi.models.Role;
import it.S6L5.GestioneDispositivi.models.RoleType;
import it.S6L5.GestioneDispositivi.models.TipoDispositivo;
import it.S6L5.GestioneDispositivi.models.Utente;
import it.S6L5.GestioneDispositivi.services.DispositivoService;
import it.S6L5.GestioneDispositivi.services.RoleService;
import it.S6L5.GestioneDispositivi.services.UtenteService;

@SpringBootApplication
public class Main implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Autowired
	private UtenteService utServ;
	@Autowired
	private DispositivoService dispServ;
	@Autowired
	private RoleService roleServ;
	
	private int populateDbFlag = 0;


	@Override
	public void run(String... args) throws Exception {
		if( populateDbFlag == 1 ) {
			System.out.println("DB generation");
			populateDb();
		}
	}
	
	private void populateDb() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		Utente u1 = (Utente)ctx.getBean("utente", "giuly", "Giulia", "Bianchi","giulia@bianchi.it", "1234");
		
		Role r1 = (Role)ctx.getBean("role", RoleType.ROLE_ADMIN);
		Role r2 = (Role)ctx.getBean("role", RoleType.ROLE_USER);
		
		Dispositivo d1 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.CELLULARE, DispDispositivo.DISPONIBILE);
		Dispositivo d2 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.CELLULARE, DispDispositivo.ASSEGNATO);
		Dispositivo d3 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.CELLULARE, DispDispositivo.MANUTENZIONE);
		Dispositivo d4 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.CELLULARE, DispDispositivo.DISMESSO);
		
		Dispositivo d5 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.TABLET, DispDispositivo.DISPONIBILE);
		Dispositivo d6 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.TABLET, DispDispositivo.ASSEGNATO);
		Dispositivo d7 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.TABLET, DispDispositivo.MANUTENZIONE);
		Dispositivo d8 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.TABLET, DispDispositivo.DISMESSO);
		
		Dispositivo d9 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.LAPTOP, DispDispositivo.DISPONIBILE);
		Dispositivo d10 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.LAPTOP, DispDispositivo.ASSEGNATO);
		Dispositivo d11 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.LAPTOP, DispDispositivo.MANUTENZIONE);
		Dispositivo d12 = (Dispositivo)ctx.getBean("dispositivo", TipoDispositivo.LAPTOP, DispDispositivo.DISMESSO);
		
		u1.setRoles(new HashSet<>() {{
			add(r2);
		}});
		
		u1.setDispositivi(new HashSet<>() {{
			add(d6);
		}});
		
		roleServ.save(r1);
		roleServ.save(r2);
	
		dispServ.save(d1);
		dispServ.save(d2);
		dispServ.save(d3);
		dispServ.save(d4);
		dispServ.save(d4);
		dispServ.save(d5);
		dispServ.save(d6);
		dispServ.save(d6);
		dispServ.save(d7);
		dispServ.save(d8);
		dispServ.save(d9);
		dispServ.save(d10);
		dispServ.save(d11);
		dispServ.save(d12);
		
		utServ.save(u1);
		
		((AnnotationConfigApplicationContext)ctx).close();
	}
	

}
