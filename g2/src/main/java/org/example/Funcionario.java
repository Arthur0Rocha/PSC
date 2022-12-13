package org.example;

public final class Funcionario extends Pessoa{

    private String user;
    private String senha;
    static int totalid = 1;

    private int idpessoal;
    private boolean admin;

    public Funcionario(String nm, String numtlf, String usr, String snh, boolean adm) {
        super(nm, numtlf);
        this.setUser(usr);
        this.setSenha(snh);
        this.setAdmin(adm);
        this.setIdpessoal(getTotalid());
    }

    public Funcionario(String nm, String usr, String snh, boolean adm) {
        super(nm, null );
        this.setUser(usr);
        this.setSenha(snh);
        this.setAdmin(adm);
        this.setIdpessoal(getTotalid());
    }




    public String getUser() {
        return user;
    }

    public void setUser(String login) {
        this.user = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static int getTotalid() {
        return totalid;
    }

    public static void setId(int idt) {
        totalid = idt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getIdpessoal() {
        return idpessoal;
    }

    public void setIdpessoal(int idp) {
        this.idpessoal = idp;
    }
}
