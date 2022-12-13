package entidades;

public class MembrosInstituicao {

    protected int id;
    protected String nome;
    protected String membro;
    protected String semestre;

    public MembrosInstituicao() {

    }

    public MembrosInstituicao(int id, String nome, String membro, String semestre) {
        this.id = id;
        this.nome = nome;
        this.membro = membro;
        this.semestre = semestre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMembro(String membro) {
        this.membro = membro;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMembro() {
        return membro;
    }

    public String getSemestre() {
        return semestre;
    }

}
