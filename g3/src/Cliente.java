public abstract class Cliente implements BancoDeDados {

	private String nome;
	private String email;
	private String dataNasc;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Excecoes {
		if (nome.matches("^(([a-zA-Zà-ùÀ-Ù]+)(\\ )?){0,7}$") && nome.length() >= 3) {
			this.nome = nome;
		} else {
			throw new Excecoes("Nome inválido, digite novamente");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Excecoes {
		if (email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			this.email = email;
		} else {
			throw new Excecoes("E-mail inválido, digite um e-mail válido");
		}

	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) throws Excecoes {
		if (dataNasc.matches("^(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/([12][0-9]{3})$|^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])([12][0-9]{3})$")) {
			this.dataNasc = dataNasc;
		} else {
			throw new Excecoes("Data de nascimento inválida, digite um data no formato DD/MM/YYYY ou sem /");
		}
	}

}