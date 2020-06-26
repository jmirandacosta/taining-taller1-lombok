package com.zabudtecnologies.taller1.services;

import java.util.List;

import com.zabudtecnologies.taller1.models.Persona;

public interface IPersonaService {
	
	public List<Persona> findAll();
	
	public Persona findById(Long id);
	
	public Persona save(Persona persona);
	
	public void delete(Long id);

}
