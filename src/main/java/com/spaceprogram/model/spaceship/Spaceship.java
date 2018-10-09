/**
 * 
 */
package com.spaceprogram.model.spaceship;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.model.engine.Engine;
import com.spaceprogram.model.module.Module;
import com.spaceprogram.model.spaceship.type.SpaceshipType;

/**
 * @author GLieutard
 * 
 * Entity Spaceship
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiObjectField(description = "Id")
	private Integer id;
	
	/*
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
	private SpaceshipType type;
	
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
	 * Cargoe Capacity
	 */
	@ApiObjectField(description = "Cargo Capacity")
	private Integer cargoCapacity;
	
	/**
	 * Crews
	 */
//	@ElementCollection(targetClass=String.class) ///// A Tester en changeant les noms
	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany
    @JoinTable(
            name="spaceship_crews",
            joinColumns = @JoinColumn( name="idSpaceship"),
            inverseJoinColumns = @JoinColumn( name="idCrew")
    )
	@ApiObjectField(description = "Crews")
	private List<Crew> crews;
	
	/**
	 * Engines
	 */
	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany
    @JoinTable(
            name="spaceship_engines",
            joinColumns = @JoinColumn( name="idSpaceship"),
            inverseJoinColumns = @JoinColumn( name="idEngine")
    )
	@ApiObjectField(description = "Engines")
	private List<Engine> engines;
	
	/**
	 * Modules
	 */
	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany
    @JoinTable(
            name="spaceship_modules",
            joinColumns = @JoinColumn( name="idSpaceship"),
            inverseJoinColumns = @JoinColumn( name="idModule")
    )
	@ApiObjectField(description = "Modules")
	private List<Module> modules;
	
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
	public SpaceshipType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SpaceshipType type) {
		this.type = type;
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

	/**
	 * @return the crews
	 */
	public List<Crew> getCrews() {
		return crews;
	}

	/**
	 * @param crews the crews to set
	 */
	public void setCrews(List<Crew> crews) {
		this.crews = crews;
	}

	/**
	 * @return the engines
	 */
	public List<Engine> getEngines() {
		return engines;
	}

	/**
	 * @param engines the engines to set
	 */
	public void setEngines(List<Engine> engines) {
		this.engines = engines;
	}

	/**
	 * @return the modules
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

}
