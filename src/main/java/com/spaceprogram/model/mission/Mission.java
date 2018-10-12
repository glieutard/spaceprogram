/**
 * 
 */
package com.spaceprogram.model.mission;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spaceprogram.model.coordinates.Coordinates;
import com.spaceprogram.model.mission.state.MissionState;
import com.spaceprogram.model.spaceship.Spaceship;

/**
 * @author GLieutard
 * 
 * Entity Mission
 *
 */
@Entity
@AttributeOverrides({
	@AttributeOverride(name = "baseCoordinates.x", column = @Column(name = "baseCoordinateX")),
	@AttributeOverride(name = "baseCoordinates.y", column = @Column(name = "baseCoordinateY")),
	@AttributeOverride(name = "baseCoordinates.z", column = @Column(name = "baseCoordinateZ")),
	@AttributeOverride(name = "targetCoordinates.x", column = @Column(name = "targetCoordinateX")),
	@AttributeOverride(name = "targetCoordinates.y", column = @Column(name = "targetCoordinateY")),
	@AttributeOverride(name = "targetCoordinates.z", column = @Column(name = "targetCoordinateZ"))
	})
@ApiObject(name = "Mission", description = "Entity Mission")
public class Mission implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -8725912297825152839L;
	
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
	 * Description
	 */
	@ApiObjectField(description = "Description")
	private String description;
	
	/**
	 * State
	 */
	@OneToOne
	@JoinColumn(name = "idState", referencedColumnName = "id")
	@ApiObjectField(description = "State")
	private MissionState state;

	/**
	 * Spaceships
	 */
	@JsonInclude(value = Include.NON_EMPTY)
	@Transient
	@ApiObjectField(description = "Spaceships")
	private List<Spaceship> spaceships;
	
	/**
	 * Base Coordinates
	 */
	@ApiObjectField(description = "Base Coordinates")
	private Coordinates baseCoordinates;

	/**
	 * Target Coordinates
	 */
	@ApiObjectField(description = "Target Coordinates")
	private Coordinates targetCoordinates;
	
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the state
	 */
	public MissionState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(MissionState state) {
		this.state = state;
	}

	/**
	 * @return the spaceships
	 */
	public List<Spaceship> getSpaceships() {
		return spaceships;
	}

	/**
	 * @param spaceships the spaceships to set
	 */
	public void setSpaceships(List<Spaceship> spaceships) {
		this.spaceships = spaceships;
	}

	/**
	 * @return the baseCoordinates
	 */
	public Coordinates getBaseCoordinates() {
		return baseCoordinates;
	}

	/**
	 * @param baseCoordinates the baseCoordinates to set
	 */
	public void setBaseCoordinates(Coordinates baseCoordinates) {
		this.baseCoordinates = baseCoordinates;
	}

	/**
	 * @return the targetCoordinates
	 */
	public Coordinates getTargetCoordinates() {
		return targetCoordinates;
	}

	/**
	 * @param targetCoordinates the targetCoordinates to set
	 */
	public void setTargetCoordinates(Coordinates targetCoordinates) {
		this.targetCoordinates = targetCoordinates;
	}

}
