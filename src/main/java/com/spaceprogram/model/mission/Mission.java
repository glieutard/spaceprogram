/**
 * 
 */
package com.spaceprogram.model.mission;

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
import com.spaceprogram.model.mission.state.MissionState;
import com.spaceprogram.model.spaceship.Spaceship;

/**
 * @author GLieutard
 * 
 * Entity Mission
 *
 */
@Entity
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
	@OneToMany
    @JoinTable(
            name="mission_spaceships",
            joinColumns = @JoinColumn( name="idMission"),
            inverseJoinColumns = @JoinColumn( name="idSpaceship")
    )
	@ApiObjectField(description = "Spaceships")
	private List<Spaceship> spaceships;
	
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

}
