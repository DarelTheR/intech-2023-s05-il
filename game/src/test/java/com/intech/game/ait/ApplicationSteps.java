package com.intech.game.ait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;

public class ApplicationSteps {

	@Autowired
	public MockMvc mockMvc;
	
	private ResultActions resultActions;
	
	@Etantdonné("l utilisateur demande le nom de l application")
	public void l_utilisateur_demande_le_nom_de_l_application() throws Exception {
		resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/"));
	}
	
	@Quand("la reponse est retournee a l utilisateur")
	public void la_reponse_est_retournee_a_l_utilisateur() throws Exception {
		resultActions = resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Alors("on retourne {string}")
	public void on_retourne(String expectedResult) throws Exception {
		MvcResult result = resultActions.andReturn();
		assertEquals(expectedResult, result.getResponse().getContentAsString());
	}
	
}
