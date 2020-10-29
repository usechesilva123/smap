package edu.co.software.smap.service;

import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Estado;

@Service
public interface EstadoService {
	
	Estado findByName(String name);

}
