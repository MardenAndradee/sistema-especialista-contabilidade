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
        Perguntas pergunta = perguntasRepository.findPerguntasById(1L);

        if(idPergunta==1){
            if(dto.getRespostaDouble()<=81000){
                //retorna id2
                pergunta = perguntasRepository.findPerguntasById(2L);
                //"acao": "Possível enquadramento: MEI"
                //armazena a resposta
                Respostas resposta = new Respostas(pergunta, dto.getRespostaDouble().toString());
                respostasService.create(resposta);
            }else if(dto.getRespostaDouble()>81000 && dto.getRespostaDouble() <=360000){
                //retorna id3
                pergunta = perguntasRepository.findPerguntasById(3L);
                //"Possível enquadramento: Simples Nacional (Faixa 1)"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaDouble().toString());
                respostasService.create(resposta);
            }else if(dto.getRespostaDouble()>360000 && dto.getRespostaDouble() <=480000){
                //retorna id3
                pergunta = perguntasRepository.findPerguntasById(3L);
                //"Possível enquadramento: Simples Nacional (Faixa 2)"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaDouble().toString());
                respostasService.create(resposta);
            }else if(dto.getRespostaDouble()>480000){
                //retorna id4
                pergunta = perguntasRepository.findPerguntasById(4L);
                //"Possível enquadramento: Lucro Presumido ou Real"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaDouble().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==2){
            if(dto.getRespostaBoolean()){
                //retorna id5
                pergunta = perguntasRepository.findPerguntasById(5L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //retorna id6
                pergunta = perguntasRepository.findPerguntasById(6L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==3){
            if(dto.getRespostaString().equals("Comércio")){
                //retorna id7
                pergunta = perguntasRepository.findPerguntasById(7L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("Serviços")){
                //retorna id8
                pergunta = perguntasRepository.findPerguntasById(8L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("Industria")){
                //retorna id9
                pergunta = perguntasRepository.findPerguntasById(9L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("Misto")){
                //retorna id10
                pergunta = perguntasRepository.findPerguntasById(10L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==4){
            if(dto.getRespostaBoolean()){
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                //"acao": "Recomendação: Avaliar Lucro Real"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                //"acao": "Recomendação: Avaliar Lucro Presumido"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==5){
            if(dto.getRespostaBoolean()){
                //"acao": "Possibilidade de redução tributária via Simples Nacional (Anexo IV)"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Manter enquadramento atual"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==6){
            if(dto.getRespostaBoolean()){
                //"acao": "Recomendação: Permanecer no MEI"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Migrar para Simples Nacional"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==7){
            if(dto.getRespostaBoolean()){
                //"acao": "Recomendação: Permanecer no MEI"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Migrar para Simples Nacional"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==8){
            if(dto.getRespostaBoolean()){
                //"acao": "Necessário Inscrição Estadual e atenção ao ICMS interestadual"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Seguir obrigações fiscais estaduais normais"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==9){
            if(dto.getRespostaBoolean()){
                //"acao": "Manter benefícios e avaliar enquadramento no Simples ou Lucro Presumido"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Seguir regime normal"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==10){
            if(dto.getRespostaBoolean()){
                //"acao": "Avaliar tributação separada por atividade"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Seguir tributação principal"
                //retorna id11
                pergunta = perguntasRepository.findPerguntasById(11L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==11){
            if(dto.getRespostaBoolean()){
                //"acao": "Verificar obrigações de comércio exterior e ICMS"
                //retorna id12
                pergunta = perguntasRepository.findPerguntasById(12L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Seguir tributação normal"
                //retorna id12
                pergunta = perguntasRepository.findPerguntasById(12L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==12){
            if(dto.getRespostaString().equals("MEI")){
                //retorna id13
                pergunta = perguntasRepository.findPerguntasById(13L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("EIRELI")){
                //retorna id13
                pergunta = perguntasRepository.findPerguntasById(13L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("LTDA")){
                //retorna id13
                pergunta = perguntasRepository.findPerguntasById(13L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("S/A")){
                //retorna id13
                pergunta = perguntasRepository.findPerguntasById(13L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }else if(dto.getRespostaString().equals("Outros")){
                //retorna id13
                pergunta = perguntasRepository.findPerguntasById(13L);
                Respostas resposta = new Respostas(pergunta, dto.getRespostaString());
                respostasService.create(resposta);
            }
        }else if(idPergunta==13){
            if(dto.getRespostaBoolean()){
                //"acao": "Possível vantagem em avaliar Lucro Real"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }else{
                //"acao": "Manter enquadramento atual"
                Respostas resposta = new Respostas(pergunta, dto.getRespostaBoolean().toString());
                respostasService.create(resposta);
            }
        }

        if (pergunta == null) {
            throw new BeanDefinitionValidationException("Pergunta não encontrada");
        }

        return pergunta;

     }
}
