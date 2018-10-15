/**
 * 
 */
package com.spaceprogram.controller.mission.state;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spaceprogram.SpaceprogramApplication;

/**
 * @author GLieutard
 * 
 * Test MissionStates Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
public class MissionStatesControllerRestTest {

	// Injections
	@Autowired
	private MockMvc mvc;

    /**
     * Initialisation
     */
	@Before
	public void setup() {

	}

	/**
	 * Test getState
	 * 
	 * @throws Exception
	 */
	@Test
	public void getState() throws Exception {

		this.mvc.perform(get("/v1/missionStates/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("backlog")));

		this.mvc.perform(get("/v1/missionStates/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("in progress")));
		
		this.mvc.perform(get("/v1/missionStates/5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getStates
	 * 
	 * @throws Exception
	 */
	@Test
	public void getStates() throws Exception {

	    this.mvc.perform(get("/v1/missionStates"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(4)))
	            .andExpect(jsonPath("$.[0].name", is("backlog")))
	            .andExpect(jsonPath("$.[1].name", is("in progress")))
	            .andExpect(jsonPath("$.[2].name", is("failed")))
	            .andExpect(jsonPath("$.[3].name", is("accomplished")));
	}

}
