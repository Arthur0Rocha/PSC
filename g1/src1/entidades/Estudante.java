package entidades;

public class Estudante extends MembrosInstituicao {

    private String curso;
    private double mediaFinal;

    public Estudante() {
        super(); // Versão padrão do construtor da classe mãe. 
    }

    public Estudante(String curso, double mediaFinal, int id, String nome, String membro, String semestre) {
        super(id, nome, membro, semestre); // Indica que a classe filha herda também o construtor da classe mãe.
        this.curso = curso;
        this.mediaFinal = mediaFinal;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public String getCurso() {
        return curso;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }
    
    @Override
    public String toString() { // // Sobreposição do método ''toString'', implementando a própria versão de ''toString''.
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
                + "Curso: "
                + curso
                + "| "
                + "Média Final: "
                + String.format("%.2f", mediaFinal)
                +")";
    }

}
