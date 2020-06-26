/**
 * 
 */
package com.zabudtecnologies.taller1.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zabudtecnologies.taller1.models.Persona;
import com.zabudtecnologies.taller1.services.IPersonaService;

/**
 * @author Jorge
 * @since 25/06/2020
 */

@RestController
@RequestMapping("/api")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/personas")
	public ResponseEntity<?> showAll(){
		
		List<Persona> personas = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			personas = personaService.findAll();
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if ( personas.isEmpty()) {
			response.put("mensaje", " no existen personas en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}
	
	@PostMapping("/personas")
	public ResponseEntity<?> create(@Valid @RequestBody Persona persona, BindingResult result) {
		
		Persona personaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			personaNew = personaService.save(persona);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La persona ha sido creada con éxito!");
		response.put("persona", personaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/personas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Persona persona = null;
		Map<String, Object> response = new HashMap<>();

		try {
			persona = personaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (persona == null) {
			response.put("mensaje", "La persona ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@PutMapping("personas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Persona persona, @PathVariable Long id, BindingResult result) {
		Persona personaUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		Persona personaActual = personaService.findById(id);

		if (personaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la persona ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			personaActual.setPrimerNombre(persona.getPrimerNombre());
			personaActual.setSegundoNombre(persona.getSegundoNombre());
			personaActual.setPrimerApellido(persona.getSegundoApellido());
			personaActual.setSegundoApellido(persona.getSegundoApellido());
			personaActual.setEdad(persona.getEdad());
			personaActual.setSexo(persona.getSexo());
			personaActual.setDireccion(persona.getDireccion());
			personaActual.setCelular(persona.getCelular());
			
			personaUpdate = personaService.save(personaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la persona en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La persona ha sido actualizada con éxito!");
		response.put("persona", personaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			personaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la persona de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La persona ha sido eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
