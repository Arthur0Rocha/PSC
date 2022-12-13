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
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.DAO;

public class Pedido extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedido dialog = new Pedido();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTextField txtidped;
	private JTextField txtcliped;
	private JTextField txtpastped;

	public Pedido() {
		
		setModal(true);
		setTitle("Pedido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Pedido.class.getResource("")));
		setBounds(100, 100, 452, 402);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Id Pedido");
		lblNewLabel.setBounds(32, 39, 60, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Id cliente");
		lblNewLabel_1.setBounds(32, 103, 60, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Pastel");
		lblNewLabel_2.setBounds(32, 144, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtidped = new JTextField();
		txtidped.setBounds(102, 36, 86, 20);
		getContentPane().add(txtidped);
		txtidped.setColumns(10);

		txtcliped = new JTextField();
		txtcliped.setBounds(102, 100, 86, 20);
		getContentPane().add(txtcliped);
		txtcliped.setColumns(10);

		txtpastped = new JTextField();
		txtpastped.setBounds(102, 141, 86, 20);
		getContentPane().add(txtpastped);
		txtpastped.setColumns(10);

		txtareaobs = new JTextArea();
		txtareaobs.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		txtareaobs.setBounds(112, 172, 169, 37);
		getContentPane().add(txtareaobs);

		JLabel lblNewLabel_3 = new JLabel("Observação");
		lblNewLabel_3.setBounds(32, 184, 71, 14);
		getContentPane().add(lblNewLabel_3);

		JButton btnverped = new JButton("Ver pedido");
		btnverped.setOpaque(false);
		btnverped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarPedido();
			}
		});
		btnverped.setBounds(199, 35, 103, 23);
		getContentPane().add(btnverped);

		// Botão add pedido
		JButton btnaddped = new JButton("");
		btnaddped.setContentAreaFilled(false);
		btnaddped.setBorderPainted(false);
		btnaddped.setIcon(new ImageIcon(Pedido.class.getResource("/images/create.png")));
		btnaddped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarPedido();
			}
		});
		btnaddped.setBounds(39, 272, 64, 64);
		getContentPane().add(btnaddped);

		// Botão add pedido
		JButton btnatuped = new JButton("");
		btnatuped.setIcon(new ImageIcon(Pedido.class.getResource("/images/update.png")));
		btnatuped.setBorderPainted(false);
		btnatuped.setContentAreaFilled(false);
		btnatuped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPedido();
			}
		});
		btnatuped.setBounds(191, 272, 64, 64);
		getContentPane().add(btnatuped);

		// Excluir pedido
		JButton btndelped = new JButton("");
		btndelped.setIcon(new ImageIcon(Pedido.class.getResource("/images/delete.png")));
		btndelped.setContentAreaFilled(false);
		btndelped.setBorderPainted(false);
		btndelped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPedido();
			}
		});
		btndelped.setBounds(340, 272, 64, 64);
		getContentPane().add(btndelped);
		
		JLabel lblNewLabel_4 = new JLabel("NOVO PEDIDO");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(162, 69, 103, 20);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Quantidade");
		lblNewLabel_5.setBounds(291, 144, 71, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtquant = new JTextField();
		txtquant.setBounds(358, 141, 46, 20);
		getContentPane().add(txtquant);
		txtquant.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Valor");
		lblNewLabel_6.setBounds(198, 144, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtvalor = new JTextField();
		txtvalor.setBounds(235, 141, 46, 20);
		getContentPane().add(txtvalor);
		txtvalor.setColumns(10);
		
		JLabel lbl = new JLabel("Total");
		lbl.setBounds(32, 237, 46, 14);
		getContentPane().add(lbl);
		
		lbldata = new JLabel("");
		lbldata.setBounds(309, 11, 117, 14);
		getContentPane().add(lbldata);
		
		txttotal = new JTextField();
		txttotal.setBounds(73, 234, 86, 20);
		getContentPane().add(txttotal);
		txttotal.setColumns(10);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		//JLabel lblNewLabel_4 = new JLabel(sdf.format(new Date()));
		//lblNewLabel_4.setBounds(32, 14, 134, 14);
		//getContentPane().add(lblNewLabel_4);

	}// fim do construtor

	private void setModal(boolean b) {
		// TODO Auto-generated method stub
		
	}

	DAO dao = new DAO();
	private JButton btnaddped;
	private JButton btnatuped;
	private JButton btndelped;
	private JButton btnverped;
	private JTextArea txtareaobs;
	private JTextField txtquant;
	private JTextField txtvalor;
	private JLabel lbldata;
	private JTextField txttotal;


	/**
	 * M�todo respons�vel por pesquisar o pedido dos clientes
	 * 
	 */
	
	private void pesquisarPedido() {
		if (txtidped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número do pedido");
			txtidped.requestFocus();
		} else {
			// l�gica principal
			// query principal ( Instru��o SQL)
			String read = "select * from tbpedidos where idpedido = ?";
			// tratar excess�es sempre que lidar com o banco
			try {
				// estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtidped.getText());
				
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de clientes)
				// rs.next() -> exist�ncia de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtidped.setText(rs.getString(1));
					lbldata.setText(rs.getString(2));
					txtcliped.setText(rs.getString(3));
					txtpastped.setText(rs.getString(4));
					txtvalor.setText(rs.getString(6));
					txtquant.setText(rs.getString(5));
					txtareaobs.setText(rs.getString(7));
					txttotal.setText(rs.getString(8));

					btnatuped.setEnabled(true);
					btndelped.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Pedido não cadastrado");
					limparCampos();
					btnaddped.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// M�todo respons�vel por adicionar um cliente ao banco

	private void adicionarPedido() {

		if (txtcliped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o ID do cliente");
			txtcliped.requestFocus();
		} else if (txtpastped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o número do sabor do pastel");
			txtpastped.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into tbpedidos (nome, sabor, quantidade, valor, obs, total) values (?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtcliped.getText());
				pst.setString(2, txtpastped.getText());
				pst.setString(4, txtvalor.getText());
				pst.setString(3, txtquant.getText());
				pst.setString(5, txtareaobs.getText());
				pst.setString(6, txttotal.getText());
				// Executar a query e inserir o cliente no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Pedido feito");
				// limpar campos
				limparCampos();
				// Encerrar a conex�o
				con.close();
			}
			catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Pedido já feito");
				txtidped.setText(null);
				txtidped.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
	}
	
	/**
	 * M�todo responsavel por alterar os dados de um cliente do banco
	 */

	private void alterarPedido() {
		// valida��o
		if (txtcliped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o ID do cliente");
			txtcliped.requestFocus();
		} else if (txtpastped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o número do sabor do pastel");
			txtpastped.requestFocus();
		} else {
			// l�gica principal
			String update = "update tbpedidos set nome=?, sabor=?, valor=?, quantidade=?,obs=?, total=? where idpedido=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtcliped.getText());
				pst.setString(2, txtpastped.getText());
				pst.setString(3, txtvalor.getText());
				pst.setString(4, txtquant.getText());
				pst.setString(5, txtareaobs.getText());
				pst.setString(6, txttotal.getText());
				pst.setString(7, txtidped.getText());
				// Executar a query e alterar o cliente no banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Pedido alterado");
				limparCampos();

				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo responsavel por excluir um cliente do banco
	 */

	private void excluirPedido() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do pedido?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbpedidos where idpedido=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtidped.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirma��o
				JOptionPane.showMessageDialog(null, "Pedido excluido");
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
		txtidped.setText(null);
		txtidped.setText(null);
		txtvalor.setText(null);
		txtquant.setText(null);
		txtpastped.setText(null);
		txtcliped.setText(null);
		txtareaobs.setText(null);
		txttotal.setText(null);

	}
}
