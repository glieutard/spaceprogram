/**
 * 
 */
package com.spaceprogram.model.spaceship.crew;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 * 
 * Entity SpaceshipCrews
 *
 */
@Entity
@Table(name = "spaceship_crews")
@IdClass(IdSpaceshipCrews.class)
@ApiObject(name = "SpaceshipCrews", description = "Entity SpaceshipCrews", show = false)
public class SpaceshipCrews implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -5682113778821759934L;
	
	/**
	 * Id Spaceship
	 */
	@Id
	@ApiObjectField(description = "Id Spaceship")
	private Integer idSpaceship;
	
	/**
	 * Id Crew
	 */
	@Id
	@ApiObjectField(description = "Id Crew")
	private Integer idCrew;

	/**
	 * @return the idSpaceship
	 */
	public Integer getIdSpaceship() {
		return idSpaceship;
	}

	/**
	 * @param idSpaceship the idSpaceship to set
	 */
	public void setIdSpaceship(Integer idSpaceship) {
		this.idSpaceship = idSpaceship;
	}

	/**
	 * @return the idCrew
	 */
	public Integer getIdCrew() {
		return idCrew;
	}

	/**
	 * @param idCrew the idCrew to set
	 */
	public void setIdCrew(Integer idCrew) {
		this.idCrew = idCrew;
	}

}
