/**
 * 
 */
package com.spaceprogram.controller.job;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.spaceprogram.model.job.Job;

/**
 * @author GLieuatard
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.config.name: spaceprogram" }, classes = { SpaceprogramApplication.class })
@AutoConfigureMockMvc
public class JobsControllerRestTest {

	@Autowired
	private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {

	}

	@Test
	public void getJob() throws Exception {

		this.mvc.perform(get("/v1/jobs/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("pilote")));

		this.mvc.perform(get("/v1/jobs/2")).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("copilote")));

	}
	
	@Test
	public void getJobs() throws Exception {

//	    given(this.jobsControllerRest.getJobs())
//	            .willReturn(asList(
//	                    new Job().withId("1").withTitle("pilote"),
//	                    new Job().withId("2").withTitle("copilote")));

	    this.mvc.perform(get("/v1/jobs"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()", is(8)));
	}

	@Test
	public void postJob() throws Exception {

		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job());
		jobs.get(0).setName("test");

		this.mvc.perform(post("/v1/jobs").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(jobs))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$.[0].id", is(9))).andExpect(jsonPath("$.[0].name", is("test")));
		
	}

}
