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
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

public class Principal extends JFrame {
	public JButton btnusuario_1;
	public JButton btnclientes;
	public JButton btnpastel;
	public JButton btnpedidos;
	public JButton btncarrinho;
	public JButton btnrelatorio;
	public Object panelusu;
	public JLabel lblusu;
	private JLabel lblData;
	private JButton btnpedidos_1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		addWindowListener(new WindowAdapter() {
			//evento ativar janela
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
		        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		        lblData.setText(formatador.format(data));
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("")));
		setTitle("Pastelaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 204);
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnclientes = new JButton("Clientes");
		btnclientes.setBounds(168, 47, 100, 23);
		btnclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		panel.setLayout(null);
		
		btnclientes.setToolTipText("Clientes");
		btnclientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnclientes.setIcon(new ImageIcon(Principal.class.getResource("")));
		panel.add(btnclientes);
		
		
		JButton btnpastel = new JButton("Produtos");
		btnpastel.setBounds(293, 47, 100, 23);
		btnpastel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produto = new Produtos();
				produto.setVisible(true);
			}
		});
		btnpastel.setToolTipText("Produtos");
		btnpastel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnpastel.setIcon(new ImageIcon(Principal.class.getResource("")));
		panel.add(btnpastel);
		
		JButton btnpedidos_1 = new JButton("Pedido");
		btnpedidos_1.setBounds(33, 93, 107, 23);
		btnpedidos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pedido pedido = new Pedido();
				pedido.setVisible(true);
			}
		});
		btnpedidos_1.setToolTipText("Pedido");
		btnpedidos_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnpedidos_1.setIcon(new ImageIcon(Principal.class.getResource("")));
		panel.add(btnpedidos_1);
		
		
		
		JButton btncarrinho = new JButton("Carrinho");
		btncarrinho.setBounds(168, 93, 100, 23);
		btncarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Carrinho carrinho = new Carrinho();
				carrinho.setVisible(true);
			}
		});
		btncarrinho.setToolTipText("Carrinho");
		btncarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncarrinho.setIcon(new ImageIcon(Principal.class.getResource("")));
		panel.add(btncarrinho);
		

		
		
		btnrelatorio = new JButton("Relatórios");
		btnrelatorio.setBounds(293, 93, 100, 23);
		btnrelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comanda();
			}
		});
		btnrelatorio.setToolTipText("Relat\u00F3rios");
		btnrelatorio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnrelatorio.setIcon(new ImageIcon(Principal.class.getResource("")));
		panel.add(btnrelatorio);
		
		
		btnusuario_1 = new JButton("Usuários");
		btnusuario_1.setBounds(33, 47, 107, 23);
		btnusuario_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Usuarios user = new Usuarios();
			user.setVisible(true);
		}
	});
		btnusuario_1.setToolTipText("Usu\\u00E1rios");
		btnusuario_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnusuario_1.setIcon(new ImageIcon(Principal.class.getResource("")));
		panel.add(btnusuario_1);
		
		
		Panel panelusu_1 = new Panel();
		panelusu_1.setBounds(0, 256, 499, 38);
		panel.add(panelusu_1);
		
		
		lblusu = new JLabel("");
		lblusu.setBounds(224, 5, 0, 0);
		lblusu.setForeground(Color.WHITE);
		panelusu_1.setLayout(null);
		panelusu_1.add(lblusu);
		
		lblData = new JLabel("");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(229, 5, 0, 0);
		panelusu_1.add(lblData);
		
	}//fim do construtor
	
	/**
	 * Método responsável pela impressão da comanda
	 */
	DAO dao = new DAO();

	// 
	private void comanda() {
		// criar objeto para construir a p�gina pdf
		Document document = new Document();
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("vendas.pdf"));
			document.open();
			// gerar o conte�do do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph("Data de emiss�o: " + formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Vendas por data"));
			document.add(new Paragraph(" "));
			// ... Demais conte�dos (imagem, tabela, gr�fico, etc)
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Data"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Número do pedido"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Total"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Acessar o banco de dados
			String relvendas = "select data_ped,idpedido,quantidade from tbpedidos";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relvendas);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					
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
			Desktop.getDesktop().open(new File("vendas.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
