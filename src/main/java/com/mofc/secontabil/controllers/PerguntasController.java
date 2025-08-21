package com.mofc.secontabil.controllers;

import com.mofc.secontabil.dtos.RespostaDTO;
import com.mofc.secontabil.models.Perguntas;
import com.mofc.secontabil.services.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PerguntasController {

    @Autowired
    PerguntasService perguntasService;

    @GetMapping("/perguntas")
    public ResponseEntity<List<Perguntas>> getAllPerguntas(){
        return ResponseEntity.status(HttpStatus.OK).body(perguntasService.getAllPerguntas());
    }

    @GetMapping("/perguntas/{id}")
    public ResponseEntity<Perguntas> getPerguntaById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(perguntasService.findById(id));
    }

    @PostMapping("/resposta")
    public String obterResposta(@RequestBody RespostaDTO dto){
        Long id = dto.id();
        Object resposta = dto.resposta();

        //fazer lógica e retornar id da proxima pergunta
        //perguntasService.obterIdPerguntaPelaResposta(dto);

        if (resposta instanceof String) {
            String valor = (String) resposta;
            return "Pergunta " + id + " recebida com resposta TEXTUAL: " + valor;
        } else if (resposta instanceof Boolean) {
            Boolean valor = (Boolean) resposta;
            return "Pergunta " + id + " recebida com resposta BOOLEANA: " + valor;
        } else if (resposta instanceof Number) {
            Double valor = ((Number) resposta).doubleValue();
            return "Pergunta " + id + " recebida com resposta NUMÉRICA: " + valor;
        } else {
            return "Tipo de resposta não reconhecido para a pergunta " + id;
        }
    }



}
