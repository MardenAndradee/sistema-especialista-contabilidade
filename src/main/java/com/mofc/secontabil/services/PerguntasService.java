package com.mofc.secontabil.services;

import com.mofc.secontabil.models.Perguntas;
import com.mofc.secontabil.repositories.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntasService {

    @Autowired
    PerguntasRepository perguntasRepository;

    public Perguntas findById(Long id){
        Optional<Perguntas> perguntas = this.perguntasRepository.findById(id);
        return perguntas.orElseThrow(() -> new BeanDefinitionValidationException(
           "Pergunta não encontrada"
        ));
    }

    public List<Perguntas> getAllPerguntas(){
        return perguntasRepository.findAll();
    }



}
