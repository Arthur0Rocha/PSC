public class ClientePessoaJuridica extends Cliente {
	private String cnpj;
	private int qtdFiliais;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) throws Excecoes {
		if (cnpj.matches("(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)|(^\\d{2}\\d{3}\\d{3}\\d{4}\\d{2}$)")) {
			this.cnpj = cnpj;
		} else {
			throw new Excecoes("CNPJ inválido, digite novamente");
		}
	}

	public int getQtdFiliais() {
		return qtdFiliais;
	}

	public void setQtdFiliais(int qtdFiliais) throws Excecoes {
		if (qtdFiliais >= 1) {
			this.qtdFiliais = qtdFiliais;
		} else {
			throw new Excecoes("Você deve cadastrar no minímo uma filial para efetuar o cadastro, digite novamente");
		}
	}

	@Override
	public String toString() {
		return "Nome cliente: " + getNome() + "\nCNPJ: " + getCnpj() + "\nEmail: " + getEmail() + "\nData de nascimento: " + getDataNasc() + "\nQuantidade de filiais: " + getQtdFiliais() + "\n";
	}
}
