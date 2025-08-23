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

    public Perguntas obterIdProximaPergunta(RespostaDTO dto){

        Long idPergunta = dto.getIdPergunta();
        Optional<Perguntas> pergunta = perguntasRepository.findById(1L);


        if(idPergunta==1){

            if(dto.getRespostaDouble()<=81000){
                //retorna id2
                pergunta = perguntasRepository.findById(2L);
                //"acao": "Possível enquadramento: MEI"
            }else if(dto.getRespostaDouble()>81000 && dto.getRespostaDouble() <=360000){
                //retorna id3
                pergunta = perguntasRepository.findById(3L);
                //"Possível enquadramento: Simples Nacional (Faixa 1)"
            }else if(dto.getRespostaDouble()>360000 && dto.getRespostaDouble() <=480000){
                //retorna id3
                pergunta = perguntasRepository.findById(3L);
                //"Possível enquadramento: Simples Nacional (Faixa 2)"
            }else if(dto.getRespostaDouble()>480000){
                //retorna id4
                pergunta = perguntasRepository.findById(4L);
                //"Possível enquadramento: Lucro Presumido ou Real"
            }

        }else if(idPergunta==2){

            if(dto.getRespostaBoolean()){
                //retorna id5
                pergunta = perguntasRepository.findById(5L);
            }else{
                //retorna id6
                pergunta = perguntasRepository.findById(6L);
            }

        }else if(idPergunta==3){

            if(dto.getRespostaString().equals("Comércio")){
                //retorna id7
                pergunta = perguntasRepository.findById(7L);
            }else if(dto.getRespostaString().equals("Serviços")){
                //retorna id8
                pergunta = perguntasRepository.findById(8L);
            }else if(dto.getRespostaString().equals("Industria")){
                //retorna id9
                pergunta = perguntasRepository.findById(9L);
            }else if(dto.getRespostaString().equals("Misto")){
                //retorna id10
                pergunta = perguntasRepository.findById(10L);
            }

        }else if(idPergunta==4){

            if(dto.getRespostaBoolean()){
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
                //"acao": "Recomendação: Avaliar Lucro Real"
            }else{
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
                //"acao": "Recomendação: Avaliar Lucro Presumido"
            }

        }else if(idPergunta==5){

            if(dto.getRespostaBoolean()){
                //"acao": "Possibilidade de redução tributária via Simples Nacional (Anexo IV)"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }else{
                //"acao": "Manter enquadramento atual"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }

        }else if(idPergunta==6){

            if(dto.getRespostaBoolean()){
                //"acao": "Recomendação: Permanecer no MEI"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }else{
                //"acao": "Migrar para Simples Nacional"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }

        }else if(idPergunta==7){

            if(dto.getRespostaBoolean()){
                //"acao": "Recomendação: Permanecer no MEI"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }else{
                //"acao": "Migrar para Simples Nacional"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }

        }else if(idPergunta==8){

            if(dto.getRespostaBoolean()){
                //"acao": "Necessário Inscrição Estadual e atenção ao ICMS interestadual"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }else{
                //"acao": "Seguir obrigações fiscais estaduais normais"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }

        }else if(idPergunta==9){

            if(dto.getRespostaBoolean()){
                //"acao": "Manter benefícios e avaliar enquadramento no Simples ou Lucro Presumido"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }else{
                //"acao": "Seguir regime normal"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }

        }else if(idPergunta==10){

            if(dto.getRespostaBoolean()){
                //"acao": "Avaliar tributação separada por atividade"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }else{
                //"acao": "Seguir tributação principal"
                //retorna id11
                pergunta = perguntasRepository.findById(11L);
            }

        }else if(idPergunta==11){

            if(dto.getRespostaBoolean()){
                //"acao": "Verificar obrigações de comércio exterior e ICMS"
                //retorna id12
                pergunta = perguntasRepository.findById(12L);
            }else{
                //"acao": "Seguir tributação normal"
                //retorna id12
                pergunta = perguntasRepository.findById(12L);
            }

        }else if(idPergunta==12){

            if(dto.getRespostaString().equals("MEI")){
                //retorna id13
                pergunta = perguntasRepository.findById(13L);
            }else if(dto.getRespostaString().equals("EIRELI")){
                //retorna id13
                pergunta = perguntasRepository.findById(13L);
            }else if(dto.getRespostaString().equals("LTDA")){
                //retorna id13
                pergunta = perguntasRepository.findById(13L);
            }else if(dto.getRespostaString().equals("S/A")){
                //retorna id13
                pergunta = perguntasRepository.findById(13L);
            }else if(dto.getRespostaString().equals("Outros")){
                //retorna id13
                pergunta = perguntasRepository.findById(13L);
            }

        }else if(idPergunta==13){

            if(dto.getRespostaBoolean()){
                //"acao": "Possível vantagem em avaliar Lucro Real"
            }else{
                //"acao": "Manter enquadramento atual"
            }

        }




        return pergunta.orElseThrow(() -> new BeanDefinitionValidationException(
                "Pergunta não encontrada"
        ));

     }



}
