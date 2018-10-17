/**
 * 
 */
package com.spaceprogram.controller.mission;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceprogram.SpaceprogramApplication;
import com.spaceprogram.model.coordinates.Coordinates;
import com.spaceprogram.model.mission.Mission;
import com.spaceprogram.model.mission.state.MissionState;
import com.spaceprogram.model.spaceship.Spaceship;

/**
 * @author GLieutard
 * 
 * Test Missions Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MissionsControllerRestTest {

	// Injections
	@Autowired
	private MockMvc mvc;

	// Mapper pour Body
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Initialisation
     */
	@Before
	public void setup() {

	}

	/**
	 * Test getMission
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetMission() throws Exception {

		this.mvc.perform(get("/v1/missions/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Save Princess Leila Organa")))
				.andExpect(jsonPath("$.description", is("Infiltrate the Death Star and save Princess Leila Organa")))
				.andExpect(jsonPath("$.baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.spaceships").doesNotExist());

		this.mvc.perform(get("/v1/missions/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Destroy the Death Star")))
				.andExpect(jsonPath("$.description", is("Use our new weapon to destroy the Death Star.")))
				.andExpect(jsonPath("$.baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.spaceships").doesNotExist());

	}

	/**
	 * Test getMission with option detail == full
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetMissionFull() throws Exception {

		this.mvc.perform(get("/v1/missions/1?detail=full"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Save Princess Leila Organa")))
				.andExpect(jsonPath("$.description", is("Infiltrate the Death Star and save Princess Leila Organa")))
				.andExpect(jsonPath("$.baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.spaceships.[0].id", is(2)))
				.andExpect(jsonPath("$.spaceships.[0].name", is("X-Wing Red Two")))
				.andExpect(jsonPath("$.spaceships.[0].engines[0].id", is(1)));

	}

	/**
	 * Test getMission with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetMissionWithBadId() throws Exception {

		this.mvc.perform(get("/v1/missions/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getMissions
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetMissions() throws Exception {

	    this.mvc.perform(get("/v1/missions"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].name", is("Save Princess Leila Organa")))
				.andExpect(jsonPath("$.[0].description", is("Infiltrate the Death Star and save Princess Leila Organa")))
				.andExpect(jsonPath("$.[0].baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.[0].targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.[0].spaceships").doesNotExist())
				.andExpect(jsonPath("$.[1].name", is("Destroy the Death Star")))
				.andExpect(jsonPath("$.[1].description", is("Use our new weapon to destroy the Death Star.")))
				.andExpect(jsonPath("$.[1].baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.[1].targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.[1].spaceships").doesNotExist());
	}

	/**
	 * Test getMissions with option detail = full
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetMissionsFull() throws Exception {

	    this.mvc.perform(get("/v1/missions?detail=full"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].name", is("Save Princess Leila Organa")))
				.andExpect(jsonPath("$.[0].description", is("Infiltrate the Death Star and save Princess Leila Organa")))
				.andExpect(jsonPath("$.[0].baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.[0].targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.[0].spaceships.[0].id", is(2)))
				.andExpect(jsonPath("$.[0].spaceships.[0].name", is("X-Wing Red Two")))
				.andExpect(jsonPath("$.[0].spaceships.[0].engines[0].id", is(1)))
				.andExpect(jsonPath("$.[1].name", is("Destroy the Death Star")))
				.andExpect(jsonPath("$.[1].description", is("Use our new weapon to destroy the Death Star.")))
				.andExpect(jsonPath("$.[1].baseCoordinates.x", is(1.156)))
				.andExpect(jsonPath("$.[1].targetCoordinates.x", is(11.103)))
				.andExpect(jsonPath("$.[1].spaceships").exists());
	}

	/**
	 * Test postMissions
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03PostMissions() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setName("test insert 1");
		missions.get(0).setDescription("test insert 1");
		missions.get(0).setState(new MissionState());
		missions.get(0).getState().setId(1);
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(0).setSpaceships(new ArrayList<Spaceship>());
		missions.get(0).getSpaceships().add(new Spaceship());
		missions.get(0).getSpaceships().get(0).setId(3);
		missions.add(new Mission());
		missions.get(1).setName("test insert 2");
		missions.get(1).setDescription("test insert 2");
		missions.get(1).setState(new MissionState());
		missions.get(1).getState().setId(1);
		missions.get(1).setBaseCoordinates(new Coordinates());
		missions.get(1).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(1).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(1).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(1).setTargetCoordinates(new Coordinates());
		missions.get(1).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(1).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(1).getTargetCoordinates().setZ(new BigDecimal(30.125));

		this.mvc.perform(post("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(3)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[1].id", is(4)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")));
		
	}

	/**
	 * Test postMissions with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04PostMissionsWithId() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(3);
		missions.get(0).setName("test insert 3");
		missions.get(0).setDescription("test insert 1");
		missions.get(0).setState(new MissionState());
		missions.get(0).getState().setId(1);
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(0).setSpaceships(new ArrayList<Spaceship>());
		missions.get(0).getSpaceships().add(new Spaceship());
		missions.get(0).getSpaceships().get(0).setId(3);

		this.mvc.perform(post("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postMissions without state
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PostMissionsWithoutState() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setName("test insert 3");
		missions.get(0).setDescription("test insert 3");
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(0).setSpaceships(new ArrayList<Spaceship>());
		missions.get(0).getSpaceships().add(new Spaceship());
		missions.get(0).getSpaceships().get(0).setId(3);
		missions.add(new Mission());
		missions.get(1).setName("test insert 4");
		missions.get(1).setDescription("test insert 4");
		missions.get(1).setState(new MissionState());
		missions.get(1).setBaseCoordinates(new Coordinates());
		missions.get(1).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(1).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(1).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(1).setTargetCoordinates(new Coordinates());
		missions.get(1).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(1).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(1).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(1).setSpaceships(new ArrayList<Spaceship>());
		missions.get(1).getSpaceships().add(new Spaceship());
		missions.get(1).getSpaceships().get(0).setId(3);

		this.mvc.perform(post("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postMissions with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PostMissionsWithNullFields() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setName("");

		this.mvc.perform(post("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postMissions with spaceship in mission
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PostMissionsWithSpaceshipInMission() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setName("test insert spaceship in mission");
		missions.get(0).setDescription("test insert spaceship in mission");
		missions.get(0).setState(new MissionState());
		missions.get(0).getState().setId(1);
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(0).setSpaceships(new ArrayList<Spaceship>());
		missions.get(0).getSpaceships().add(new Spaceship());
		missions.get(0).getSpaceships().get(0).setId(3);

		this.mvc.perform(post("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test insert spaceship in mission")))
				.andExpect(jsonPath("$.[0].spaceships").doesNotExist());
		
	}

	/**
	 * Test putMissions
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PutMissions() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(3);
		missions.get(0).setName("test update 1");
		missions.get(0).setDescription("test update 1");
		missions.get(0).setState(new MissionState());
		missions.get(0).getState().setId(4);
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(0).setSpaceships(new ArrayList<Spaceship>());
		missions.get(0).getSpaceships().add(new Spaceship());
		missions.get(0).getSpaceships().get(0).setId(3);
		missions.add(new Mission());
		missions.get(1).setId(4);
		missions.get(1).setName("test update 2");
		missions.get(1).setDescription("test update 2");
		missions.get(1).setState(new MissionState());
		missions.get(1).getState().setId(1);
		missions.get(1).setBaseCoordinates(new Coordinates());
		missions.get(1).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(1).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(1).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(1).setTargetCoordinates(new Coordinates());
		missions.get(1).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(1).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(1).getTargetCoordinates().setZ(new BigDecimal(30.125));
		missions.get(1).setSpaceships(new ArrayList<Spaceship>());
		missions.get(1).getSpaceships().add(new Spaceship());
		missions.get(1).getSpaceships().get(0).setId(3);

		this.mvc.perform(put("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(3)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[0].spaceships").doesNotExist())
				.andExpect(jsonPath("$.[1].id", is(4)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")));
		
	}

	/**
	 * Test putMissions without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08PutMissionsWithoutId() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setName("test update 3");
		missions.get(0).setDescription("test update 3");
		missions.get(0).setState(new MissionState());
		missions.get(0).getState().setId(4);
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));

		this.mvc.perform(put("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putMissions with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09PutMissionsWithBadId() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(100);
		missions.get(0).setName("test update 4");
		missions.get(0).setDescription("test update 4");
		missions.get(0).setState(new MissionState());
		missions.get(0).getState().setId(4);
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));

		this.mvc.perform(put("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putMissions without state
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10PutMissionsWithoutState() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(3);
		missions.get(0).setName("test update 5");
		missions.get(0).setDescription("test update 5");
		missions.get(0).setBaseCoordinates(new Coordinates());
		missions.get(0).getBaseCoordinates().setX(new BigDecimal(1.125));
		missions.get(0).getBaseCoordinates().setY(new BigDecimal(2.125));
		missions.get(0).getBaseCoordinates().setZ(new BigDecimal(3.125));
		missions.get(0).setTargetCoordinates(new Coordinates());
		missions.get(0).getTargetCoordinates().setX(new BigDecimal(10.125));
		missions.get(0).getTargetCoordinates().setY(new BigDecimal(20.125));
		missions.get(0).getTargetCoordinates().setZ(new BigDecimal(30.125));

		this.mvc.perform(put("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postMissions with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11PutMissionsWithNullFields() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(3);
		missions.get(0).setName("");

		this.mvc.perform(put("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteMissions
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12DeleteMissions() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(3);
		missions.add(new Mission());
		missions.get(1).setId(4);
		missions.add(new Mission());
		missions.get(2).setId(5);

		this.mvc.perform(delete("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/missions"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(2)));
		
	}

	/**
	 * Test deleteMissions Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13DeleteMissionsWithoutId() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/missions"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(2)));
		
	}

	/**
	 * Test deleteMissions With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14DeleteMissionsWithBadId() throws Exception {

		List<Mission> missions = new ArrayList<Mission>();
		missions.add(new Mission());
		missions.get(0).setId(100);

		this.mvc.perform(delete("/v1/missions").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(missions))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/missions"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(2)));
		
	}

	/**
	 * Test getSpaceshipsByMission
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15GetSpaceshipsByMission() throws Exception {

		this.mvc.perform(get("/v1/missions/1/spaceships"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(2)))
				.andExpect(jsonPath("$.[0].name", is("X-Wing Red Two")))
				.andExpect(jsonPath("$.[0].coordinates").doesNotExist());

	}

	/**
	 * Test getSpaceshipsByMission with detail == full
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16GetSpaceshipsByMissionFull() throws Exception {

		this.mvc.perform(get("/v1/missions/1/spaceships?detail=full"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(2)))
				.andExpect(jsonPath("$.[0].name", is("X-Wing Red Two")))
				.andExpect(jsonPath("$.[0].coordinates").exists());

	}

	/**
	 * Test getCoordinatesByMissionSpaceship with detail == full
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17GetCoordinatesByMissionSpaceship() throws Exception {

		this.mvc.perform(get("/v1/missions/1/spaceships/2/coordinates"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists());

	}

}
