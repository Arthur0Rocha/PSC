package org.example;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int numescolha;



            BancoDDS BancoDeDados = new BancoDDS();
            BancoDeDados.cadastroSys("admin", "admin", "admin", true);

            Login.realizarLogin();

    if(Login.getX() == 0){
            if (Login.isLoginAprovado()) {
                do {

                    String[] escolha = {"Gerenciar Horários", "Gerenciar Usuários", "Mostrar Horários", "Logar com outra Conta", "Sair"};
                    numescolha = JOptionPane.showOptionDialog(null, "SELECIONE UMA DAS OPÇÕES",
                            "O QUE DESEJA FAZER?",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolha, escolha[0]);

                    switch (numescolha) {
                        case 0:
                            String[] escolhaGenHor = {"Criar Horário", "Sair"};
                            int numescolhaGenHor = JOptionPane.showOptionDialog(null, "SELECIONE UMA DAS OPÇÕES",
                                    "O QUE DESEJA FAZER?",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaGenHor, escolhaGenHor[0]);
                            switch (numescolhaGenHor) {
                                case 0:
                                    BancoDeDados.cadastrarHorario();
                                    break;
                                case 1:
                                    JOptionPane.showMessageDialog(null, "Saindo");
                                    break;

                            }

                            break;

                        case 1:
                            String[] escolhaGerenUser = {"Criar Usuário", "Sair"};
                            int numescolhaGerenUser = JOptionPane.showOptionDialog(null, "SELECIONE UMA DAS OPÇÕES",
                                    "O QUE DESEJA FAZER?",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaGerenUser, escolhaGerenUser[0]);
                            switch (numescolhaGerenUser) {
                                case 0 -> {BancoDeDados.cadastrarUsuario();
                                    break;
                                }
                                case 1 -> {
                                    JOptionPane.showMessageDialog(null, "Saindo");
                                    break;
                                }
                                default -> {
                                    break;
                                }
                            }
                            break;

                        case 2:
                            for (int i = 0; i < Horario.getQtdHorario(); i++) {
                                try {
                                    System.out.println("ID: " + BancoDDS.horarios[i].getIdPessoal() + " [" + "Dia: " + BancoDDS.horarios[i].getDia() + " | Hora: " + BancoDDS.horarios[i].getHora() + " | Raça do Cachorro: " + BancoDDS.horarios[i].getRaca() + " | Cliente: " +
                                            BancoDDS.horarios[i].getDono() + " | Procedimento: " + BancoDDS.horarios[i].getAcao() + " | Valor: " + BancoDDS.horarios[i].getValor() + "R$" + "]");
                                } catch (NullPointerException exc) {

                                }
                            }
                            break;

                        case 3:
                            Login.realizarLogin();
                            break;

                        case 4:

                            break;


                    }

                } while (numescolha == 0 || numescolha == 1 || numescolha == 2 || numescolha == 3);
            }
                JOptionPane.showMessageDialog(null, "SAINDO...", "OBRIGADO POR USAR O SISTEMA, ATÈ LOGO!", JOptionPane.PLAIN_MESSAGE);
            }


        }
}