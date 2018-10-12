/**
 * 
 */
package com.spaceprogram.model.mission.spaceship;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author GLieutard
 * 
 * Entity MissionSpaceships
 *
 */
@Entity
@Table(name = "mission_spaceships")
@IdClass(IdMissionSpaceships.class)
@ApiObject(name = "MissionSpaceships", description = "Entity MissionSpaceships", show = false)
public class MissionSpaceships implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = 5842251820686806004L;
	
	/**
	 * Constructeurs
	 */
	public MissionSpaceships () {}
	public MissionSpaceships (Integer idMission, Integer idSpaceship, Date date) {
		this.idMission = idMission;
		this.idSpaceship = idSpaceship;
		this.date = date;
	}

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
	 * Date
	 */
	@ApiObjectField(description = "Date")
	private Date date;

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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
