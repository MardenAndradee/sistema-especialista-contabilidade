package com.mofc.secontabil.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_RESPOSTAS")
public class Respostas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "pergunta_id")
    private Perguntas pergunta;
    private String Resposta;

    public Respostas(){}

    public Respostas(Perguntas pergunta, String resposta) {
        this.pergunta = pergunta;
        Resposta = resposta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perguntas  getPergunta() {
        return pergunta;
    }

    public void setPergunta(Perguntas pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return Resposta;
    }

    public void setResposta(String resposta) {
        Resposta = resposta;
    }
}
