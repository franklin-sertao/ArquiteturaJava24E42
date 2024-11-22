package br.edu.infnet.franklin.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.infnet.franklin.client.CosmosClient;

public class CosmosService {
	@Autowired
	private CosmosClient cosmosClient;

	public String getGtin(String cod) {
		return cosmosClient.getGtin(cod).toString();
	}
}
