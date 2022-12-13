import java.util.InputMismatchException;
import java.util.Scanner;

public class Cadastrar extends Listar implements BancoDeDados {

	private Scanner in = new Scanner(System.in);
	private boolean execute;

	public Cadastrar() throws Excecoes {
		execute = true;

		System.out.println("BEM VINDO AO CADASTRO DE USUÁRIOS");

		while (execute) {
			String opcao = menu();

			if (opcao.equalsIgnoreCase("n")) {
				cadastrar();
			} else if (opcao.equalsIgnoreCase("l")) {
				listarCadastros();
			} else if (opcao.equalsIgnoreCase("x")) {
				execute = false;
				System.out.println("Programa encerrado!");
			} else {
				System.out.println("\nOpção Inválida!!!\n");
			}
		}

	}

	public String menu() {
		System.out.println("Selecione a opção:");
		System.out.println("N - Novo cadastro");
		System.out.println("L - Listar cadastros");
		System.out.println("X - Sair");
		return in.nextLine();
	}

	public String menuPfOuPj() {
		System.out.println("Você é cliente PF ou PJ?");
		System.out.println("PF - Pessoa Física");
		System.out.println("PJ - Pessoa Jurídica");
		return in.nextLine();
	}

	public void cadastrar() throws Excecoes {
		boolean cadastrando = true;

		while (cadastrando) {
			String pfOuPj = menuPfOuPj();

			System.out.println("Cadastro de Usuário");
			System.out.println("--------------------");

			if (pfOuPj.equalsIgnoreCase("pf")) {
				ClientePessoaFisica cliente = new ClientePessoaFisica();

				boolean nomeInvalido = true;
				while (nomeInvalido) {
					try {
						cliente.setNome(input("Nome:"));
						nomeInvalido = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean cpfInvalido = true;
				while (cpfInvalido) {
					try {
						cliente.setCpf(input("CPF:"));
						cpfInvalido = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean emailInvalido = true;
				while (emailInvalido) {
					try {
						cliente.setEmail(input("E-mail:"));
						emailInvalido = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean dataNascInvalida = true;
				while (dataNascInvalida) {
					try {
						cliente.setDataNasc(input("Data de nascimento:"));
						dataNascInvalida = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				String cadastrar = input("Confirmar cadastro (S/N) ?");

				if (cadastrar.equalsIgnoreCase("s")) {
					System.out.println("Cadastro adicionado com sucesso!!!");
					clientesPF.add(cliente);
					clientes.add(cliente);
				} else if (cadastrar.equalsIgnoreCase("n")) {
					System.out.println("Cadastro cancelado!!!");
				} else {
					System.out.println("\nOpção inválida, vou ignorar o cadastro para você digitar novamente!!!\n");
				}
			} else if (pfOuPj.equalsIgnoreCase("pj")) {
				ClientePessoaJuridica cliente = new ClientePessoaJuridica();

				boolean nomeInvalido = true;
				while (nomeInvalido) {
					try {
						cliente.setNome(input("Nome:"));
						nomeInvalido = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean cnpjInvalido = true;
				while (cnpjInvalido) {
					try {
						cliente.setCnpj(input("CNPJ:"));
						cnpjInvalido = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean emailInvalido = true;
				while (emailInvalido) {
					try {
						cliente.setEmail(input("E-mail:"));
						emailInvalido = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean dataNascInvalida = true;
				while (dataNascInvalida) {
					try {
						cliente.setDataNasc(input("Data de nascimento:"));
						dataNascInvalida = false;
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				boolean test = true;
				while (test) {
					try {
						System.out.println("Quantidade de filiais");
						cliente.setQtdFiliais(input(in.nextInt()));
						test = false;
						in.nextLine();
					} catch (InputMismatchException e) {
						System.out.println("Neste campo nós só aceitamos números");
						in.nextLine();
					} catch (Excecoes e) {
						e.printMessage();
					}
				}

				String cadastrar = input("Confirmar cadastro (S/N) ?");

				if (cadastrar.equalsIgnoreCase("s")) {
					System.out.println("Cadastro adicionado com sucesso!!!");
					clientesPJ.add(cliente);
					clientes.add(cliente);
				} else if (cadastrar.equalsIgnoreCase("n")) {
					System.out.println("Cadastro cancelado!!!");
				} else {
					System.out.println("\nOpção inválida, vou ignorar o cadastro para você digitar novamente!!!\n");
				}
			}

			String continua = input("Continuar adcionando cadastros (S/N) ?");
			if (continua.equalsIgnoreCase("N")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")) {
				// se for s sai do if e volta para o inicio do while
			} else {
				System.out.println("\nOpção inválida, eu vou sair só porque você não colabora !!! \n");
				cadastrando = false;
			}
		}
	}

	private String input(String label) {
		System.out.println(label);
		return in.nextLine();
	}

	private int input(int numero) {
		return numero;
	}

}
