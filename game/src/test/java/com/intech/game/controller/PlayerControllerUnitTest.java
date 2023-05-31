package com.intech.game.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.intech.game.model.Player;
import com.intech.game.service.PlayerService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PlayerControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerService playerService;

	@WithMockUser
	@Test
	public void testGetPlayers() throws Exception {
		//Arrange
		List<Player> players = new ArrayList<Player>();
		Player p1 = new Player();
		p1.setPlayerId(1);
		p1.setUsername("p1");
		p1.setLevel(100);
		players.add(p1);

		Mockito.when(playerService.getPlayers()).thenReturn(players);
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/player");
		//Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().isOk());
		rActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("p1"));
	}

	@WithMockUser
	@Test
	public void testGetPlayerNotFound() throws Exception {
		//Arrange
		Mockito.when(playerService.getPlayer(1)).thenReturn(Optional.ofNullable(null));
		RequestBuilder rBuilder = MockMvcRequestBuilders.get("/player/1");
		//Act
		ResultActions rActions = mockMvc.perform(rBuilder);
		//Assert
		rActions.andExpect(MockMvcResultMatchers.status().is(404));
	}


}



