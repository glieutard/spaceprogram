/**
 * 
 */
package com.spaceprogram.controller.job;

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
import com.spaceprogram.model.job.Job;

/**
 * @author GLieuatard
 * 
 * Test Jobs Controller Rest
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobsControllerRestTest {

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
	 * Test getJob
	 * 
	 * @throws Exception
	 */
	@Test
	public void test01GetJob() throws Exception {

		this.mvc.perform(get("/v1/jobs/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("pilote")));

		this.mvc.perform(get("/v1/jobs/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("copilote")));

		this.mvc.perform(get("/v1/jobs/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").doesNotExist());

	}
	
	/**
	 * Test getJobs
	 * 
	 * @throws Exception
	 */
	@Test
	public void test02GetJobs() throws Exception {

//	    given(this.jobsControllerRest.getJobs())
//	            .willReturn(asList(
//	                    new Job().withId("1").withTitle("pilote"),
//	                    new Job().withId("2").withTitle("copilote")));

	    this.mvc.perform(get("/v1/jobs"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(8)))
	            .andExpect(jsonPath("$.[0].name", is("pilote")))
	            .andExpect(jsonPath("$.[1].name", is("copilote")))
	            .andExpect(jsonPath("$.[2].name", is("droid")))
	            .andExpect(jsonPath("$.[3].name", is("mecanician")))
	            .andExpect(jsonPath("$.[4].name", is("general")))
	            .andExpect(jsonPath("$.[5].name", is("captain")))
	            .andExpect(jsonPath("$.[6].name", is("emperor")))
	            .andExpect(jsonPath("$.[7].name", is("soldier")));
	}

	/**
	 * Test postJobs
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03PostJobs() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setName("test insert 1");
		jobs.add(new Job());
		jobs.get(1).setName("test insert 2");

		this.mvc.perform(post("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].name", is("test insert 1")))
				.andExpect(jsonPath("$.[1].name", is("test insert 2")));
		
	}

	/**
	 * Test postJobs with id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04PostJobsWithId() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(3);
		jobs.get(0).setName("test insert 3");
		jobs.add(new Job());
		jobs.get(1).setName("test insert 4");

		this.mvc.perform(post("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].name", is("test insert 4")));
		
	}

	/**
	 * Test putJobs
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05PutJobs() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(9);
		jobs.get(0).setName("test update 1");
		jobs.add(new Job());
		jobs.get(1).setId(10);
		jobs.get(1).setName("test update 2");

		this.mvc.perform(put("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$.[0].id", is(9)))
				.andExpect(jsonPath("$.[0].name", is("test update 1")))
				.andExpect(jsonPath("$.[1].id", is(10)))
				.andExpect(jsonPath("$.[1].name", is("test update 2")));
		
	}

	/**
	 * Test putJobs without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06PutJobsWithoutId() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(9);
		jobs.get(0).setName("test update 3");
		jobs.add(new Job());
		jobs.get(1).setName("test update 4");

		this.mvc.perform(put("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(9)))
				.andExpect(jsonPath("$.[0].name", is("test update 3")));
		
	}

	/**
	 * Test putJobs with bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07PutJobsWithBadId() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(100);
		jobs.get(0).setName("test update 5");

		this.mvc.perform(put("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(0)));
		
	}

	/**
	 * Test deleteJobs
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08DeleteJobs() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(9);
		jobs.add(new Job());
		jobs.get(1).setId(10);
		jobs.add(new Job());
		jobs.get(2).setId(11);

		this.mvc.perform(delete("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk());
		
		// Problème à cause de l'ordre des tests faits aléatoriement ... 
		this.mvc.perform(get("/v1/jobs"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

	/**
	 * Test deleteJobs Without id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09DeleteJobsWithoutId() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setName("test delete");

		this.mvc.perform(delete("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk());

		// Problème à cause de l'ordre des tests faits aléatoriement ...
		this.mvc.perform(get("/v1/jobs"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

	/**
	 * Test deleteJobs With bad id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10DeleteJobsWithBadId() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(100);

		this.mvc.perform(delete("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk());

		// Problème à cause de l'ordre des tests faits aléatoriement ...
		this.mvc.perform(get("/v1/jobs"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

	/**
	 * Test deleteJobs With used id
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11DeleteJobsWithUsedId() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setId(1);

		this.mvc.perform(delete("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk());

		// Problème à cause de l'ordre des tests faits aléatoriement ...
		this.mvc.perform(get("/v1/jobs"))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.length()", is(8)));
		
	}

}
