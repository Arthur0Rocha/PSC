package aplicacao;

import java.util.Locale;
import java.util.Scanner;
import repositorio.InstituicaoDAO;
import entidades.Professor;
import entidades.Estudante;
import java.util.List;

public class UsaInstituicao {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(System.in);

        InstituicaoDAO instituicao = new InstituicaoDAO();
        instituicao.conectar();

        char servico;

        do {

            System.out.println("------------------------------------------------------------");
            System.out.println("Escolha um dos serviços abaixo:\n");
            System.out.println("Cadastrar membro na instituição - (Pressione C)");
            System.out.println("Atualizar dados de um membro da instituição - (Pressione A)");
            System.out.println("Listar membros da instituição - (Pressione L)");
            System.out.println("Remover um membro da instituição - (Pressione R)");
            System.out.println("Encerrar os serviços - (Pressione S)\n");

            System.out.print("Serviço: ");
            servico = in.nextLine().charAt(0);
            System.out.println("------------------------------------------------------------");

            switch (servico) {
                // Este caso é responsável por adicionar um membro a instituição.
                case 'C':
                    System.out.println("Escolha um tipo de membro para cadastro:\n");

                    System.out.println("Cadastrar um Professor - (Pressione P)");
                    System.out.println("Cadastrar um Estudante - (Pressione E)\n");

                    System.out.print("Escolha: ");
                    char escolha = in.nextLine().charAt(0);

                    if (escolha == 'P') {
                        Professor professor = new Professor();
                        System.out.println("~~Cadastro de Professor~~\n");

                        System.out.print("Nome: ");
                        professor.setNome(in.nextLine());
                        System.out.print("Membro: ");
                        professor.setMembro(in.nextLine());
                        System.out.print("Semestre: ");
                        professor.setSemestre(in.nextLine());
                        System.out.print("Curso Lecionado: ");
                        professor.setCursoLecionado(in.nextLine());
                        System.out.print("Salário: ");
                        double salario = in.nextDouble();
                        in.nextLine(); // Limpa o buffer do teclado.
                        if (salario < 0) { // Não aceita valores negativos para o salário.
                            System.err.println("Valor informado para salário é inválido.");
                            in.close();
                            System.exit(0); // Caso o valor informado seja inválido, encerra o programa.
                        } else {
                            professor.setSalario(salario);
                            instituicao.adicionarMembro(professor);
                        }
                    } else if (escolha == 'E') {
                        Estudante estudante = new Estudante();
                        System.out.println("~~Cadastro de Estudante~~\n");
                        System.out.print("Nome: ");
                        estudante.setNome(in.nextLine());
                        System.out.print("Membro: ");
                        estudante.setMembro(in.nextLine());
                        System.out.print("Semestre: ");
                        estudante.setSemestre(in.nextLine());
                        System.out.print("Curso: ");
                        estudante.setCurso(in.nextLine());
                        System.out.print("Média Final: ");
                        double mediaFinal = in.nextDouble();
                        in.nextLine();
                        if (mediaFinal < 0) {
                            System.err.println("Valor informado para média final é inválido.");
                            in.close();
                            System.exit(0);
                        } else {
                            estudante.setMediaFinal(mediaFinal);
                            instituicao.adicionarMembro(estudante);
                        }
                    } else {
                        System.err.println("Serviço inválido.");
                        in.close();
                        System.exit(0);
                    }
                    break;

                // Este caso é responsável por atualizar os dados dos membros da instituição.
                case 'A':
                    System.out.println("Escolha um tipo de membro para atualizar os dados:\n");

                    System.out.println("Atualizar dados de Professor - (Pressione P)");
                    System.out.println("Atualizar dados de Estudante - (Pressione E)\n");

                    System.out.print("Escolha: ");
                    escolha = in.nextLine().charAt(0);

                    if (escolha == 'P') {
                        System.out.println("~~Atualizar dados de Professor~~\n");

                        System.out.println("IDs disponíveis para atualização: ");
                        List<Professor> lista = instituicao.listarProfessor();
                        for (int i = 0; i < lista.size(); i++) {
                            System.out.println(lista.get(i));
                        }
                        System.out.print("Atualizar dados do ID: ");
                        int id = in.nextInt();
                        in.nextLine();
                        System.out.println("Escolha um dado para atualizar:\n");

                        System.out.println("Nome - (Pressione N)");
                        System.out.println("Membro - (Pressione M)");
                        System.out.println("Semestre - (Pressione S)");
                        System.out.println("Curso Lecionado - (Pressione C)");
                        System.out.println("Salário - (Pressione P)\n");

                        System.out.print("Escolha: ");
                        escolha = in.nextLine().charAt(0);

                        if (escolha == 'N') {
                            System.out.print("Atualizar Nome: ");
                            String nome = in.nextLine();
                            instituicao.atualizarNomeProfessor(id, nome);
                        } else if (escolha == 'M') {
                            System.out.print("Atualizar Membro: ");
                            String membro = in.nextLine();
                            instituicao.atualizarMembroProfessor(id, membro);
                        } else if (escolha == 'S') {
                            System.out.print("Atualizar Semestre: ");
                            String semestre = in.nextLine();
                            instituicao.atualizarSemestreProfessor(id, semestre);
                        } else if (escolha == 'C') {
                            System.out.print("Atualizar Curso Lecionado: ");
                            String cursoLecionado = in.nextLine();
                            instituicao.atualizarCursoLecionadoProfessor(id, cursoLecionado);
                        } else if (escolha == 'P') {
                            System.out.print("Atualizar Salário: ");
                            double salario = in.nextDouble();
                            in.nextLine();
                            if (salario < 0) {
                                System.err.println("Valor informado para salário é inválido.");
                                in.close();
                                System.exit(0);
                            } else {
                                instituicao.atualizarSalarioProfessor(id, salario);
                            }
                        } else {
                            System.err.println("Serviço inválido.");
                            in.close();
                            System.exit(0);
                        }
                    } else if (escolha == 'E') {
                        System.out.println("~~Atualizar dados de Estudante~~\n");

                        System.out.println("IDs disponíveis para atualização: ");
                        List<Estudante> lista = instituicao.listarAlunos();
                        for (int i = 0; i < lista.size(); i++) {
                            System.out.println(lista.get(i));
                        }
                        System.out.print("Atualizar dados do ID: ");
                        int id = in.nextInt();
                        in.nextLine();
                        System.out.println("Escolha um dado para atualizar:\n");

                        System.out.println("Atualizar Nome - (Pressione N)");
                        System.out.println("Atualizar Membro - (Pressione M)");
                        System.out.println("Atualizar Semestre - (Pressione S)");
                        System.out.println("Atualizar Curso - (Pressione C)");
                        System.out.println("Atualizar Média Final - (Pressione F)\n");

                        System.out.print("Escolha: ");
                        escolha = in.nextLine().charAt(0);

                        if (escolha == 'N') {
                            System.out.print("Atualizar Nome: ");
                            String nome = in.nextLine();
                            instituicao.atualizarNomeEstudante(id, nome);
                        } else if (escolha == 'M') {
                            System.out.print("Atualizar Membro: ");
                            String membro = in.nextLine();
                            instituicao.atualizarMembroEstudante(id, membro);
                        } else if (escolha == 'S') {
                            System.out.print("Atualizar Semestre: ");
                            String semestre = in.nextLine();
                            instituicao.atualizarSemestreEstudante(id, semestre);
                        } else if (escolha == 'C') {
                            System.out.print("Atualizar Curso: ");
                            String curso = in.nextLine();
                            instituicao.atualizarCursoEstudante(id, curso);
                        } else if (escolha == 'F') {
                            System.out.print("Atualizar Média Final: ");
                            double mediaFinal = in.nextDouble();
                            in.nextLine();
                            if (mediaFinal < 0) {
                                System.err.println("Valor informado para média final é inválido.");
                                in.close();
                                System.exit(0);
                            } else {
                                instituicao.atualizarMediaFinalEstudante(id, mediaFinal);
                            }
                        } else {
                            System.out.println("Serviço inválido.");
                            in.close();
                            System.exit(0);
                        }
                    } else {
                        System.err.println("Serviço inválido.");
                        in.close();
                        System.exit(0);
                    }
                    break;

                // Este caso é responsável pela listagem de membros da instituição.
                case 'L':
                    System.out.println("Escolha um membro para listar os dados:\n");

                    System.out.println("Listar dados de Professores (Pressione P)");
                    System.out.println("Listar dados de Estudantes (Pressione E)\n");

                    System.out.print("Escolha: ");
                    escolha = in.nextLine().charAt(0);

                    if (escolha == 'P') {
                        System.out.println("~~Listando dados de Professores~~\n");

                        List<Professor> lista = instituicao.listarProfessor();
                        for (int i = 0; i < lista.size(); i++) { // Usado para percorrer a lista.
                            System.out.println(lista.get(i));
                        }
                    } else if (escolha == 'E') {
                        System.out.println("~~Listando dados de Estudantes~~\n");
                        List<Estudante> lista = instituicao.listarAlunos();
                        for (int i = 0; i < lista.size(); i++) {
                            System.out.println(lista.get(i));
                        }
                    } else {
                        System.err.println("Serviço inválido.");
                        in.close();
                        System.exit(0);
                    }
                    break;

                // Este caso é responsável por remover um membro da instituição.
                case 'R':
                    System.out.println("Escolha um membro para remoção:\n");

                    System.out.println("Remover Professor - (Pressione P)");
                    System.out.println("Remover Estudante - (Pressione E)\n");

                    System.out.print("Escolha: ");
                    escolha = in.nextLine().charAt(0);

                    if (escolha == 'P') {
                        System.out.println("~~Remover Professor~~\n");

                        System.out.print("Escolha um ID para remoção:\n");
                        List<Professor> lista = instituicao.listarProfessor();
                        for (int i = 0; i < lista.size(); i++) {
                            System.out.println(lista.get(i));
                        }
                        System.out.print("Remover o ID: ");
                        int id = in.nextInt();
                        instituicao.removerProfessor(id);
                        in.nextLine();
                    } else if (escolha == 'E') {
                        System.out.println("~~Remover Estudante~~\n");

                        System.out.println("Escolha um ID para remoção:\n");
                        List<Estudante> lista = instituicao.listarAlunos();
                        for (int i = 0; i < lista.size(); i++) {
                            System.out.println(lista.get(i));
                        }
                        System.out.print("Remover o ID: ");
                        int id = in.nextInt();
                        instituicao.removerEstudante(id);
                        in.nextLine();
                    } else {
                        System.err.println("Serviço inválido.");
                        in.close();
                        System.exit(0);
                    }
                    break;

                // Este caso é responsável por encerrar o programa.
                case 'S':
                    in.close();
                    instituicao.desconectar();
                    System.out.println("Sistema encerrado.");
                    System.exit(0);
                    break;

                // Caso o serviço escolhido não seja nenhuma das opções válidas acima.
                default:
                    System.err.println("Serviço escolhido inexistente.");
                    in.close();
                    System.exit(0);
                    break;
            }

        } while (servico != 'S');
    }

}
