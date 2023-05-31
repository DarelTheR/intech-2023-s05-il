package com.intech.game.controller;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser
	@Test
	public void testGetPlayers() throws Exception {
		//Arrange
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/player");
		// Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().isOk());
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].username", CoreMatchers.is("test1")));
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$[1].username", CoreMatchers.is("test2")));
		
	}
	
	@WithMockUser
	@Test
	public void testGetPlayer() throws Exception {
		//Arrange
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/player/1");
		//Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().is(200));
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$.playerId", CoreMatchers.is(1)));
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is("test1")));
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$.level", CoreMatchers.is(10)));
	}
	
	@WithMockUser
	@Test
	public void testGetPlayerNotFound() throws Exception {
		//Arrange
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/player/4");
		//Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
}