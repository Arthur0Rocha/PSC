package org.example;

import javax.swing.*;

public final class BancoDDS implements AcoesBDDS {


     public JPanel b = new JPanel();
     public JPanel h = new JPanel();

     private boolean admcheck;
     private boolean idExiste;

     private boolean firstHorario;
     private boolean firstCadastro;

     public JTextField nmpanel = new JTextField(10);
    public JTextField numtlfpanel = new JTextField(9);
    public JTextField usrpanel = new JTextField(10);
    public JTextField snhpanel = new JTextField(10);



    public JTextField diapanel = new JTextField(10);
    public JTextField horapanel = new JTextField(9);
    public JTextField donopanel = new JTextField(10);
    public JTextField racapanel = new JTextField(10);





    static int tamanhoVetHor = 1;


    static Horario[] horarios = new Horario[Horario.getQtdHorario() + 3];

    static Funcionario[] funcionarios =  new Funcionario[Funcionario.getTotalid() + 3];


    @Override
    public void cadastrarHorario() {
        if (Horario.getQtdHorario() == 1){
            setFirstHorario(true);
        }
        if(isFirstHorario()) {
            h.add(new JLabel("Dia : "));
            h.add(diapanel);
            h.add(new JLabel("Cliente : "));
            h.add(donopanel);
            h.add(new JLabel("Raca Do cachorro : "));
            h.add(racapanel);
        }

        JOptionPane.showConfirmDialog(null, h, "DIGITE AS INFORMAÇÔES DO NOVO HORARIO", JOptionPane.OK_CANCEL_OPTION);

        if (isFirstHorario()) {
            setFirstHorario(false);
            BancoDDS.horarios[Horario.getQtdHorario() - 1] = new Horario(diapanel.getText(), donopanel.getText(), racapanel.getText());


            String[] escolhaHor = {"DEZ DA MANHÂ", "DUAS DA TARDE", "QUATRO DA TARDE", "SETE DA NOITE"};
            int numescolhaHor = JOptionPane.showOptionDialog(null, "ESCOLHA UM HORARIO?",
                    "ESCOLHA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaHor, escolhaHor[0]);

            switch (numescolhaHor){
                case 0 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.DEZMANHA.getHorario());
                    break;
                }
                case 1 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.QUATORZETARDE.getHorario());
                    break;
                }
                case 2 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.DEZESSEISTARDE.getHorario());
                    break;
                }
                case 3 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.DEZENOVENOITE.getHorario());
                    break;
                }
                default ->  {
                    break;
                }

            }

            String[] escolhaProced = {Servicos.TOSASIMPLES.getNome(), Servicos.TOSACOMPLETA.getNome(), Servicos.BANHO.getNome(), Servicos.BANHOETOSA.getNome()};
            int numescolhaProced = JOptionPane.showOptionDialog(null, "QUAL SERÀ O PROCEDIMENTO REALIZADO?",
                    "ESCOLHA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaProced, escolhaProced[0]);


            switch (numescolhaProced) {
                case 0 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.TOSASIMPLES.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.TOSASIMPLES.getPreco());
                    break;
                }
                case 1 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.TOSACOMPLETA.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.TOSACOMPLETA.getPreco());
                    break;
                }
                case 2 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.BANHO.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.BANHO.getPreco());
                    break;
                }
                case 3 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.BANHOETOSA.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.BANHOETOSA.getPreco());
                    break;
                }
                default -> {
                    break;
                }

            }

            Horario.setQtdHorario(Horario.getQtdHorario() + 1);

        }else {

            BancoDDS.horarios[Horario.getQtdHorario() - 1] = new Horario(diapanel.getText(), donopanel.getText(), racapanel.getText());

            String[] escolhaHorInt = {"DEZ DA MANHÂ", "DUAS DA TARDE", "QUATRO DA TARDE", "SETE DA NOITE"};
            int numescolhaHorInt = JOptionPane.showOptionDialog(null, "ESCOLHA UM HORARIO?",
                    "ESCOLHA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaHorInt, escolhaHorInt[0]);

            switch (numescolhaHorInt){
                case 0 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.DEZMANHA.getHorario());
                    break;
                }
                case 1 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.QUATORZETARDE.getHorario());
                    break;
                }
                case 2 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.DEZESSEISTARDE.getHorario());
                    break;
                }
                case 3 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setHora(Horarios.DEZENOVENOITE.getHorario());
                    break;
                }
                default ->  {
                    break;
                }

            }




            String[] escolhaProced = {Servicos.TOSASIMPLES.getNome(), Servicos.TOSACOMPLETA.getNome(), Servicos.BANHO.getNome(), Servicos.BANHOETOSA.getNome()};
            int numescolhaProced = JOptionPane.showOptionDialog(null, "QUAL SERÀ O PROCEDIMENTO REALIZADO?",
                    "ESCOLHA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaProced, escolhaProced[0]);


            switch (numescolhaProced) {
                case 0 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.TOSASIMPLES.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.TOSASIMPLES.getPreco());
                }
                case 1 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.TOSACOMPLETA.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.TOSACOMPLETA.getPreco());
                }
                case 2 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.BANHO.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.BANHO.getPreco());
                }
                case 3 -> {
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setAcao(Servicos.BANHOETOSA.getNome());
                    BancoDDS.horarios[Horario.getQtdHorario() - 1].setValor(Servicos.BANHOETOSA.getPreco());
                }
                default -> {
                }
            }
            Horario.setQtdHorario(Horario.getQtdHorario() + 1);
        }

    }




    @Override
    public void cadastrarUsuario() {

           if (Login.getFuncionarioLogado().isAdmin()) {
            if(Funcionario.getTotalid() == 1){
                setFirstCadastro(true);
            }

            if(isFirstCadastro()) {
                b.add(new JLabel("Nome : "));
                b.add(nmpanel);
                b.add(new JLabel("Numero de Teleone : "));
                b.add(numtlfpanel);
                b.add(new JLabel("Usuário : "));
                b.add(usrpanel);
                b.add(new JLabel("Senha : "));
                b.add(snhpanel);
                setFirstCadastro(false);
            }

               JOptionPane.showConfirmDialog(null, b, "DIGITE AS INFORMAÇÔES DO NOVO USUÀRIO", JOptionPane.OK_CANCEL_OPTION);

               String[] escolhaCadUser = {"SIM", "NÂO"};

               try {

                   int numescolhaCadUser = JOptionPane.showOptionDialog(null, "ESTE USUÀRIO SERÀ ADM ?",
                           "RESPONDA",
                           JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, escolhaCadUser, escolhaCadUser[0]);

                   if (numescolhaCadUser == 0){
                       setAdmcheck(true);
                   }if (numescolhaCadUser == 1){
                       setAdmcheck(false);
                   }

               } catch (Throwable exc){
                   JOptionPane.showMessageDialog(null, "ERRO! A janela foi fechada e o usuário foi colocado como Usuário comum");
                   setAdmcheck(false);
               }

               Funcionario.setId(Funcionario.getTotalid() + 1);

               BancoDDS.funcionarios[Funcionario.getTotalid() - 1] = new Funcionario(nmpanel.getText(), numtlfpanel.getText(), usrpanel.getText(), snhpanel.getText(), admcheck );

           } else {
               JOptionPane.showMessageDialog(null, "Você não Tem permissão de Criar um Usuário");
           }
       }

    @Override
    public void cadastroSys(String nm, String usr, String snh, boolean adm) {
        BancoDDS.funcionarios[Funcionario.getTotalid() - 1] = new Funcionario(nm, usr, snh, adm);
        Login.setFuncionarioLogado(BancoDDS.funcionarios[Funcionario.getTotalid() - 1]);
    }




    public boolean isAdmcheck() {
        return admcheck;
    }

    public void setAdmcheck(boolean admcheck) {
        this.admcheck = admcheck;
    }

    public boolean isIdExiste() {
        return idExiste;
    }

    public void setIdExiste(boolean idExiste) {
        this.idExiste = idExiste;
    }

    public boolean isFirstHorario() {
        return firstHorario;
    }

    public void setFirstHorario(boolean firstHorario) {
        this.firstHorario = firstHorario;
    }

    public boolean isFirstCadastro() {
        return firstCadastro;
    }

    public void setFirstCadastro(boolean firstCadastro) {
        this.firstCadastro = firstCadastro;
    }
}
