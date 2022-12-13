package org.example;

public enum Servicos {

    TOSASIMPLES("Tosa Simples", 50),
    TOSACOMPLETA("Tosa Higiênica", 85),
    BANHO("Banho", 45),
    BANHOETOSA("Banho e Tosa", 110);

    private String nome;
    private double preco;

    Servicos(String nm, double prc){
    this.nome = nm;
    this.preco = prc;
    }

    public String getNome() {
        return nome;
    }


    public double getPreco() {
        return preco;
    }
}

