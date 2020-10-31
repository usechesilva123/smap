package edu.co.software.smap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Estado;
import edu.co.software.smap.repository.EstadoRepository;

@Service
public class EstadoServiceImpl implements EstadoService{

	@Autowired
	EstadoRepository estadoRepository;

	@Override
	public Estado findByName(String name) {
		// TODO Auto-generated method stub
		return estadoRepository.findByName(name);
	}
	

}
