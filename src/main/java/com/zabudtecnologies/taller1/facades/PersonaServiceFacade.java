/**
 * 
 */
package com.zabudtecnologies.taller1.facades;

import java.util.List;

import com.zabudtecnologies.taller1.dtos.PersonaDTO;

/**
 * @author Jorge
 * @since 28/06/2020
 */
public interface PersonaServiceFacade {
	
	public List<PersonaDTO> findAll();
	
	public PersonaDTO findById(Long id);
	
	public PersonaDTO save(PersonaDTO persona);
	
	public void delete(Long id);

}
