
public class Excecoes extends Exception {

	private String msg = "";

	public Excecoes(String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return this.msg;
	}

	public void printMessage() {
		System.out.println(msg);
	}

}
