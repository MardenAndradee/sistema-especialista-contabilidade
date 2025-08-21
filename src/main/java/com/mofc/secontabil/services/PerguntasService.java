package com.mofc.secontabil.services;

import com.mofc.secontabil.dtos.RespostaDTO;
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

//    public Perguntas obterIdPerguntaPelaResposta(RespostaDTO dto){
//
//        Long idPergunta = dto.id();
//        Object resposta = dto.resposta();
//
//        if (resposta instanceof String) {
//            String valor = (String) resposta;
//        } else if (resposta instanceof Boolean) {
//            Boolean valor = (Boolean) resposta;
//        } else if (resposta instanceof Number) {
//            Double valor = ((Number) resposta).doubleValue();
//        } else {
//            String valor = "Inválido";
//        }
//
//        if(idPergunta.equals(1)){
//            if(valor=>81000){
//                Optional<Perguntas> perguntas = this.perguntasRepository.findById(Long.valueOf(3));
//                return perguntas.orElseThrow(() -> new BeanDefinitionValidationException(
//                        "Pergunta não encontrada"
//                ));
//            } else if() {
//
//            }
//        }
//
  //   }



}
