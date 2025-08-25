package com.mofc.secontabil.controllers;

import com.mofc.secontabil.models.Respostas;
import com.mofc.secontabil.services.RespostasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RespostasController {

    @Autowired
    RespostasService respostasService;

    @RequestMapping("/respostas")
    public ResponseEntity<List<Respostas>> getAllRespostas(){
        return ResponseEntity.ok(respostasService.getAllRespostas());
    }
}
