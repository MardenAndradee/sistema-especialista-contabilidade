package com.mofc.secontabil.controllers;

import com.mofc.secontabil.models.Perguntas;
import com.mofc.secontabil.services.PerguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
