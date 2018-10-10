/**
 * 
 */
package com.spaceprogram.model.mission.spaceship;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author GLieutard
 * 
 * Id Entity MissionSpaceships
 *
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiObject(description = "Id Entity MissionSpaceships", show = false)
public class IdMissionSpaceships implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = 4135473961343147176L;

	/**
	 * Id Mission
	 */
	@Id
	@ApiObjectField(description = "Id Mission")
	private Integer idMission;
	
	/**
	 * Id Spaceship
	 */
	@Id
	@ApiObjectField(description = "Id Spaceship")
	private Integer idSpaceship;

	/**
	 * @return the idMission
	 */
	public Integer getIdMission() {
		return idMission;
	}

	/**
	 * @param idMission the idMission to set
	 */
	public void setIdMission(Integer idMission) {
		this.idMission = idMission;
	}

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
	
}
