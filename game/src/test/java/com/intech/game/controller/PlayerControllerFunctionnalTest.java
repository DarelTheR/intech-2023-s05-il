package com.intech.game.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import com.intech.game.model.Player;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PlayerControllerFunctionnalTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Timeout(unit = TimeUnit.MINUTES, value = 1)
	public void getPlayers() {
		// Act
		Player[] players = restTemplate.withBasicAuth("user","password").getForObject("/player", Player[].class);
		// Assert
		assertEquals(1, players[0].getPlayerId());
		assertEquals("test1", players[0].getUsername());
		assertEquals(10, players[0].getLevel());

		assertEquals(2, players[1].getPlayerId());
		assertEquals("test2", players[1].getUsername());
		assertEquals(4, players[1].getLevel());
	}

	@Test
	@Timeout(unit = TimeUnit.MINUTES, value = 1)
	public void getPlayer() {
		// Act
		Player player = restTemplate.withBasicAuth("user","password").getForObject("/player/1", Player.class);
		// Assert
		assertEquals(1, player.getPlayerId());
		assertEquals("test1", player.getUsername());
		assertEquals(10, player.getLevel());
		assertEquals(2, player.getRewards().size());
	}

}
