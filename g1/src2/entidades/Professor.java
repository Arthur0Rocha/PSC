package entidades;

import entidades.MembrosInstituicao;

public class Professor extends MembrosInstituicao {

    private String cursoLecionado;
    private double salario;

    MembrosInstituicao instituicao = new MembrosInstituicao();

    public Professor() {
        super(); // Versão padrão do construtor da classe mãe. 
    }

    public Professor(String cursoLecionado, double salario, int id, String nome, String membro, String semestre) {
        super(id, nome, membro, semestre); // Indica que a classe filha herda também o construtor da classe mãe.
        this.cursoLecionado = cursoLecionado;
        this.salario = salario;
    }

    public void setCursoLecionado(String cursoLecionado) {
        this.cursoLecionado = cursoLecionado;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCursoLecionado() {
        return cursoLecionado;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() { // Sobreposição do método ''toString'', implementando a própria versão de ''toString''.
        return "ID - " + id
                + " ("
                + "Nome: "
                + nome
                + "| "
                + "Membro: "
                + membro
                + "| "
                + "Semestre: "
                + semestre
                + "| "
                + "Curso Lecionado: "
                + cursoLecionado
                + "| "
                + "Salário: "
                + String.format("%.2f", salario)
                +")";
        
    }

}
