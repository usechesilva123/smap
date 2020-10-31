package edu.co.software.smap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.software.smap.model.Radicado;
import edu.co.software.smap.repository.RadicadoRepository;

@Service
public class RadicadoServiceImpl implements RadicadoService{

	@Autowired
	RadicadoRepository radicadoRepository;

	@Override
	public Radicado saveRadicado(Radicado radicado) {
		// TODO Auto-generated method stub
		return radicadoRepository.save(radicado);
	}

	@Override
	public Radicado findById(Integer id) {
		// TODO Auto-generated method stub
		return radicadoRepository.getOne(id);
	}

	@Override
	public List<Radicado> findByUser(Integer id) {
		// TODO Auto-generated method stub
		return radicadoRepository.findByUser(id);
	}

	@Override
	public void delete(Radicado usuario) {
		// TODO Auto-generated method stub
		radicadoRepository.delete(usuario);
		
	}

	@Override
	public List<Radicado> fetch() {
		// TODO Auto-generated method stub
		return radicadoRepository.findAll();
	}
	

}
