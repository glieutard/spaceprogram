/**
 * 
 */
package com.spaceprogram.model.spaceship.type;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 * 
 * Entity SpaceshipType
 *
 */
@Entity
@Audited
@Table(name = "spaceship_type")
@ApiObject(name = "SpaceshipType", description = "Entity SpaceshipType")
public class SpaceshipType implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -4884134238962677905L;
	
	/**
	 * id
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
	 * Widdth
	 */
	@ApiObjectField(description = "Width")
	private Integer width;
	
	/**
	 * Length
	 */
	@ApiObjectField(description = "Length")
	private Integer length;
	
	/**
	 * Height
	 */
	@ApiObjectField(description = "Height")
	private Integer height;
	
	/**
	 * Weight
	 */
	@ApiObjectField(description = "Weight")
	private Integer weight;

	/**
	 * Cargo Capacity
	 */
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
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
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
