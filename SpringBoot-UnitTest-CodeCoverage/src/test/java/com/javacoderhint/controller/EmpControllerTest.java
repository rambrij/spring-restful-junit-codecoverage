package com.javacoderhint.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.javacoderhint.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllEmpList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/emp").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	@Test
	public void verifyEmpById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/emp/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.age").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.name").value("John"))
		.andExpect(jsonPath("$.age").value(23))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidEmpArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/emp/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	
	@Test
	public void verifyInvalidEmpId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/emp/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Emp doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyNullEmp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/emp/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Emp doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteEmp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/emp/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Emp has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidEmpIdToDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/emp/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Emp to delete doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveEmp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/emp/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Mike\", \"age\" : \"34\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.age").exists())
		.andExpect(jsonPath("$.name").value("Mike"))
		.andExpect(jsonPath("$.age").value(34))
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveEmp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/emp/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"8\", \"name\" : \"New Emp Sample\", \"age\" : \"12\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
		.andDo(print());
	}
	
	@Test
	public void verifyUpdateEmp() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/emp/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"1\", \"name\" : \"New Emp Text\", \"age\" : \"12\" }")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.age").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("New Emp Text"))
		.andExpect(jsonPath("$.age").value(12))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidEmpUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/emp/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"idd\": \"8\", \"name\" : \"New Emp Sample\", \"age\" : \"12\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Emp to update doesn´t exist"))
		.andDo(print());
	}

}
