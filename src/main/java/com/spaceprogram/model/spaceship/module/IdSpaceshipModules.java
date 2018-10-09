/**
 * 
 */
package com.spaceprogram.model.spaceship.module;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author GLieutard
 * 
 * Id Entity SpaceshipCrew
 *
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiObject(description = "Id Entity SpaceshipCrew", show = false)
public class IdSpaceshipModules implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -1390552035630340201L;
	
	/**
	 * id Spaceship
	 */
	@ApiObjectField(description = "Id Spaceship")
	private Integer idSpaceship;
	
	/**
	 * Id Crew
	 */
	@ApiObjectField(description = "Id Crew")
	private Integer idModule;

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
	 * @return the idModule
	 */
	public Integer getIdModule() {
		return idModule;
	}

	/**
	 * @param idModule the idModule to set
	 */
	public void setIdModule(Integer idModule) {
		this.idModule = idModule;
	}

}
