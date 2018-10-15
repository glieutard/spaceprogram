/**
 * 
 */
package com.spaceprogram.controller.crew;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceprogram.SpaceprogramApplication;
import com.spaceprogram.model.crew.Crew;
import com.spaceprogram.model.job.Job;

/**
 * @author GLieutard
 * 
 * Test Crews Controlelr Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
public class CrewsControllerRestTest {

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
	 * Test getCrew
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCrew() throws Exception {

		this.mvc.perform(get("/v1/crews/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Luke Skywalker")))
				.andExpect(jsonPath("$.age", is(19)))
				.andExpect(jsonPath("$.sexe", is("M")))
				.andExpect(jsonPath("$.job.id", is(1)))
				.andExpect(jsonPath("$.job.name", is("pilote")));

		this.mvc.perform(get("/v1/crews/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Leia Organa")))
				.andExpect(jsonPath("$.age", is(19)))
				.andExpect(jsonPath("$.sexe", is("F")))
				.andExpect(jsonPath("$.job.id", is(5)))
				.andExpect(jsonPath("$.job.name", is("general")));

		this.mvc.perform(get("/v1/crews/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getCrews
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCrews() throws Exception {

	    this.mvc.perform(get("/v1/crews"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(8)))
	            .andExpect(jsonPath("$.[0].name", is("Luke Skywalker")))
				.andExpect(jsonPath("$.[0].age", is(19)))
				.andExpect(jsonPath("$.[0].sexe", is("M")))
				.andExpect(jsonPath("$.[0].job.id", is(1)))
				.andExpect(jsonPath("$.[0].job.name", is("pilote")))
	            .andExpect(jsonPath("$.[1].name", is("Leia Organa")))
				.andExpect(jsonPath("$.[0].age", is(19)))
				.andExpect(jsonPath("$.[0].sexe", is("F")))
				.andExpect(jsonPath("$.[0].job.id", is(5)))
				.andExpect(jsonPath("$.[0].job.name", is("general")))
	            .andExpect(jsonPath("$.[7].name", is("Wedge Antilles")));
	}

	/**
	 * Test postCrews
	 * 
	 * @throws Exception
	 */
	@Test
	public void postCrews() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setName("test insert 1");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);
		crews.add(new Crew());
		crews.get(1).setName("test insert 2");
		crews.get(1).setAge(100);
		crews.get(1).setSexe("M");
		crews.get(1).setJob(new Job());
		crews.get(1).getJob().setId(1);

		this.mvc.perform(post("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(9)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[1].id", is(10)))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")));
		
	}

	/**
	 * Test postCrews with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void postCrewsWithId() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(3);
		crews.get(0).setName("test insert 3");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);
		crews.add(new Crew());
		crews.get(1).setName("test insert 4");
		crews.get(1).setAge(100);
		crews.get(1).setSexe("M");
		crews.get(1).setJob(new Job());
		crews.get(1).getJob().setId(1);

		this.mvc.perform(post("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(11)))
				.andExpect(jsonPath("$.[0].name", is("test insert 4")));
		
	}

	/**
	 * Test postCrews without job
	 * 
	 * @throws Exception
	 */
	@Test
	public void postCrewsWithoutJob() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(3);
		crews.get(0).setName("test insert 3");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(100);
		crews.add(new Crew());
		crews.get(1).setName("test insert 4");
		crews.get(1).setAge(100);
		crews.get(1).setSexe("M");

		this.mvc.perform(post("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postCrews with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void postCrewsWithNullFields() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(3);
		crews.get(0).setName("test insert 3");
		crews.get(0).setAge(100);
		crews.get(0).setSexe(null);
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);

		this.mvc.perform(post("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putCrews
	 * 
	 * @throws Exception
	 */
	@Test
	public void putCrews() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(9);
		crews.get(0).setName("test update 1");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);
		crews.add(new Crew());
		crews.get(1).setId(10);
		crews.get(1).setName("test update 2");
		crews.get(1).setAge(100);
		crews.get(1).setSexe("M");
		crews.get(1).setJob(new Job());
		crews.get(1).getJob().setId(1);

		this.mvc.perform(put("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(9)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[1].id", is(10)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")));
		
	}

	/**
	 * Test putCrews without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void putCrewsWithoutId() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(9);
		crews.get(0).setName("test update 3");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);
		crews.add(new Crew());
		crews.get(1).setName("test update 4");
		crews.get(1).setAge(100);
		crews.get(1).setSexe("M");
		crews.get(1).setJob(new Job());
		crews.get(1).getJob().setId(1);

		this.mvc.perform(put("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(9)))
				.andExpect(jsonPath("$.[0].name", is("test update 3")));
		
	}

	/**
	 * Test putCrews with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void putCrewsWithBadId() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(100);
		crews.get(0).setName("test update 5");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);

		this.mvc.perform(put("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test putCrews without job
	 * 
	 * @throws Exception
	 */
	@Test
	public void putCrewsWithoutJob() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(3);
		crews.get(0).setName("test insert 3");
		crews.get(0).setAge(100);
		crews.get(0).setSexe("M");
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(100);
		crews.add(new Crew());
		crews.get(1).setName("test insert 4");
		crews.get(1).setAge(100);
		crews.get(1).setSexe("M");

		this.mvc.perform(put("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test postCrews with null fields
	 * 
	 * @throws Exception
	 */
	@Test
	public void putCrewsWithNullFields() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(3);
		crews.get(0).setName("test insert 3");
		crews.get(0).setAge(100);
		crews.get(0).setSexe(null);
		crews.get(0).setJob(new Job());
		crews.get(0).getJob().setId(1);

		this.mvc.perform(put("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteCrews
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteCrews() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(9);
		crews.add(new Crew());
		crews.get(1).setId(10);
		crews.add(new Crew());
		crews.get(2).setId(11);

		this.mvc.perform(delete("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk());
		
		this.mvc.perform(get("/v1/crews"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

	/**
	 * Test deleteCrews Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteCrewsWithoutId() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/crews"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

	/**
	 * Test deleteCrews With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteCrewsWithBadId() throws Exception {

		List<Crew> crews = new ArrayList<Crew>();
		crews.add(new Crew());
		crews.get(0).setId(100);

		this.mvc.perform(delete("/v1/crews").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(crews))).andExpect(status().isOk());

		this.mvc.perform(get("/v1/crews"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

}
