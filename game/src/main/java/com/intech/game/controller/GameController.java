package com.intech.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
	
	@GetMapping(value = "/")
	public String getNameApplication() throws InterruptedException {
		return "Game 1 Application";
	}

}