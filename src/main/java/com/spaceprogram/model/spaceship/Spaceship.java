/**
 * 
 */
package com.spaceprogram.model.spaceship;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spaceprogram.model.coordinates.Coordinates;
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
@Audited
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
	 * Crews
	 */
//	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@JsonInclude(value = Include.NON_EMPTY)
	@OneToMany(fetch = FetchType.LAZY)
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
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
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
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(
            name="spaceship_modules",
            joinColumns = @JoinColumn( name="idSpaceship"),
            inverseJoinColumns = @JoinColumn( name="idModule")
    )
	@ApiObjectField(description = "Modules")
	private List<Module> modules;

	/**
	 * Spaceships
	 */
	@JsonInclude(value = Include.NON_EMPTY)
	@Transient
	@ApiObjectField(description = "Coordinates")
	private Coordinates coordinates;

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
	 * @return the crews
	 */
	public List<Crew> getCrews() {
		return crews;
	}

	/**
	 * @param crews the crews to set
	 */
	public void setCrews(List<Crew> crews) {
//		if (crews != null) {
//			HashSet<Crew> set = new HashSet<Crew>(crews);
//			this.crews = new ArrayList<Crew>(set);
//		}
//		else this.crews = null;
		
		for (int i = 0;i < crews.size();i++)
			for (int j = i+1;j < crews.size();j++)
				if (crews.get(i).getId() == crews.get(j).getId())
					crews.remove(j);
		
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
		
		for (int i = 0;i < engines.size();i++)
			for (int j = i+1;j < engines.size();j++)
				if (engines.get(i).getId() == engines.get(j).getId())
					engines.remove(j);
		
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
		
		for (int i = 0;i < modules.size();i++)
			for (int j = i+1;j < modules.size();j++)
				if (modules.get(i).getId() == modules.get(j).getId())
					modules.remove(j);
		
		this.modules = modules;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

}
