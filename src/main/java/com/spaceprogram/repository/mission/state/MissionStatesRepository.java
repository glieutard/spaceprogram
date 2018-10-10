/**
 * 
 */
package com.spaceprogram.repository.mission.state;

import org.springframework.data.repository.CrudRepository;

import com.spaceprogram.model.mission.state.MissionState;

/**
 * @author GLieutard
 * 
 * MissionStates Repository
 *
 */
public interface MissionStatesRepository extends CrudRepository<MissionState, Integer> {

}
