package com.intech.game.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intech.game.model.Player;
import com.intech.game.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {

	private PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@GetMapping
	public List<Player> getPlayers() {
		return playerService.getPlayers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Player> getPlayer(@PathVariable(name = "id") Integer id) {
		Optional<Player> player = playerService.getPlayer(id);
		if (player.isPresent()) {
			return ResponseEntity.ok(player.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}