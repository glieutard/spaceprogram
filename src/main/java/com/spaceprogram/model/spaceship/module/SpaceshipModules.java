/**
 * 
 */
package com.spaceprogram.model.spaceship.module;

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
 * Entity SpaceshipModules
 *
 */
@Entity
@Table(name = "spaceship_modules")
@IdClass(IdSpaceshipModules.class)
@ApiObject(name = "SpaceshipModules", description = "Entity SpaceshipModules", show = false)
public class SpaceshipModules implements Serializable {

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
	 * Id Module
	 */
	@Id
	@ApiObjectField(description = "Id Module")
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
