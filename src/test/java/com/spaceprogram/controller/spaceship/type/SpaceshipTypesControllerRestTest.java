/**
 * 
 */
package com.spaceprogram.controller.spaceship.type;

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
import com.spaceprogram.model.spaceship.type.SpaceshipType;

/**
 * @author GLieutard
 * 
 * Test SpaceshipTypes Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpaceshipTypesControllerRestTest {

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
	 * Test getSpaceshipType
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetSpaceshipType() throws Exception {

		this.mvc.perform(get("/v1/spaceshipTypes/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("X-Wing")))
				.andExpect(jsonPath("$.width", is(1140)))
				.andExpect(jsonPath("$.length", is(1250)))
				.andExpect(jsonPath("$.height", is(230)))
				.andExpect(jsonPath("$.weight", is(10)))
				.andExpect(jsonPath("$.cargoCapacity", is(0)));

		this.mvc.perform(get("/v1/spaceshipTypes/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Y-Wing")))
				.andExpect(jsonPath("$.width", is(800)))
				.andExpect(jsonPath("$.length", is(1600)))
				.andExpect(jsonPath("$.height", is(230)))
				.andExpect(jsonPath("$.weight", is(10)))
				.andExpect(jsonPath("$.cargoCapacity", is(0)));

		this.mvc.perform(get("/v1/spaceshipTypes/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getSpaceshipTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetSpaceshipTypes() throws Exception {

	    this.mvc.perform(get("/v1/spaceshipTypes"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(4)))
	            .andExpect(jsonPath("$.[0].name", is("X-Wing")))
				.andExpect(jsonPath("$.[0].width", is(1140)))
				.andExpect(jsonPath("$.[0].length", is(1250)))
				.andExpect(jsonPath("$.[0].height", is(230)))
				.andExpect(jsonPath("$.[0].weight", is(10)))
				.andExpect(jsonPath("$.[0].cargoCapacity", is(0)))
	            .andExpect(jsonPath("$.[1].name", is("Y-Wing")))
				.andExpect(jsonPath("$.[1].width", is(800)))
				.andExpect(jsonPath("$.[1].length", is(1600)))
				.andExpect(jsonPath("$.[1].height", is(230)))
				.andExpect(jsonPath("$.[1].weight", is(10)))
				.andExpect(jsonPath("$.[1].cargoCapacity", is(0)))
	            .andExpect(jsonPath("$.[2].name", is("Light Cargo")))
	            .andExpect(jsonPath("$.[3].name", is("A-Wing")));
	}

	/**
	 * Test postSpaceshipTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03PostSpaceshipTypes() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setName("test insert 1");
		spaceshipTypes.get(0).setWidth(100);
		spaceshipTypes.get(0).setLength(100);
		spaceshipTypes.get(0).setHeight(100);
		spaceshipTypes.get(0).setWeight(10);
		spaceshipTypes.get(0).setCargoCapacity(0);
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(1).setName("test insert 2");
		spaceshipTypes.get(1).setWidth(100);
		spaceshipTypes.get(1).setLength(200);
		spaceshipTypes.get(1).setHeight(200);
		spaceshipTypes.get(1).setWeight(20);
		spaceshipTypes.get(1).setCargoCapacity(0);

		this.mvc.perform(post("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[0].width", is(100)))
				.andExpect(jsonPath("$.[0].length", is(100)))
				.andExpect(jsonPath("$.[0].height", is(100)))
				.andExpect(jsonPath("$.[0].weight", is(10)))
				.andExpect(jsonPath("$.[0].cargoCapacity", is(0)))
				.andExpect(jsonPath("$.[1].id", is(6)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")))
				.andExpect(jsonPath("$.[1].width", is(100)))
				.andExpect(jsonPath("$.[1].length", is(200)))
				.andExpect(jsonPath("$.[1].height", is(200)))
				.andExpect(jsonPath("$.[1].weight", is(20)))
				.andExpect(jsonPath("$.[1].cargoCapacity", is(0)));
		
	}

	/**
	 * Test postSpaceshipTypes with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04PostSpaceshipTypesWithId() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(3);
		spaceshipTypes.get(0).setName("test insert 3");
		spaceshipTypes.get(0).setWidth(100);
		spaceshipTypes.get(0).setLength(100);
		spaceshipTypes.get(0).setHeight(100);
		spaceshipTypes.get(0).setWeight(10);
		spaceshipTypes.get(0).setCargoCapacity(0);
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(1).setName("test insert 4");
		spaceshipTypes.get(1).setWidth(100);
		spaceshipTypes.get(1).setLength(200);
		spaceshipTypes.get(1).setHeight(200);
		spaceshipTypes.get(1).setWeight(20);
		spaceshipTypes.get(1).setCargoCapacity(0);

		this.mvc.perform(post("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(7)))
				.andExpect(jsonPath("$.[0].name", is("test insert 4")))
				.andExpect(jsonPath("$.[0].width", is(100)))
				.andExpect(jsonPath("$.[0].length", is(200)))
				.andExpect(jsonPath("$.[0].height", is(200)))
				.andExpect(jsonPath("$.[0].weight", is(20)))
				.andExpect(jsonPath("$.[0].cargoCapacity", is(0)));
		
	}

	/**
	 * Test putSpaceshipTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PutSpaceshipTypes() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(5);
		spaceshipTypes.get(0).setName("test update 1");
		spaceshipTypes.get(0).setWidth(100);
		spaceshipTypes.get(0).setLength(100);
		spaceshipTypes.get(0).setHeight(100);
		spaceshipTypes.get(0).setWeight(10);
		spaceshipTypes.get(0).setCargoCapacity(0);
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(1).setId(6);
		spaceshipTypes.get(1).setName("test update 2");
		spaceshipTypes.get(1).setWidth(100);
		spaceshipTypes.get(1).setLength(200);
		spaceshipTypes.get(1).setHeight(200);
		spaceshipTypes.get(1).setWeight(20);
		spaceshipTypes.get(1).setCargoCapacity(0);

		this.mvc.perform(put("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[1].id", is(6)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")));
		
	}

	/**
	 * Test putSpaceshipTypes without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PutSpaceshipTypesWithoutId() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(5);
		spaceshipTypes.get(0).setName("test update 3");
		spaceshipTypes.get(0).setWidth(100);
		spaceshipTypes.get(0).setLength(100);
		spaceshipTypes.get(0).setHeight(100);
		spaceshipTypes.get(0).setWeight(10);
		spaceshipTypes.get(0).setCargoCapacity(0);
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(1).setName("test update 4");
		spaceshipTypes.get(1).setWidth(100);
		spaceshipTypes.get(1).setLength(200);
		spaceshipTypes.get(1).setHeight(200);
		spaceshipTypes.get(1).setWeight(20);
		spaceshipTypes.get(1).setCargoCapacity(0);

		this.mvc.perform(put("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(5)))
				.andExpect(jsonPath("$.[0].name", is("test update 3")));
		
	}

	/**
	 * Test putSpaceshipTypes with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PutSpaceshipTypesWithBadId() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(100);
		spaceshipTypes.get(0).setName("test update 5");
		spaceshipTypes.get(0).setWidth(100);
		spaceshipTypes.get(0).setLength(100);
		spaceshipTypes.get(0).setHeight(100);
		spaceshipTypes.get(0).setWeight(10);
		spaceshipTypes.get(0).setCargoCapacity(0);

		this.mvc.perform(put("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteSpaceshipTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08DeleteSpaceshipTypes() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(5);
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(1).setId(6);
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(2).setId(7);

		this.mvc.perform(delete("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/spaceshipTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

	/**
	 * Test deleteSpaceshipTypes Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09DeleteSpaceshipTypesWithoutId() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/spaceshipTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

	/**
	 * Test deleteSpaceshipTypes With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10DeleteSpaceshipTypesWithBadId() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(100);

		this.mvc.perform(delete("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/spaceshipTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

	/**
	 * Test deleteSpaceshipTypes With used id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11DeleteSpaceshipTypesWithUsedId() throws Exception {

		List<SpaceshipType> spaceshipTypes = new ArrayList<SpaceshipType>();
		spaceshipTypes.add(new SpaceshipType());
		spaceshipTypes.get(0).setId(1);

		this.mvc.perform(delete("/v1/spaceshipTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(spaceshipTypes))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/spaceshipTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(4)));
		
	}

}
