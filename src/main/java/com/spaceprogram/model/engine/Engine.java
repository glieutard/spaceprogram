/**
 * 
 */
package com.spaceprogram.model.engine;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author GLieutard
 * 
 * Entity Engine
 *
 */
@Entity
@Audited
@ApiObject(name = "Engine", description = "Entity Engine")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Engine implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -6269845674900200921L;
	
	/**
	 * Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiObjectField(description = "Id")
	private Integer id;
	
	/**
	 * Name
	 */
	@ApiObjectField(description = "Name")
	private String name;
	
	/**
	 * Horse Power
	 */
	@ApiObjectField(description = "Horse Power")
	private Integer horsePower;
	
	/**
	 * Weight
	 */
	@ApiObjectField(description = "Weight")
	private Integer weight;

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

	/**
	 * @return the horsePower
	 */
	public Integer getHorsePower() {
		return horsePower;
	}

	/**
	 * @param horsePower the horsePower to set
	 */
	public void setHorsePower(Integer horsePower) {
		this.horsePower = horsePower;
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
