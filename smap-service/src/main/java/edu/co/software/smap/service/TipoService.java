package edu.co.software.smap.service;

import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Tipo;

@Service
public interface TipoService {
	
	Tipo findByName(String name);

}
