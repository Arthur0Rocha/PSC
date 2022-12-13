import java.util.ArrayList;

public interface BancoDeDados {
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<ClientePessoaFisica> clientesPF = new ArrayList<ClientePessoaFisica>();
	ArrayList<ClientePessoaJuridica> clientesPJ = new ArrayList<ClientePessoaJuridica>();
}
