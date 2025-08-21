package com.mofc.secontabil.controllers;

import com.mofc.secontabil.services.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cnpj")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/{cnpj}")
    public ResponseEntity<String> buscarCNPJ (@PathVariable String cnpj){
        String resultado = receitaService.obterDadosEmpresa(cnpj);
        return ResponseEntity.ok(resultado);
    }

}
