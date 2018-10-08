/**
 * 
 */
package com.spaceprogram.model.spaceship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 *
 */
@Entity
@ApiObject(name = "Spaceship", description = "Entity Spaceship")
public class Spaceship implements Serializable {

	/**
	 * idversion
	 */
	private static final long serialVersionUID = 8355428496156661105L;
	
	/*
	 * id
	 */
	@Id
	@ApiObjectField(description = "Id")
	private Integer id;
	
	/*
	 * Name
	 */
	@ApiObjectField(description = "Name")
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
