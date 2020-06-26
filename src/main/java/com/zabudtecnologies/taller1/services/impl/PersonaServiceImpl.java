/**
 * 
 */
package com.zabudtecnologies.taller1.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabudtecnologies.taller1.dao.IPersonaDao;
import com.zabudtecnologies.taller1.models.Persona;
import com.zabudtecnologies.taller1.services.IPersonaService;

/**
 * @author Jorge
 * @since 25/06/2020
 */

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	public Persona findById(Long id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id).orElse(null);
	}

	@Override
	public Persona save(Persona persona) {
		// TODO Auto-generated method stub
		return personaDao.save(persona);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		personaDao.deleteById(id);

	}

}
