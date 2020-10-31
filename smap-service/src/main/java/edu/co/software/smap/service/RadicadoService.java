package edu.co.software.smap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Radicado;

@Service
public interface RadicadoService {
	
	Radicado saveRadicado(Radicado radicado);
	
	List<Radicado> fetch();
	
	Radicado findById(Integer id);
	
	List<Radicado> findByUser(Integer id);
	
	void delete(Radicado usuario);

}
