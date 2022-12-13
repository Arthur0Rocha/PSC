public class ClientePessoaFisica extends Cliente {

	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Excecoes {
		if (cpf.matches("(\\d{3})(\\d{3})(\\d{3})(\\d{2})|^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")) {
			this.cpf = cpf;
		} else {
			throw new Excecoes("CPF inválido, digite novamente");
		}
	}

	@Override
	public String toString() {
		return "Nome cliente: " + getNome() + "\nCPF: " + getCpf() + "\nEmail: " + getEmail() + "\nData de nascimento: " + getDataNasc() + "\n";
	}

}
