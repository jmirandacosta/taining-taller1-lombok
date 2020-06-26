/**
 * 
 */
package com.zabudtecnologies.taller1.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jorge
 * @since 25/06/2020
 */

@Entity
@Table(name="personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min=2, max=15, message = "el tamaño tiene que estar entre 2 y 15")
	@Column(nullable = false)
	private String primerNombre;
	
	@Column(nullable = true)
	private String segundoNombre;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min=2, max=15, message = "el tamaño tiene que estar entre 2 y 15")
	@Column(nullable = false)
	private String primerApellido;
	
	@Column(nullable = true)
	private String segundoApellido;
	
	
	@Min(value = 18, message = "La edad no debe ser menor a 18")
    @Max(value = 150, message = "La edad no debe ser mayor a 150")
	@Column(nullable = false)
	private int edad;
	
	@NotEmpty(message = "no puede estar vacio")
	@Pattern(regexp = "^[M|F]{1}$", message ="Debe ser M o F")
	@Column(nullable = false)
	private String sexo;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@Column(nullable = false)
	private String celular;
	
	@Column(nullable = false)
	private String direccion;
	
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

}
