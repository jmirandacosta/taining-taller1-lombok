/**
 * 
 */
package com.zabudtecnologies.taller1.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zabudtecnologies.taller1.dtos.PersonaDTO;
import com.zabudtecnologies.taller1.facades.PersonaServiceFacade;
import com.zabudtecnologies.taller1.models.Persona;
import com.zabudtecnologies.taller1.services.IPersonaService;

/**
 * @author Jorge
 * @since 28/06/2020
 */
@Service
public class PersonaServiceFacadeImpl implements PersonaServiceFacade {
	
	@Autowired
	private IPersonaService personaService;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<PersonaDTO> findAll() {
		// TODO Auto-generated method stub
		return objectMapper.convertValue(personaService.findAll(), new TypeReference<List<PersonaDTO>>(){});
	}

	@Override
	public PersonaDTO findById(Long id) {
		// TODO Auto-generated method stub
		return objectMapper.convertValue(personaService.findById(id), PersonaDTO.class);
	}

	@Override
	public PersonaDTO save(PersonaDTO persona) {
		// TODO Auto-generated method stub	
		Persona personaSaved = objectMapper.convertValue(persona, Persona.class);
		return objectMapper.convertValue(personaService.save(personaSaved), PersonaDTO.class) ;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		personaService.delete(id);
	}

}
