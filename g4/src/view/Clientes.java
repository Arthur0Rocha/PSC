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

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.awt.event.ActionEvent;

import view.Principal;
import model.DAO;

public class Clientes extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdCli;
	private JTextField txtNomeCli;
	private JTextField txtCep;
	private JTextField txtEnd;
	private JTextField txtNum;
	private JTextField txtTel;
	private JButton bntadd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("")));
		setTitle("Clientes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(26, 23, 46, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(197, 23, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Endereço");
		lblNewLabel_2.setBounds(26, 60, 58, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("CEP");
		lblNewLabel_3.setBounds(26, 100, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Número");
		lblNewLabel_4.setBounds(226, 100, 46, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Telefone");
		lblNewLabel_5.setBounds(26, 134, 58, 14);
		getContentPane().add(lblNewLabel_5);

		txtIdCli = new JTextField();
		txtIdCli.setBounds(52, 20, 86, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		txtNomeCli = new JTextField();
		txtNomeCli.setBounds(235, 20, 155, 20);
		getContentPane().add(txtNomeCli);
		txtNomeCli.setColumns(10);

		txtCep = new JTextField();
		txtCep.setBounds(84, 97, 123, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		txtEnd = new JTextField();
		txtEnd.setBounds(85, 57, 305, 20);
		getContentPane().add(txtEnd);
		txtEnd.setColumns(10);

		txtNum = new JTextField();
		txtNum.setBounds(282, 97, 108, 20);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);

		txtTel = new JTextField();
		txtTel.setBounds(82, 131, 142, 20);
		getContentPane().add(txtTel);
		txtTel.setColumns(10);

		// botão buscar pelo id
		JButton btnId = new JButton("");
		btnId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarClientes();
			}
		});
		btnId.setContentAreaFilled(false);
		btnId.setBorderPainted(false);
		btnId.setIcon(new ImageIcon(Clientes.class.getResource("/images/search.png")));
		btnId.setBounds(148, 11, 32, 32);
		getContentPane().add(btnId);

		// botão adicionar
		bntadd = new JButton("");
		bntadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarClientes();
			}
		});
		bntadd.setBorderPainted(false);
		bntadd.setContentAreaFilled(false);
		bntadd.setIcon(new ImageIcon(Clientes.class.getResource("/images/create.png")));
		bntadd.setBounds(74, 186, 64, 64);
		getContentPane().add(bntadd);

		// Botão excluir
		btndel = new JButton("");
		btndel.setIcon(new ImageIcon(Clientes.class.getResource("/images/delete.png")));
		btndel.setContentAreaFilled(false);
		btndel.setBorderPainted(false);
		btndel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btndel.setBounds(301, 186, 64, 64);
		getContentPane().add(btndel);

		btnatu = new JButton("");
		btnatu.setBorderPainted(false);
		btnatu.setContentAreaFilled(false);
		btnatu.setIcon(new ImageIcon(Clientes.class.getResource("/images/update.png")));
		btnatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnatu.setBounds(193, 186, 64, 64);
		getContentPane().add(btnatu);

	}// fim do construtor

	DAO dao = new DAO();
	private JTable tblclientes;
	private JButton btnId;
	private JButton btnCep;
	private JButton btndel;
	private JButton btnatu;

	/**
	 * M�todo respons�vel por pesquisar com o id dos
	 * clientes
	 */

	private void pesquisarClientes() {
		if (txtIdCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do cliente");
			txtIdCli.requestFocus();
		} else {
			// l�gica principal
			// query principal ( Instru��o SQL)
			String read = "select * from tbclientes where idcli = ?";
			// tratar excess�es sempre que lidar com o banco
			try {
				// estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtIdCli.getText());
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de clientes)
				// rs.next() -> exist�ncia de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtIdCli.setText(rs.getString(1));
					txtNomeCli.setText(rs.getString(2));
					txtEnd.setText(rs.getString(3));
					txtNum.setText(rs.getString(4));
					txtTel.setText(rs.getString(5));
					txtCep.setText(rs.getString(6));

					btnatu.setEnabled(true);
					btndel.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
					limparCampos();
					bntadd.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// M�todo respons�vel por adicionar um cliente ao banco

	private void adicionarClientes() {

		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome do cliente");
			txtNomeCli.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP do cliente");
			txtCep.requestFocus();
		} else if (txtTel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do cliente");
			txtTel.requestFocus();
		} else if (txtEnd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Endereço do cliente");
			txtEnd.requestFocus();
		} else if (txtNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Número do cliente");
			txtNum.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into tbclientes(nome,endereco,numero,telefone,cep) values (?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtEnd.getText());
				pst.setString(3, txtNum.getText());
				pst.setString(4, txtTel.getText());
				pst.setString(5, txtCep.getText());
				// Executar a query e inserir o cliente no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
				// limpar campos
				limparCampos();
			
				// Encerrar a conex�o
				con.close();

				// no banco tem que estar unique
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Mesmo Nome.\nDigite outro");
				txtNomeCli.setText(null);
				txtNomeCli.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por alterar os dados de um cliente do banco
	 */

	private void alterarCliente() {
		// valida��o
		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome do cliente");
			txtNomeCli.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP do cliente");
			txtCep.requestFocus();
		} else if (txtTel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do cliente");
			txtTel.requestFocus();
		} else if (txtEnd.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Endereço do cliente");
			txtEnd.requestFocus();
		} else if (txtNum.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o Número do cliente");
			txtNum.requestFocus();
		} else {
			// l�gica principal
			String update = "update tbclientes set nome=?, endereco=?, numero=?, telefone=?, cep=? where idCli=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtEnd.getText());
				pst.setString(3, txtNum.getText());
				pst.setString(4, txtTel.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtIdCli.getText());
				// Executar a query e alterar o cliente no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
				limparCampos();
			
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Nome em uso.\nEscolha outro");
				txtNomeCli.setText(null);
				txtNomeCli.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por excluir um cliente do banco
	 */

	private void excluirCliente() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbclientes where idCli=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtIdCli.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				limparCampos();
			
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
		txtIdCli.setText(null);
		txtNomeCli.setText(null);
		txtEnd.setText(null);
		txtNum.setText(null);
		txtTel.setText(null);
		txtCep.setText(null);
		bntadd.setEnabled(false);
		btnatu.setEnabled(false);
		btndel.setEnabled(false);

	}
}
