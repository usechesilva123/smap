package edu.co.software.smap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Tipo;
import edu.co.software.smap.repository.TipoRepository;

@Service
public class TipoServiceImpl implements TipoService{

	@Autowired
	TipoRepository tipoRepository;

	@Override
	public Tipo findByName(String name) {
		// TODO Auto-generated method stub
		return tipoRepository.findByName(name);
	}
	

}
