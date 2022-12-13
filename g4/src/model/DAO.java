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
package model;
	
	import java.sql.Connection;
	import java.sql.DriverManager;

	
	public class DAO {

			private String driver = "com.mysql.cj.jdbc.Driver";
			private String url = "jdbc:mysql://127.0.0.1:3306/Pastelaok";
			private String user = "root";
			private String password = "";
			
		/**
		 * 
		 */
			
			public Connection conectar() {	
				Connection con = null;
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, user, password);
					return con;
				} catch (Exception e) {
					System.out.println(e);
					return null;
				}
			}

		}
