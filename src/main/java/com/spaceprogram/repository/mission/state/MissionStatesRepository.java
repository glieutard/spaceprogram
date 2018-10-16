/**
 * 
 */
package com.spaceprogram.repository.mission.state;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.mission.state.MissionState;

/**
 * @author GLieutard
 * 
 * MissionStates Repository
 *
 */
@Transactional
public interface MissionStatesRepository extends CrudRepository<MissionState, Integer> {

	/**
	 * Count by id
	 */
	Long countById(Integer id);
	
}
