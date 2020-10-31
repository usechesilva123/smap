package edu.co.software.smap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Respuesta;
import edu.co.software.smap.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	RespuestaRepository respuestaRepository;
	
	@Override
	public Respuesta save(Respuesta respuesta) {
		// TODO Auto-generated method stub
		return respuestaRepository.save(respuesta);
	}
}
