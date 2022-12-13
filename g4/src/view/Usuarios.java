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

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class Usuarios extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuarios user = new Usuarios();
			user.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			user.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuarios() {
		getContentPane().setEnabled(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("")));
		setTitle("Usu\u00E1rios");
		setResizable(false);
		setBounds(100, 100, 431, 272);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(246, 42, 32, 14);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("User");
		lblNewLabel_5.setBounds(75, 93, 32, 14);
		getContentPane().add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Login");
		lblNewLabel_6.setBounds(75, 42, 46, 14);
		getContentPane().add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("Senha");
		lblNewLabel_7.setBounds(207, 93, 46, 14);
		getContentPane().add(lblNewLabel_7);

		txtloginuser = new JTextField();
		txtloginuser.setBounds(111, 39, 76, 20);
		getContentPane().add(txtloginuser);
		txtloginuser.setColumns(10);

		txtsenhauser = new JPasswordField();
		txtsenhauser.setBounds(246, 90, 100, 20);
		getContentPane().add(txtsenhauser);

		//pesquisar
		btnuser = new JButton("");
		btnuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnuser.setContentAreaFilled(false);
		btnuser.setBorderPainted(false);
		btnuser.setIcon(new ImageIcon(Usuarios.class.getResource("/images/search.png")));
		btnuser.setBounds(197, 39, 32, 23);
		getContentPane().add(btnuser);

		cbouser = new JComboBox();
		cbouser.setBounds(111, 89, 67, 22);
		cbouser.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user", "caixa" }));
		getContentPane().add(cbouser);

		//add
		btnaddusu = new JButton("");
		btnaddusu.setBorderPainted(false);
		btnaddusu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionaruser();
			}
		});
		btnaddusu.setDefaultCapable(false);
		btnaddusu.setContentAreaFilled(false);
		btnaddusu.setIcon(new ImageIcon(Usuarios.class.getResource("/images/create.png")));
		btnaddusu.setBounds(71, 159, 64, 64);
		getContentPane().add(btnaddusu);

		//atualizar
		btnatuusu = new JButton("");
		btnatuusu.setBorderPainted(false);
		btnatuusu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		btnatuusu.setDefaultCapable(false);
		btnatuusu.setContentAreaFilled(false);
		btnatuusu.setIcon(new ImageIcon(Usuarios.class.getResource("/images/update.png")));
		btnatuusu.setBounds(177, 159, 64, 64);
		getContentPane().add(btnatuusu);

		//excluir
		btndelusu = new JButton("");
		btndelusu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluiruser();
			}
		});
		btndelusu.setContentAreaFilled(false);
		btndelusu.setBorderPainted(false);
		btndelusu.setIcon(new ImageIcon(Usuarios.class.getResource("/images/delete.png")));
		btndelusu.setBounds(282, 159, 64, 64);
		getContentPane().add(btndelusu);
		
		txtidusu = new JLabel("");
		txtidusu.setAutoscrolls(true);
		txtidusu.setBorder(new LineBorder(new Color(180, 180, 180)));
		txtidusu.setBackground(new Color(255, 255, 255));
		txtidusu.setBounds(270, 42, 32, 14);
		getContentPane().add(txtidusu);

	}// fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;

	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField txtloginuser;
	private JPasswordField txtsenhauser;
	private JButton btnuser;
	private JComboBox cbouser;
	private JButton btnaddusu;
	private JButton btnatuusu;
	private JButton btndelusu;
	private JLabel txtidusu;

	/**
	 * M�todo respons�vel pela pesquisa de usu�rios
	 */

	private void pesquisarUsuario() {
		if (txtloginuser.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do cliente");
			txtloginuser.requestFocus();
		} else {
			// l�gica principal
			// query principal ( Instru��o SQL)
			String read = "select * from tbusers where login = ?";
			// tratar excess�es sempre que lidar com o banco
			try {
				// estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtloginuser.getText());
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de clientes)
				// rs.next() -> exist�ncia de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtidusu.setText(rs.getString(1));
					cbouser.setSelectedItem(rs.getString(2));
					txtloginuser.setText(rs.getString(3));
					txtsenhauser.setText(rs.getString(4));
					
					btnatuusu.setEnabled(true);
					btndelusu.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
					limparCampos();
					btnaddusu.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo respons�vel por adicionar um novo usu�rio no banco
	 */
	private void adicionaruser() {
		// valida��o da senha (captura segura)
		String capturaSenha = new String(txtsenhauser.getPassword());
		// valida��o
		if (txtloginuser.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque as informa��es do usu�rio");
			txtloginuser.requestFocus();
		} else if (cbouser.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Coloque o perfil do usu�rio");
			cbouser.requestFocus();
		} else if (txtsenhauser.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Coloque a senha do usu�rio");
			txtsenhauser.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into tbusers (login,senha,tipo) values (?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtloginuser.getText());
				pst.setString(2, capturaSenha);
				pst.setString(3, cbouser.getSelectedItem().toString());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usu�rio adicionado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login");
				txtloginuser.setText(null);
				txtloginuser.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void alterarUsuario() {
		// valida��o da senha (captura segura)
		String capturaSenha = new String(txtsenhauser.getPassword());
		// valida��o
		if (txtloginuser.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Coloque as informa��es do usu�rio");
			txtloginuser.requestFocus();
		} else if (cbouser.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Coloque o perfil do usu�rio");
			cbouser.requestFocus();
		} else if (txtsenhauser.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Coloque a senha do usu�rio");
			txtsenhauser.requestFocus();
		} else {
			// l�gica principal
			String update = "update tbusers set login=?, senha=? ,tipe=? where iduser=?;";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtloginuser.getText());
				pst.setString(2, capturaSenha);
				pst.setString(3, cbouser.getSelectedItem().toString());
				pst.setString(4, txtidusu.getText());
				// Executar a query e alterar o usu�rio no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usu�rio alterado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login");
				txtloginuser.setText(null);
				txtloginuser.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * M�todo responsavel por excluir um usu�rio do banco
	 */

	private void excluiruser() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do usu�rio?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbusers where iduser=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtidusu.getText());
				// Executar a query e excluir o usu�rio do banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usu�rio excluido com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * M�todo responsavel por Limpar campos
	 */

	private void limparCampos() {
		txtidusu.setText(null);
		txtsenhauser.setText(null);
		txtloginuser.setText(null);
		cbouser.setSelectedItem("");
	}
}
