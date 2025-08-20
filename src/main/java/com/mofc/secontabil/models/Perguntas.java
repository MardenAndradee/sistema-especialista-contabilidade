package com.mofc.secontabil.models;

import jakarta.persistence.*;

@Entity
@Table(name="tb_perguntas")
public class Perguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String pergunta;

    public Perguntas(long id, String pergunta) {
        this.id = id;
        this.pergunta = pergunta;
    }

    public Perguntas(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}
