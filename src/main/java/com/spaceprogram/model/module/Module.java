/**
 * 
 */
package com.spaceprogram.model.module;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spaceprogram.model.module.type.ModuleType;

/**
 * @author GLieutard
 * 
 * Entity Module
 *
 */
@Entity
@ApiObject(name = "Module", description = "Entity Module")
public class Module implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -7427387080246841024L;
	
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
	 * Type
	 */
	@OneToOne
	@JoinColumn(name = "idType", referencedColumnName = "id")
	@ApiObjectField(description = "Type")
	private ModuleType Type;
	
	/**
	 * Fire Power
	 */
	@JsonInclude(value = Include.NON_NULL)
	@ApiObjectField(description = "Fire Power")
	private Integer firePower;
	
	/**
	 * Cargo Capacity
	 */
	@JsonInclude(value = Include.NON_NULL)
	@ApiObjectField(description = "Cargo Capacity")
	private Integer cargoCapacity;

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
	 * @return the type
	 */
	public ModuleType getType() {
		return Type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ModuleType type) {
		Type = type;
	}

	/**
	 * @return the firePower
	 */
	public Integer getFirePower() {
		return firePower;
	}

	/**
	 * @param firePower the firePower to set
	 */
	public void setFirePower(Integer firePower) {
		this.firePower = firePower;
	}

	/**
	 * @return the cargoCapacity
	 */
	public Integer getCargoCapacity() {
		return cargoCapacity;
	}

	/**
	 * @param cargoCapacity the cargoCapacity to set
	 */
	public void setCargoCapacity(Integer cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

}
