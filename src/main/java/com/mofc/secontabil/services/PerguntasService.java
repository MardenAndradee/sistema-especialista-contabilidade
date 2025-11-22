package com.mofc.secontabil.services;

import com.mofc.secontabil.dtos.RespostaDTO;
import com.mofc.secontabil.models.Perguntas;
import com.mofc.secontabil.models.Respostas;
import com.mofc.secontabil.repositories.PerguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerguntasService {

    @Autowired
    PerguntasRepository perguntasRepository;

    @Autowired
    RespostasService respostasService;

    public Perguntas findById(Long id){
        Perguntas pergunta = perguntasRepository.findPerguntasById(id);
        if (pergunta == null) {
            throw new BeanDefinitionValidationException("Pergunta não encontrada");
        }
        return pergunta;
    }

    public List<Perguntas> getAllPerguntas(){
        return perguntasRepository.findAll();
    }

    public Perguntas obterIdProximaPergunta(RespostaDTO dto){

        Long idPergunta = dto.getIdPergunta();
        Perguntas proximaPergunta = perguntasRepository.findPerguntasById(1L);

        //---------------------------------------------------------------
        // REGRA 1 — Pergunta 1
        //---------------------------------------------------------------
        if(idPergunta == 1){
            Respostas resposta = new Respostas(findById(1L), dto.getRespostaDouble().toString());
            respostasService.create(resposta);

            if(dto.getRespostaDouble() <= 81000){
                proximaPergunta = findById(2L);
            } else if(dto.getRespostaDouble() <= 360000){
                proximaPergunta = findById(3L);
            } else if(dto.getRespostaDouble() <= 480000){
                proximaPergunta = findById(3L);
            } else {
                proximaPergunta = findById(4L);
            }
        }

        //---------------------------------------------------------------
        // REGRA 2 — Pergunta 2 (Boolean)
        //---------------------------------------------------------------
        else if(idPergunta == 2){
            Respostas resposta = new Respostas(findById(2L), dto.getRespostaBoolean().toString());
            respostasService.create(resposta);

            proximaPergunta = dto.getRespostaBoolean() ? findById(5L) : findById(6L);
        }

        //---------------------------------------------------------------
        // REGRA 3 — Pergunta 3 (String)
        //---------------------------------------------------------------
        else if(idPergunta == 3){
            Respostas resposta = new Respostas(findById(3L), dto.getRespostaString());
            respostasService.create(resposta);

            switch (dto.getRespostaString()) {
                case "Comércio":  proximaPergunta = findById(7L); break;
                case "Serviços":  proximaPergunta = findById(8L); break;
                case "Industria": proximaPergunta = findById(9L); break;
                case "Misto":     proximaPergunta = findById(10L); break;
            }
        }

        //---------------------------------------------------------------
        // PERGUNTAS 4 a 10 (Boolean → sempre salva corretamente)
        //---------------------------------------------------------------
        else if(idPergunta >= 4 && idPergunta <= 10){
            Respostas resposta = new Respostas(findById(idPergunta), dto.getRespostaBoolean().toString());
            respostasService.create(resposta);

            proximaPergunta = findById(11L);
        }

        //---------------------------------------------------------------
        // PERGUNTA 11 (Boolean)
        //---------------------------------------------------------------
        else if(idPergunta == 11){
            Respostas resposta = new Respostas(findById(11L), dto.getRespostaBoolean().toString());
            respostasService.create(resposta);

            proximaPergunta = findById(12L);
        }

        //---------------------------------------------------------------
        // PERGUNTA 12 (String)
        //---------------------------------------------------------------
        else if(idPergunta == 12){
            Respostas resposta = new Respostas(findById(12L), dto.getRespostaString());
            respostasService.create(resposta);

            proximaPergunta = findById(13L);
        }

        //---------------------------------------------------------------
        // PERGUNTA 13 (Boolean)
        //---------------------------------------------------------------
        else if(idPergunta == 13){
            Respostas resposta = new Respostas(findById(13L), dto.getRespostaBoolean().toString());
            respostasService.create(resposta);

            proximaPergunta = findById(13L);
        }

        if (proximaPergunta == null) {
            throw new BeanDefinitionValidationException("Próxima pergunta não encontrada");
        }

        return proximaPergunta;
    }
}
