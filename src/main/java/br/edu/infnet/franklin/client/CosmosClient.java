package br.edu.infnet.franklin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.franklin.config.FeignConfig;
import br.edu.infnet.franklin.model.domain.Cosmos;

@FeignClient(url = "https://api.cosmos.bluesoft.com.br/", name = "cosmos", configuration = FeignConfig.class)
public interface CosmosClient {
    @GetMapping(value = "/gtins/{cod}")
    Cosmos getGtin(@PathVariable String cod);
}