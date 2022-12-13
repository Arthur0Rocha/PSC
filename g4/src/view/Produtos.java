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

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DAO;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.Font;

public class Produtos extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtSabor;
	private JTextField txtCusto;
	private JTextField txtVenda;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	public Produtos() {
		setModal(true);
		setTitle("Produtos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("")));
		setBounds(100, 100, 389, 297);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sabor");
		lblNewLabel_1.setBounds(27, 74, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Custo");
		lblNewLabel_2.setBounds(27, 112, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Venda");
		lblNewLabel_3.setBounds(154, 112, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtSabor = new JTextField();
		txtSabor.setBounds(77, 71, 137, 20);
		getContentPane().add(txtSabor);
		txtSabor.setColumns(10);

		txtCusto = new JTextField();
		txtCusto.setBounds(77, 109, 56, 20);
		getContentPane().add(txtCusto);
		txtCusto.setColumns(10);

		txtVenda = new JTextField();
		txtVenda.setBounds(201, 109, 56, 20);
		getContentPane().add(txtVenda);
		txtVenda.setColumns(10);

		// adicionar pastel
		JButton btnaddpast = new JButton("");
		btnaddpast.setContentAreaFilled(false);
		btnaddpast.setBorderPainted(false);
		btnaddpast.setIcon(new ImageIcon(Produtos.class.getResource("/images/create.png")));
		btnaddpast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarPastel();
			}
		});
		btnaddpast.setBounds(80, 173, 64, 64);
		getContentPane().add(btnaddpast);

		// atualizar pastel
		JButton btnatupast = new JButton("");
		btnatupast.setContentAreaFilled(false);
		btnatupast.setBorderPainted(false);
		btnatupast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPastel();
			}
		});
		btnatupast.setIcon(new ImageIcon(Produtos.class.getResource("/images/update.png")));
		btnatupast.setBounds(174, 173, 64, 64);
		getContentPane().add(btnatupast);

		// excluir pastel
		JButton btndelpast = new JButton("");
		btndelpast.setContentAreaFilled(false);
		btndelpast.setBorderPainted(false);
		btndelpast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPastel();
			}
		});
		btndelpast.setIcon(new ImageIcon(Produtos.class.getResource("/images/delete.png")));
		btndelpast.setBounds(272, 173, 64, 64);
		getContentPane().add(btndelpast);

		JButton btnsabor = new JButton("Pesquisar");
		btnsabor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarSabor();
			}
		});
		btnsabor.setBounds(224, 70, 101, 23);
		getContentPane().add(btnsabor);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Sabores");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(120, 25, 144, 14);
		getContentPane().add(lblNewLabel);
	}
	// fim do construtor

	DAO dao = new DAO();
	private JTable tbpstel;
	private JButton btnsabor;
	private JButton btnaddpast;
	private JButton btndelpast;
	private JButton btnatupast;
	
	// Pesquisar sabor
	private void pesquisarSabor() {
		if (txtSabor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite Sabor do pastel");
			txtSabor.requestFocus();
		} else {
			// l�gica principal
			// query principal ( Instru��o SQL)
			String read = "select * from tbpastel where sabor = ?";
			// tratar excess�es sempre que lidar com o banco
			try {
				// estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtSabor.getText());
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de clientes)
				// rs.next() -> exist�ncia de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtSabor.setText(rs.getString(1));
					txtCusto.setText(rs.getString(2));
					txtVenda.setText(rs.getString(3));

					btnatupast.setEnabled(true);
					btndelpast.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Sabor não cadastrado");
					limparCampos();
					btnaddpast.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// M�todo respons�vel por adicionar um pastel ao banco

	private void adicionarPastel() {

		if (txtSabor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o sabor");
			txtSabor.requestFocus();
		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o custo");
			txtCusto.requestFocus();
		} else if (txtVenda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o valor da venda");
			txtVenda.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into tbpastel(sabor,custo,venda) values (?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtSabor.getText());
				pst.setString(2, txtCusto.getText());
				pst.setString(3, txtVenda.getText());
				// Executar a query e inserir o cliente no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Informações adicionadas");
				// limpar campos
				limparCampos();
				// Encerrar a conex�o
				con.close();

				// no banco tem que estar unique
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Sabor já registrado");
				txtSabor.setText(null);
				txtSabor.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * M�todo responsavel por alterar os dados de um cliente do banco
	 */

	private void alterarPastel() {
		// valida��o
		if (txtSabor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o sabor");
			txtSabor.requestFocus();
		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o custo");
			txtCusto.requestFocus();
		} else if (txtVenda.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o valor da venda");
			txtVenda.requestFocus();
		} else {
			// l�gica principal
			String update = "update tbpastel set sabor=? ,custo=?,venda=? where sabor=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtSabor.getText());
				pst.setString(2, txtCusto.getText());
				pst.setString(3, txtVenda.getText());
				pst.setString(4, txtSabor.getText());
				// Executar a query e alterar o cliente no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
				limparCampos();
				// Encerrar a conex�o
				
				//tem que colocar unique
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Sabor já cadastrado");
				txtSabor.setText(null);
				txtSabor.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por excluir um cliente do banco
	 */

	private void excluirPastel() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do Sabor?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbpastel where sabor=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtSabor.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Sabor excluído com sucesso");
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
		txtSabor.setText(null);
		txtCusto.setText(null);
		txtVenda.setText(null);
	}
	}


