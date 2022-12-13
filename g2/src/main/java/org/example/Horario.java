package org.example;

public class Horario {

    private String dia;

    private String hora;

    private static int qtdHorario = 1;

    private int idPessoal;

    private String dono;

    private String raca;

    private String acao;

    private double valor;


    public Horario(String da, String dn, String rc) {
        setDia(da);
        setDono(dn);
        setRaca(rc);
        this.setIdPessoal(Horario.getQtdHorario());
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public static int getQtdHorario() {
        return qtdHorario;
    }

    public static void setQtdHorario(int qtdHorario) {
        Horario.qtdHorario = qtdHorario;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaça(String raça) {
        this.raca = raça;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdPessoal() {
        return idPessoal;
    }

    public void setIdPessoal(int idPessoal) {
        this.idPessoal = idPessoal;
    }
}
