/**
 * 
 */
package com.spaceprogram.model.job;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 *
 */
@Entity
@Audited
@ApiObject(name = "Job", description = "Entity Job")
public class Job implements Serializable {

	/**
	 * id version
	 */
	private static final long serialVersionUID = -61244652428693107L;
	
	/*
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
