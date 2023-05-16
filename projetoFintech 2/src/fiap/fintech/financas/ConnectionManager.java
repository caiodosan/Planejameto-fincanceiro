package fiap.fintech.financas;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	private static ConnectionManager instance;

	public static Connection obterConexao() {
		Connection conexao = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM96420", "170600");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conexao;
	}

	public static ConnectionManager gerenciar() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

}
