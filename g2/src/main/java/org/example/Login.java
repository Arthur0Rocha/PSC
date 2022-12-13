package org.example;

import javax.swing.*;
import java.awt.image.BandCombineOp;

public class Login {

    private static boolean loginAprovado;

    private static Funcionario funcionarioLogado;

    private static boolean bg;

    private static int x;

    private static int contador;
    private static int num = 0;
    static JPanel p = new JPanel();
    static JTextField user = new JTextField(10);
    static JTextField senha = new JTextField(10);

    public static void realizarLogin(){

        if (!bg) {
            p.add(new JLabel("Login : "));
            p.add(user);
            p.add(new JLabel("Senha : "));
            p.add(senha);
        }

        x = JOptionPane.showConfirmDialog(null, p, "BEM VINDO AO PETSHOP MOREIRA", JOptionPane.OK_CANCEL_OPTION);

    if (x == 0) {
        for (int contador = 0; contador < Funcionario.getTotalid(); contador++) {


            boolean loginCorreto = user.getText().equalsIgnoreCase(BancoDDS.funcionarios[contador].getUser());


            if (loginCorreto) {

                boolean senhaCorreta = senha.getText().equalsIgnoreCase(BancoDDS.funcionarios[contador].getSenha());

                if (senhaCorreta) {
                    setLoginAprovado(true);
                    JOptionPane.showMessageDialog(null, "Realizando login...");
                    Login.setFuncionarioLogado(BancoDDS.funcionarios[contador]);
                    setBg(true);
                    break;

                } else {
                    setLoginAprovado(false);
                    JOptionPane.showMessageDialog(null, "Senha Incorreta");
                    setBg(true);
                    realizarLogin();
                }

            } else if (contador == Funcionario.getTotalid() - 1) {
                setLoginAprovado(false);
                JOptionPane.showMessageDialog(null, "Usuário Não Encontrado");
                setBg(true);
                realizarLogin();
            }

        }
    }

        }


    public static boolean isLoginAprovado() {
        return loginAprovado;
    }

    public static void setLoginAprovado(boolean lga) {
        loginAprovado = lga;
    }

    public static boolean isBg() {
        return bg;
    }

    public static void setBg(boolean bg) {
        Login.bg = bg;
    }

    public static Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public static void setFuncionarioLogado(Funcionario funcionarioLogado) {
        Login.funcionarioLogado = funcionarioLogado;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        Login.num = num;
    }

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        Login.x = x;
    }
}

