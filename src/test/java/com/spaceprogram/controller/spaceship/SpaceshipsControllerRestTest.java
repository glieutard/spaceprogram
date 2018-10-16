/**
 * 
 */
package com.spaceprogram.controller.spaceship;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.model.engine.Engine;
import com.spaceprogram.model.module.Module;
import com.spaceprogram.model.spaceship.Spaceship;
import com.spaceprogram.model.spaceship.type.SpaceshipType;

/**
 * @author GLieutard
 * 
 * Test Spaceships Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpaceshipsControllerRestTest {

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
	 * Test getSpaceship
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetSpaceship() throws Exception {

		this.mvc.perform(get("/v1/spaceships/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("X-Wing Red Five")))
				.andExpect(jsonPath("$.type.id", is(1)))
				.andExpect(jsonPath("$.type.name", is("X-Wing")))
				.andExpect(jsonPath("$.crews").doesNotExist())
				.andExpect(jsonPath("$.engines").doesNotExist())
				.andExpect(jsonPath("$.modules").doesNotExist());

		this.mvc.perform(get("/v1/spaceships/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("X-Wing Red Two")))
				.andExpect(jsonPath("$.type.id", is(1)))
				.andExpect(jsonPath("$.type.name", is("X-Wing")))
				.andExpect(jsonPath("$.crews").doesNotExist())
				.andExpect(jsonPath("$.engines").doesNotExist())
				.andExpect(jsonPath("$.modules").doesNotExist());

		this.mvc.perform(get("/v1/spaceships/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}

	/**
	 * Test getSpaceship option detail == full
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetSpaceshipFull() throws Exception {

		this.mvc.perform(get("/v1/spaceships/1?detail=full"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("X-Wing Red Five")))
				.andExpect(jsonPath("$.type.id", is(1)))
				.andExpect(jsonPath("$.type.name", is("X-Wing")))
				.andExpect(jsonPath("$.crews.[0].id", is(1)))
				.andExpect(jsonPath("$.engines.[0].id", is(1)))
				.andExpect(jsonPath("$.modules.[0].id", is(1)));

	}

	/**
	 * Test getSpaceships
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03GetSpaceships() throws Exception {

	    this.mvc.perform(get("/v1/spaceships"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(3)))
				.andExpect(jsonPath("$.[0].name", is("X-Wing Red Five")))
				.andExpect(jsonPath("$.[0].type.id", is(1)))
				.andExpect(jsonPath("$.[0].type.name", is("X-Wing")))
				.andExpect(jsonPath("$.[0].crews").doesNotExist())
				.andExpect(jsonPath("$.[0].engines").doesNotExist())
				.andExpect(jsonPath("$.[0].modules").doesNotExist())
				.andExpect(jsonPath("$.[1].name", is("X-Wing Red Two")))
				.andExpect(jsonPath("$.[1].type.id", is(1)))
				.andExpect(jsonPath("$.[1].type.name", is("X-Wing")))
				.andExpect(jsonPath("$.[1].crews").doesNotExist())
				.andExpect(jsonPath("$.[1].engines").doesNotExist())
				.andExpect(jsonPath("$.[1].modules").doesNotExist());
	}
	
	/**
	 * Test getSpaceships option detail == full
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04GetSpaceshipsFull() throws Exception {

	    this.mvc.perform(get("/v1/spaceships?detail=full"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(3)))
				.andExpect(jsonPath("$.[0].name", is("X-Wing Red Five")))
				.andExpect(jsonPath("$.[0].type.id", is(1)))
				.andExpect(jsonPath("$.[0].type.name", is("X-Wing")))
				.andExpect(jsonPath("$.[0].crews.[0].id", is(1)))
				.andExpect(jsonPath("$.[0].engines.[0].id", is(1)))
				.andExpect(jsonPath("$.[0].modules.[0].id", is(1)));
	}

	/**
	 * Test postSpaceships
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PostSpaceships() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setName("test insert 1");
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);
		spaceships.get(0).setEngines(new ArrayList<Engine>());
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(1);;
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(1).setId(2);;
		spaceships.add(new Spaceship());
		spaceships.get(1).setName("test insert 2");
		spaceships.get(1).setType(new SpaceshipType());
		spaceships.get(1).getType().setId(1);
		spaceships.get(1).setEngines(new ArrayList<Engine>());
		spaceships.get(1).getEngines().add(new Engine());
		spaceships.get(1).getEngines().get(0).setId(1);;
		spaceships.get(1).getEngines().add(new Engine());
		spaceships.get(1).getEngines().get(1).setId(2);;
		spaceships.get(1).setCrews(new ArrayList<Crew>());
		spaceships.get(1).getCrews().add(new Crew());
		spaceships.get(1).getCrews().get(0).setId(6);
		spaceships.get(1).setModules(new ArrayList<Module>());
		spaceships.get(1).getModules().add(new Module());
		spaceships.get(1).getModules().get(0).setId(1);

		this.mvc.perform(post("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(4)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[1].id", is(5)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")));
		
	}

	/**
	 * Test postSpaceships with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PostSpaceshipsWithId() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(3);
		spaceships.get(0).setName("test insert 3");
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);
		spaceships.get(0).setEngines(new ArrayList<Engine>());
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(1);;
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(2);;

		this.mvc.perform(post("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postSpaceships without type
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PostSpaceshipsWithoutType() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setName("test insert 3");
		spaceships.get(0).setEngines(new ArrayList<Engine>());
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(1);;
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(2);;

		this.mvc.perform(post("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postSpaceships with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08PostSpaceshipsWithNullFields() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);
		spaceships.get(0).setEngines(new ArrayList<Engine>());
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(1);;
		spaceships.get(0).getEngines().add(new Engine());
		spaceships.get(0).getEngines().get(0).setId(2);;

		this.mvc.perform(post("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putSpaceships
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09PutSpaceships() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(4);
		spaceships.get(0).setName("test update 1");
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);
		spaceships.add(new Spaceship());
		spaceships.get(1).setId(5);
		spaceships.get(1).setName("test update 2");
		spaceships.get(1).setType(new SpaceshipType());
		spaceships.get(1).getType().setId(1);

		this.mvc.perform(put("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(4)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[1].id", is(5)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")))
				.andExpect(jsonPath("$.[1].engines.[0].id", is(1)))
				.andExpect(jsonPath("$.[1].engines.[1].id", is(2)))
				.andExpect(jsonPath("$.[1].crews.[0].id", is(6)))
				.andExpect(jsonPath("$.[1].modules.[0].id", is(1)));
		
	}

	/**
	 * Test putSpaceships without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10PutSpaceshipsWithoutId() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setName("test update 3");
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);

		this.mvc.perform(put("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putSpaceships with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11PutSpaceshipsWithBadId() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(100);
		spaceships.get(0).setName("test update 5");
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);

		this.mvc.perform(put("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putSpaceships without type
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12PutSpaceshipsWithoutType() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(3);
		spaceships.get(0).setName("test insert 3");

		this.mvc.perform(put("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postSpaceships with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13PutSpaceshipsWithNullFields() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(3);
		spaceships.get(0).setType(new SpaceshipType());
		spaceships.get(0).getType().setId(1);

		this.mvc.perform(put("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteSpaceships
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14DeleteSpaceships() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(4);
		spaceships.add(new Spaceship());
		spaceships.get(1).setId(5);

		this.mvc.perform(delete("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/spaceships"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(3)));
		
	}

	/**
	 * Test deleteSpaceships Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15DeleteSpaceshipsWithoutId() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/spaceships"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(3)));
		
	}

	/**
	 * Test deleteSpaceships With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16DeleteSpaceshipsWithBadId() throws Exception {

		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		spaceships.add(new Spaceship());
		spaceships.get(0).setId(100);

		this.mvc.perform(delete("/v1/spaceships").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceships))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/spaceships"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(3)));
		
	}

	/**
	 * Test getCrewsBySpaceship
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17GetCrewsBySpaceship() throws Exception {

	    this.mvc.perform(get("/v1/spaceships/1/crews"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].name", is("Luke Skywalker")))
				.andExpect(jsonPath("$.[0].age", is(19)))
				.andExpect(jsonPath("$.[0].sexe", is("M")))
				.andExpect(jsonPath("$.[0].job.id", is(1)))
				.andExpect(jsonPath("$.[0].job.name", is("pilote")))
				.andExpect(jsonPath("$.[1].name", is("R2D2")));
	}

	/**
	 * Test getEnginesBySpaceship
	 * 
	 * @throws Exception
	 */
	@Test
	public void test18GetEnginesBySpaceship() throws Exception {

	    this.mvc.perform(get("/v1/spaceships/1/engines"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].name", is("Hyperdrive")))
				.andExpect(jsonPath("$.[0].horsePower", is(0)))
				.andExpect(jsonPath("$.[0].weight", is(2)))
				.andExpect(jsonPath("$.[1].name", is("Incom 4L4 fusial thrust engines")));
	}

	/**
	 * Test getModulesBySpaceship
	 * 
	 * @throws Exception
	 */
	@Test
	public void test19GetModulesBySpaceship() throws Exception {

	    this.mvc.perform(get("/v1/spaceships/1/modules"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].name", is("Laser canons")))
				.andExpect(jsonPath("$.[0].firePower", is(15)))
				.andExpect(jsonPath("$.[0].cargoCapacity", is(0)))
				.andExpect(jsonPath("$.[1].name", is("Deflector shields")));
	}

	/**
	 * Test getHistoryBySpaceship
	 * 
	 * @throws Exception
	 */
	@Test
	public void test20GetHistoryBySpaceship() throws Exception {

	    this.mvc.perform(get("/v1/spaceships/1/history"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].name", is("X-Wing Red Five")))
				.andExpect(jsonPath("$.[0].type.id", is(1)))
				.andExpect(jsonPath("$.[0].crews.[0].id", is(1)))
				.andExpect(jsonPath("$.[0].crews.[1].id", is(5)))
				.andExpect(jsonPath("$.[0].engines.[0].id", is(1)))
				.andExpect(jsonPath("$.[0].engines.[1].id", is(2)))
				.andExpect(jsonPath("$.[0].modules.[0].id", is(1)))
				.andExpect(jsonPath("$.[0].modules.[1].id", is(2)));
	}
	
}
