package org.example;

public enum Horarios {

    DEZMANHA("10:00"), QUATORZETARDE("14:00"), DEZESSEISTARDE("16:00"), DEZENOVENOITE("19:00");


    private String horario;
    Horarios(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }
}
