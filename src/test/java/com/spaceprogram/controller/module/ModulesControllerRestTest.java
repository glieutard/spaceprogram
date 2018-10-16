/**
 * 
 */
package com.spaceprogram.controller.module;

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
import com.spaceprogram.model.module.Module;
import com.spaceprogram.model.module.type.ModuleType;

/**
 * @author GLieutard
 * 
 * Test Modules Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModulesControllerRestTest {

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
	 * Test getModule
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetModule() throws Exception {

		this.mvc.perform(get("/v1/modules/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Laser canons")))
				.andExpect(jsonPath("$.type.id", is(1)))
				.andExpect(jsonPath("$.firePower", is(15)))
				.andExpect(jsonPath("$.cargoCapacity", is(0)));

		this.mvc.perform(get("/v1/modules/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Deflector shields")))
				.andExpect(jsonPath("$.type.id", is(3)))
				.andExpect(jsonPath("$.firePower", is(0)))
				.andExpect(jsonPath("$.cargoCapacity", is(0)));

		this.mvc.perform(get("/v1/modules/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getModules
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetModules() throws Exception {

	    this.mvc.perform(get("/v1/modules"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(9)))
				.andExpect(jsonPath("$.[0].name", is("Laser canons")))
				.andExpect(jsonPath("$.[0].type.id", is(1)))
				.andExpect(jsonPath("$.[0].firePower", is(15)))
				.andExpect(jsonPath("$.[0].cargoCapacity", is(0)))
	            .andExpect(jsonPath("$.[1].name", is("Deflector shields")))
				.andExpect(jsonPath("$.[1].type.id", is(3)))
				.andExpect(jsonPath("$.[1].firePower", is(0)))
				.andExpect(jsonPath("$.[1].cargoCapacity", is(0)))
	            .andExpect(jsonPath("$.[8].name", is("Corellian Engineering Corporation AG-2G quad laser cannons")));
	}

	/**
	 * Test postModules
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03PostModules() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setName("test insert 1");
		modules.get(0).setType(new ModuleType());
		modules.get(0).getType().setId(1);
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);
		modules.add(new Module());
		modules.get(1).setName("test insert 2");
		modules.get(1).setType(new ModuleType());
		modules.get(1).getType().setId(3);
		modules.get(1).setFirePower(0);
		modules.get(1).setCargoCapacity(0);

		this.mvc.perform(post("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(10)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[1].id", is(11)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")));
		
	}

	/**
	 * Test postModules with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04PostModulesWithId() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(3);
		modules.get(0).setName("test insert 3");
		modules.get(0).setType(new ModuleType());
		modules.get(0).getType().setId(1);
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);
		modules.add(new Module());
		modules.get(1).setName("test insert 4");
		modules.get(1).setType(new ModuleType());
		modules.get(1).getType().setId(3);
		modules.get(1).setFirePower(0);
		modules.get(1).setCargoCapacity(0);

		this.mvc.perform(post("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(12)))
				.andExpect(jsonPath("$.[0].name", is("test insert 4")));
		
	}

	/**
	 * Test postModules without type
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PostModulesWithoutType() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(3);
		modules.get(0).setName("test insert 3");
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);

		this.mvc.perform(post("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postModules with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PostModulesWithNullFields() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setName("test insert 3");

		this.mvc.perform(post("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putModules
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PutModules() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(10);
		modules.get(0).setName("test update 1");
		modules.get(0).setType(new ModuleType());
		modules.get(0).getType().setId(1);
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);
		modules.add(new Module());
		modules.get(1).setId(11);
		modules.get(1).setName("test update 2");
		modules.get(1).setType(new ModuleType());
		modules.get(1).getType().setId(3);
		modules.get(1).setFirePower(0);
		modules.get(1).setCargoCapacity(0);

		this.mvc.perform(put("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(10)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[1].id", is(11)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")));
		
	}

	/**
	 * Test putModules without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08PutModulesWithoutId() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(10);
		modules.get(0).setName("test update 3");
		modules.get(0).setType(new ModuleType());
		modules.get(0).getType().setId(1);
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);
		modules.add(new Module());
		modules.get(1).setName("test update 4");
		modules.get(1).setType(new ModuleType());
		modules.get(1).getType().setId(3);
		modules.get(1).setFirePower(0);
		modules.get(1).setCargoCapacity(0);

		this.mvc.perform(put("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(10)))
				.andExpect(jsonPath("$.[0].name", is("test update 3")));
		
	}

	/**
	 * Test putModules with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09PutModulesWithBadId() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(100);
		modules.get(0).setName("test update 5");
		modules.get(0).setType(new ModuleType());
		modules.get(0).getType().setId(1);
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);

		this.mvc.perform(put("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putModules without type
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10PutModulesWithoutType() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(3);
		modules.get(0).setName("test insert 3");
		modules.get(0).setFirePower(15);
		modules.get(0).setCargoCapacity(0);

		this.mvc.perform(put("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postModules with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11PutModulesWithNullFields() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(3);
		modules.get(0).setName("test insert 3");

		this.mvc.perform(put("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteModules
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12DeleteModules() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(10);
		modules.add(new Module());
		modules.get(1).setId(11);
		modules.add(new Module());
		modules.get(2).setId(12);

		this.mvc.perform(delete("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/modules"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(9)));
		
	}

	/**
	 * Test deleteModules Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13DeleteModulesWithoutId() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/modules"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(9)));
		
	}

	/**
	 * Test deleteModules With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14DeleteModulesWithBadId() throws Exception {

		List<Module> modules = new ArrayList<Module>();
		modules.add(new Module());
		modules.get(0).setId(100);

		this.mvc.perform(delete("/v1/modules").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(modules))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/modules"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(9)));
		
	}

}
