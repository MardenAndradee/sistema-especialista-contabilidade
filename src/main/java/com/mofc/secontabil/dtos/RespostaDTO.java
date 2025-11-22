package com.mofc.secontabil.dtos;

public class RespostaDTO{
    private Long idPergunta;
    private String respostaString;
    private Boolean respostaBoolean;
    private Double respostaDouble;

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getRespostaString() {
        return respostaString;
    }

    public void setRespostaString(String respostaString) {
        this.respostaString = respostaString;
    }

    public Boolean getRespostaBoolean() {
        return respostaBoolean;
    }

    public void setRespostaBoolean(Boolean respostaBoolean) {
        this.respostaBoolean = respostaBoolean;
    }

    public Double getRespostaDouble()   {
        return respostaDouble;
    }

    public void setRespostaDouble(Double respostaDouble) {
        this.respostaDouble = respostaDouble;
    }
}
