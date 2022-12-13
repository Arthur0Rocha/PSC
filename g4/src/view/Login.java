/**
	 * A3
	 * 
	 * Author:
	 * Guilherme Henrique da Silva - 12522211128
	 * Miguel Augusto Romano Lourenço - 12522216366
	 * Gabriel Aparecido de Souza - 12522210613
	 * Renan Rodrigues Xavier - 12522223127
	 * Matheus Henrique Ferreira Rosa - 12522223536
	 * 
	 */
package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import model.DAO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame {
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});

		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 285);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		txtLogin = new JTextField();
		txtLogin.setBounds(142, 44, 113, 20);
		panel.add(txtLogin);
		txtLogin.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setBounds(132, 148, 89, 23);
		panel.add(btnEntrar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(142, 96, 113, 20);
		panel.add(txtSenha);

		lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(86, 47, 46, 14);
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(86, 99, 46, 14);
		panel.add(lblNewLabel_1);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/images/dboff.png")));
		lblStatus.setBounds(297, 165, 64, 58);
		panel.add(lblStatus);

	}

	DAO dao = new DAO();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblStatus;

	/**
	 * M�todo usado para verificar o status do servidor
	 */

	private void status() {
		try {
			// abrir a conex�o
			Connection con = dao.conectar();
			if (con == null) {
				// escolher a imagem databaseoff
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/images/dboff.png")));
			} else {
				// escolher a imagem databaseon
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/images/dbon.png")));
			}
			// N�o esquecer de fechar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * M�todo usado para autentica��o de um usu�rio
	 */

	private void logar() {

		String capturaSenha = new String(txtSenha.getPassword());

		// valida��o
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o login");
			txtLogin.requestFocus();
		} else if (txtSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Insira a senha");
			txtSenha.requestFocus();
		} else {
			// l�gica principal
			String read = "select * from tbusers where login=? and senha=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de usu�rio)
				// rs.next() -> exist�ncia de usu�rio
				if (rs.next()) {
					// Verificar o perfil do usu�rio
					String perfil = rs.getString(2);
					// System.out.println(perfil);
					Principal principal = new Principal();
					if (perfil.equals("admin")) {
						// abrir a tela principal
						principal.setVisible(true);
						// habilitar recursos
						principal.btnrelatorio.setEnabled(true);
						principal.btnusuario_1.setEnabled(true);
						// fechar a tela de login
						this.dispose();
					} else {
						// abrir a tela principal
						principal.btnrelatorio.setEnabled(false);
						principal.btnusuario_1.setEnabled(false);
						principal.setVisible(true);
						// fechar a tela de login
						this.dispose();
					}

					// encerrar a conex�o
					con.close();

				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)");
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

} // Fim do c�digo
