/**
 * 
 */
package com.spaceprogram.controller.engine;

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
import com.spaceprogram.model.engine.Engine;

/**
 * @author GLieutard
 * 
 * Test Engines Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EnginesControllerRestTest {

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
	 * Test getEngine
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetEngine() throws Exception {

		this.mvc.perform(get("/v1/engines/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Hyperdrive")))
				.andExpect(jsonPath("$.horsePower", is(0)))
				.andExpect(jsonPath("$.weight", is(2)));

		this.mvc.perform(get("/v1/engines/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Incom 4L4 fusial thrust engines")))
				.andExpect(jsonPath("$.horsePower", is(750)))
				.andExpect(jsonPath("$.weight", is(4)));

		this.mvc.perform(get("/v1/engines/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getEngines
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetEngines() throws Exception {

	    this.mvc.perform(get("/v1/engines"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(4)))
	            .andExpect(jsonPath("$.[0].name", is("Hyperdrive")))
				.andExpect(jsonPath("$.[0].horsePower", is(0)))
				.andExpect(jsonPath("$.[0].weight", is(2)))
	            .andExpect(jsonPath("$.[1].name", is("Incom 4L4 fusial thrust engines")))
				.andExpect(jsonPath("$.[1].horsePower", is(750)))
				.andExpect(jsonPath("$.[1].weight", is(4)))
	            .andExpect(jsonPath("$.[2].name", is("Novaldex K-88 Event Horizon sublight engines")))
	            .andExpect(jsonPath("$.[3].name", is("Girodyne SRB42 sublight engines")));
	}

	/**
	 * Test postEngines
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03PostEngines() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setName("test insert 1");
		engines.get(0).setHorsePower(0);
		engines.get(0).setWeight(10);
		engines.add(new Engine());
		engines.get(1).setName("test insert 2");
		engines.get(1).setHorsePower(10);
		engines.get(1).setWeight(10);

		this.mvc.perform(post("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[0].horsePower", is(0)))
				.andExpect(jsonPath("$.[0].weight", is(10)))
				.andExpect(jsonPath("$.[1].id", is(6)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")))
				.andExpect(jsonPath("$.[1].horsePower", is(10)))
				.andExpect(jsonPath("$.[1].weight", is(10)));
		
	}

	/**
	 * Test postEngines with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04PostEnginesWithId() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(3);
		engines.get(0).setName("test insert 3");
		engines.get(0).setHorsePower(0);
		engines.get(0).setWeight(10);
		engines.add(new Engine());
		engines.get(1).setName("test insert 4");
		engines.get(1).setHorsePower(0);
		engines.get(1).setWeight(10);

		this.mvc.perform(post("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(7)))
				.andExpect(jsonPath("$.[0].name", is("test insert 4")));
		
	}

	/**
	 * Test putEngines
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PutEngines() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(5);
		engines.get(0).setName("test update 1");
		engines.get(0).setHorsePower(0);
		engines.get(0).setWeight(10);
		engines.add(new Engine());
		engines.get(1).setId(6);
		engines.get(1).setName("test update 2");
		engines.get(1).setHorsePower(10);
		engines.get(1).setWeight(10);

		this.mvc.perform(put("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[0].horsePower", is(0)))
				.andExpect(jsonPath("$.[0].weight", is(10)))
				.andExpect(jsonPath("$.[1].id", is(6)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")))
				.andExpect(jsonPath("$.[1].horsePower", is(10)))
				.andExpect(jsonPath("$.[1].weight", is(10)));
		
	}

	/**
	 * Test putEngines without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PutEnginesWithoutId() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(5);
		engines.get(0).setName("test update 3");
		engines.get(0).setHorsePower(0);
		engines.get(0).setWeight(10);
		engines.add(new Engine());
		engines.get(1).setName("test update 4");
		engines.get(1).setHorsePower(0);
		engines.get(1).setWeight(10);

		this.mvc.perform(put("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test update 3")));
		
	}

	/**
	 * Test putEngines with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PutEnginesWithBadId() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(100);
		engines.get(0).setName("test update 5");
		engines.get(0).setHorsePower(0);
		engines.get(0).setWeight(10);

		this.mvc.perform(put("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteEngines
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08DeleteEngines() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(5);
		engines.add(new Engine());
		engines.get(1).setId(6);
		engines.add(new Engine());
		engines.get(2).setId(7);

		this.mvc.perform(delete("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/engines"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

	/**
	 * Test deleteEngines Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09DeleteEnginesWithoutId() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/engines"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

	/**
	 * Test deleteEngines With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10DeleteEnginesWithBadId() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(100);

		this.mvc.perform(delete("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/engines"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

	/**
	 * Test deleteEngines With used id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11DeleteEnginesWithUsedId() throws Exception {

		List<Engine> engines = new ArrayList<Engine>();
		engines.add(new Engine());
		engines.get(0).setId(1);

		this.mvc.perform(delete("/v1/engines").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(engines))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/engines"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

}
