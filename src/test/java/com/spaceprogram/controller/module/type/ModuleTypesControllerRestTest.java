/**
 * 
 */
package com.spaceprogram.controller.module.type;

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
import com.spaceprogram.model.module.type.ModuleType;

/**
 * @author GLieutard
 * 
 * Test ModuleTypes Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModuleTypesControllerRestTest {

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
	 * Test getModuleType
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetModuleType() throws Exception {

		this.mvc.perform(get("/v1/moduleTypes/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("armament")));

		this.mvc.perform(get("/v1/moduleTypes/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("power plant")));

		this.mvc.perform(get("/v1/moduleTypes/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getModuleTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetModuleTypes() throws Exception {

	    this.mvc.perform(get("/v1/moduleTypes"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(6)))
	            .andExpect(jsonPath("$.[0].name", is("armament")))
	            .andExpect(jsonPath("$.[1].name", is("power plant")))
	            .andExpect(jsonPath("$.[2].name", is("shield")))
	            .andExpect(jsonPath("$.[3].name", is("sensor system")))
	            .andExpect(jsonPath("$.[4].name", is("navigation system")))
	            .andExpect(jsonPath("$.[5].name", is("escape craft")));
	}

	/**
	 * Test postModuleTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03PostModuleTypes() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setName("test insert 1");
		moduleTypes.add(new ModuleType());
		moduleTypes.get(1).setName("test insert 2");

		this.mvc.perform(post("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(7)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[1].id", is(8)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")));
		
	}

	/**
	 * Test postModuleTypes with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04PostModuleTypesWithId() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(3);
		moduleTypes.get(0).setName("test insert 3");
		moduleTypes.add(new ModuleType());
		moduleTypes.get(1).setName("test insert 4");

		this.mvc.perform(post("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(9)))
				.andExpect(jsonPath("$.[0].name", is("test insert 4")));
		
	}

	/**
	 * Test putModuleTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PutModuleTypes() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(7);
		moduleTypes.get(0).setName("test update 1");
		moduleTypes.add(new ModuleType());
		moduleTypes.get(1).setId(8);
		moduleTypes.get(1).setName("test update 2");

		this.mvc.perform(put("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(7)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[1].id", is(8)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")));
		
	}

	/**
	 * Test putModuleTypes without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PutModuleTypesWithoutId() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(7);
		moduleTypes.get(0).setName("test update 3");
		moduleTypes.add(new ModuleType());
		moduleTypes.get(1).setName("test update 4");

		this.mvc.perform(put("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(7)))
				.andExpect(jsonPath("$.[0].name", is("test update 3")));
		
	}

	/**
	 * Test putModuleTypes with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PutModuleTypesWithBadId() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(100);
		moduleTypes.get(0).setName("test update 5");

		this.mvc.perform(put("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteModuleTypes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08DeleteModuleTypes() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(7);
		moduleTypes.add(new ModuleType());
		moduleTypes.get(1).setId(8);
		moduleTypes.add(new ModuleType());
		moduleTypes.get(2).setId(9);

		this.mvc.perform(delete("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/moduleTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(6)));
		
	}

	/**
	 * Test deleteModuleTypes Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09DeleteModuleTypesWithoutId() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/moduleTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(6)));
		
	}

	/**
	 * Test deleteModuleTypes With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10DeleteModuleTypesWithBadId() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(100);

		this.mvc.perform(delete("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/moduleTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(6)));
		
	}

	/**
	 * Test deleteModuleTypes With used id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11DeleteModuleTypesWithUsedId() throws Exception {

		List<ModuleType> moduleTypes = new ArrayList<ModuleType>();
		moduleTypes.add(new ModuleType());
		moduleTypes.get(0).setId(1);

		this.mvc.perform(delete("/v1/moduleTypes").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(moduleTypes))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/moduleTypes"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(6)));
		
	}

}
