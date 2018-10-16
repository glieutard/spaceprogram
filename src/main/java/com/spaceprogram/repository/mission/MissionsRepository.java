/**
 * 
 */
package com.spaceprogram.repository.mission;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.mission.Mission;

/**
 * @author GLieutard
 * 
 * Missions Repository
 *
 */
@Transactional
public interface MissionsRepository extends CrudRepository<Mission, Integer> {

	/**
	 * Count by id
	 */
	Long countById(Integer id);

}
