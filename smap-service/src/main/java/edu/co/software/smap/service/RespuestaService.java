package edu.co.software.smap.service;

import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Respuesta;

@Service
public interface RespuestaService {

	public Respuesta save(Respuesta respuesta);

}
