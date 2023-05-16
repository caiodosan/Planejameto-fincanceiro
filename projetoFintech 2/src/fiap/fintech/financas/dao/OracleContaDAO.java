package fiap.fintech.financas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.fintech.financas.Conta;
import fiap.fintech.financas.dao.models.ContaDAO;
import fiap.fintech.financas.ConnectionManager;

public class OracleContaDAO implements ContaDAO {

	private Connection connection;

	public OracleContaDAO() {
		this.connection = ConnectionManager.obterConexao();
	}

	@Override
	public void cadastrar(Conta conta) {
		String sql = "INSERT INTO contas (id_conta, id_cliente, vl_saldo) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, conta.getIdConta());
			stmt.setInt(2, conta.getIdCliente());
			stmt.setDouble(3, conta.getSaldo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Conta> listar() {
		String sql = "SELECT id_conta, id_cliente, vl_saldo FROM contas";

		List<Conta> contas = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				int idConta = rs.getInt("id_conta");
				int idCliente = rs.getInt("id_cliente");
				double saldo = rs.getDouble("vl_saldo");
				Conta conta = new Conta(idConta, idCliente, saldo);
				contas.add(conta);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return contas;
	}

	@Override
	public void atualizar(Conta conta) {
		String sql = "UPDATE contas SET vl_saldo = ? WHERE id_conta = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setDouble(1, conta.getSaldo());
			stmt.setInt(2, conta.getIdConta());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(int codigo) {
		String sql = "DELETE FROM contas WHERE id_conta = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Conta buscarPorId(int codigoBusca) {
		String sql = "SELECT id_conta, id_cliente, vl_saldo FROM contas WHERE id_conta = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, codigoBusca);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int idConta = rs.getInt("id_conta");
				int idCliente = rs.getInt("id_cliente");
				double saldo = rs.getDouble("vl_saldo");
				return new Conta(idConta, idCliente, saldo);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

}