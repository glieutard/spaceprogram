/**
 * 
 */
package com.spaceprogram.model.crew;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.spaceprogram.model.job.Job;

/**
 * @author GLieutard
 * 
 * Entity Crew
 *
 */
@Entity
@ApiObject(name = "Crew", description = "Entity Crew")
public class Crew implements Serializable {

	/**
	 * Id de version
	 */
	private static final long serialVersionUID = -7434230304121269735L;
	
	/**
	 * Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiObjectField(description = "id")
	private Integer id;
	
	/**
	 * Name
	 */
	@ApiObjectField(description = "Name")
	private String name;
	
	/**
	 * Age
	 */
	@ApiObjectField(description = "Age")
	private Integer age;
	
	/**
	 * Sexe
	 */
	@ApiObjectField(description = "Sexe")
	private String sexe;
	
	/**
	 * Job
	 */
	@OneToOne
    @JoinColumn(name = "idJob", referencedColumnName = "id")
	@ApiObjectField(description = "Job")
	private Job job;
	
//	/**
//	 * Detail
//	 */
//    @OneToMany(fetch=FetchType.LAZY)
//    @JoinColumn(name = "FDCECH", referencedColumnName = "FECECH")
//    @OrderBy("FDNBJO DESC")
//	@ApiObjectField(description = "détail")
//	private List<BillBookDetail> detail;

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
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

}
