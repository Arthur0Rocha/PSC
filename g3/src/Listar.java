
public class Listar implements BancoDeDados {
	public void listarCadastros() {
		System.out.println("--------------------------------");

		if (clientes.size() == 0 && clientesPF.size() == 0 && clientesPJ.size() == 0) {
			System.out.println("\nNão existem cadastros de clientes !!!\n");
		}

		if (clientes.size() > 0) {
			System.out.println("\nLista de Cadastros de clientes\n");
			for (int i = 0; i < clientes.size(); i++) {
				System.out.println(clientes.get(i));
			}
			System.out.println("\nFim da lista\n");
			System.out.println("--------------------------------");

			if (clientesPF.size() == 0) {
				System.out.println("\nNão existe cadastro de clientes PF!!!\n");
			} else {
				System.out.println("\nLista de Cadastros de clientes PF\n");
				for (int i = 0; i < clientesPF.size(); i++) {
					System.out.println(clientesPF.get(i));
				}
				System.out.println("\nFim da lista\n");
			}

			System.out.println("--------------------------------");

			if (clientesPJ.size() == 0) {
				System.out.println("\nNão existe cadastro de clientes PJ!!!\n");
			} else {
				System.out.println("\nLista de Cadastros de cliente PJ\n");
				for (int i = 0; i < clientesPJ.size(); i++) {
					System.out.println(clientesPJ.get(i));
				}
				System.out.println("\nFim da lista\n");
				System.out.println("--------------------------------");
			}

		}

	}
}
