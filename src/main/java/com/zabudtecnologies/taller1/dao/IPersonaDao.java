package com.zabudtecnologies.taller1.dao;

import org.springframework.data.repository.CrudRepository;

import com.zabudtecnologies.taller1.models.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long>{

}
