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

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class Carrinho extends JFrame {

	private JTextField txtcarrped;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Carrinho dialog = new Carrinho();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Carrinho() {
		setModal(true);
		setTitle("Carrinho");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Carrinho.class.getResource("")));
		setBounds(100, 100, 388, 246);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID pedido");
		lblNewLabel.setBounds(44, 25, 56, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Pagamento");
		lblNewLabel_3.setBounds(44, 64, 77, 14);
		getContentPane().add(lblNewLabel_3);

		txtcarrped = new JTextField();
		txtcarrped.setBounds(110, 22, 63, 20);
		getContentPane().add(txtcarrped);
		txtcarrped.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Situação");
		lblNewLabel_4.setBounds(192, 64, 61, 14);
		getContentPane().add(lblNewLabel_4);

		JButton btncomanda = new JButton("Emitir Comanda");
		btncomanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comanda();
			}
		});
		btncomanda.setBounds(217, 139, 126, 23);
		getContentPane().add(btncomanda);

		JButton btncarrped = new JButton("Procurar");
		btncarrped.setBounds(182, 21, 89, 23);
		btncarrped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procurarPedido();
			}
		});
		getContentPane().add(btncarrped);

		cbopag = new JComboBox();
		cbopag.setBounds(110, 60, 70, 22);
		cbopag.setModel(new DefaultComboBoxModel(new String[] { "", "Pix", "Débito", "Crédito" }));
		getContentPane().add(cbopag);

		cbosit = new JComboBox();
		cbosit.setBounds(244, 60, 81, 22);
		cbosit.setModel(new DefaultComboBoxModel(new String[] { "", "Pago", "Pendente" }));
		getContentPane().add(cbosit);

		btnfechar = new JButton("Fechar Pedido");
		btnfechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharpedido();
			}
		});
		btnfechar.setBounds(44, 139, 115, 23);
		getContentPane().add(btnfechar);

		JLabel lblNewLabel_1 = new JLabel("Pedido de");
		lblNewLabel_1.setBounds(44, 102, 56, 14);
		getContentPane().add(lblNewLabel_1);

		info = new JLabel("");
		info.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		info.setBounds(110, 100, 160, 16);
		getContentPane().add(info);

		btnpedfeito = new JButton("Consultar pedido feito");
		btnpedfeito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pedidofeito();
			}
		});
		btnpedfeito.setBounds(108, 173, 163, 23);
		getContentPane().add(btnpedfeito);

	}// fim do construtor

	private void setModal(boolean b) {
		// TODO Auto-generated method stub

	}

	DAO dao = new DAO();
	private JComboBox cbopag;
	private JComboBox cbosit;
	private JLabel info;
	private JButton btnfechar;
	private JButton btnpedfeito;

	/**
	 * M�todo respons�vel por pesquisar o pedido dos clientes
	 * 
	 */

	private void procurarPedido() {
		if (txtcarrped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número do pedido");
			txtcarrped.requestFocus();
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
				pst.setString(1, txtcarrped.getText());
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de clientes)
				// rs.next() -> exist�ncia de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtcarrped.setText(rs.getString(1));
					info.setText(rs.getString(3));
					cbopag.setSelectedItem(rs.getString(2));
					cbosit.setSelectedItem(rs.getString(4));
				} else {
					JOptionPane.showMessageDialog(null, "Pedido não cadastrado");
					limparCampos();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void pedidofeito() {
		if (txtcarrped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número do pedido");
			txtcarrped.requestFocus();
		} else {
			// l�gica principal
			// query principal ( Instru��o SQL)
			String read = "select * from carrinho where idpedido = ?";
			// tratar excess�es sempre que lidar com o banco
			try {
				// estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtcarrped.getText());
				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (exist�ncia de clientes)
				// rs.next() -> exist�ncia de clientes
				limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtcarrped.setText(rs.getString(1));
					cbopag.setSelectedItem(rs.getString(2));
					cbosit.setSelectedItem(rs.getString(3));
				} else {
					JOptionPane.showMessageDialog(null, "Pedido não cadastrado");
					limparCampos();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	// M�todo respons�vel por fechar um pedido para emitir o cupom

	private void fecharpedido() {

		// valida��o
		if (txtcarrped.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o número do pedido");
			txtcarrped.requestFocus();
		} else if (cbopag.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe a forma de pagamento");
			cbopag.requestFocus();
		} else if (cbosit.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe a situação do pagamento");
			cbosit.requestFocus();
		} else {
			// l�gica principal
			String create = "insert into carrinho (idpedido, pagamento, situacao) values (?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtcarrped.getText());
				pst.setString(2, cbopag.getSelectedItem().toString());
				pst.setString(3, cbosit.getSelectedItem().toString());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				JOptionPane.showMessageDialog(null, "Pedido Fechado");
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsável pela impressão da comanda
	 */

	private void comanda() {
		// criar objeto para construir a p�gina pdf
		Document document = new Document();
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("comanda.pdf"));
			document.open();
			// gerar o conte�do do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph("Data de emissão: " + formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Pedido"));
			document.add(new Paragraph(" "));
			// ... Demais conte�dos (imagem, tabela, gr�fico, etc)
			PdfPTable tabela = new PdfPTable(31);
			PdfPCell col1 = new PdfPCell(new Paragraph(""));

			tabela.addCell(col1);

			// Acessar o banco de dados
			String relvendas = "select * from carrinho";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relvendas);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o c�digo independente do resultado OK ou n�o
			document.close();
		}

		// abrir o documento que foi gerado no leitor padr�o de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("comanda.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * M�todo responsavel por Limpar campos
	 */

	private void limparCampos() {
		txtcarrped.setText(null);
		info.setText(null);
		cbopag.setSelectedItem("");
		cbosit.setSelectedItem("");
	}
}
