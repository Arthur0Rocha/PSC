package org.example;

public abstract class Pessoa {

    private String nome;
    private String numTelef;

    public Pessoa(String nome, String numTelef) {
        this.nome = nome;
        this.numTelef = numTelef;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumTelef() {
        return numTelef;
    }

    public void setNumTelef(String numTelef) {
        this.numTelef = numTelef;
    }

}
