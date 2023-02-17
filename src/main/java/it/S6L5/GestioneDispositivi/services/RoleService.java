package it.S6L5.GestioneDispositivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.S6L5.GestioneDispositivi.models.Role;
import it.S6L5.GestioneDispositivi.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Role save(Role obj) {
		return roleRepo.save(obj);
	}

}
