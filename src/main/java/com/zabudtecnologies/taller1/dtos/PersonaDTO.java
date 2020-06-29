/**
 * 
 */
package com.zabudtecnologies.taller1.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jorge
 * @since 28/06/2020
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PersonaDTO {
	
	private Long id;
	
	private String primerNombre;
	
	private String segundoNombre;
	
	private String primerApellido;
	
	private String segundoApellido;
	
	private int edad;
	
	private String sexo;
	
	private String celular;
	
	private String direccion;
	
	private String email;
	
	private Date createAt;

}
