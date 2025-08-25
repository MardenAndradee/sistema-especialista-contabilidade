package com.mofc.secontabil.services;

import com.mofc.secontabil.models.Respostas;
import com.mofc.secontabil.repositories.RespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostasService {

    @Autowired
    RespostasRepository respostasRepository;

    public void create(Respostas respostas){
        if (respostas!=null){
            respostasRepository.save(respostas);
        }else{
            throw new Error("Resposta não encontrada");
        }
    }

    public List<Respostas> getAllRespostas(){
        return respostasRepository.findAll();
    }

}
