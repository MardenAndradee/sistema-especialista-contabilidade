package com.mofc.secontabil.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReceitaService {

    private final RestTemplate restTemplate;

    public ReceitaService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public String obterDadosEmpresa(String cnpj){
        String url = "https://receitaws.com.br/v1/cnpj/" + cnpj;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
