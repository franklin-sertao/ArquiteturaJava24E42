package br.edu.infnet.franklin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.infnet.franklin.client.CosmosClient;
import feign.Headers;


@RestController
public class CosmosController {

	@Autowired
	private CosmosClient cosmosClient;
		
	@GetMapping(value = "/cosmos/gtin/{cod}")
	@Headers("Content-Type: application/json")
	public String getGtin(@PathVariable String cod) throws JsonProcessingException {
		 
		ObjectMapper mapper = new ObjectMapper();  
		 return mapper.writeValueAsString(cosmosClient.getGtin(cod));
	}
	
}
