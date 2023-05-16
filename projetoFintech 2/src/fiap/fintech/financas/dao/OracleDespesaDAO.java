package fiap.fintech.financas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.fintech.financas.Conta;
import fiap.fintech.financas.Despesa;
import fiap.fintech.financas.dao.models.ContaDAO;
import fiap.fintech.financas.dao.models.DespesaDAO;
import fiap.fintech.financas.ConnectionManager;

public class OracleDespesaDAO implements DespesaDAO {

  private Connection connection;

  public OracleDespesaDAO() {
    this.connection = ConnectionManager.obterConexao();
  }

  @Override
  public void cadastrar(Despesa despesa) {
    String sql = "INSERT INTO despesas (id_despesa, id_conta, vl_valor, dt_data, dsc_tipo_despesa) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, despesa.getId());
      stmt.setInt(2, despesa.getConta().getIdConta());
      stmt.setDouble(3, despesa.getValor());
      stmt.setDate(4, despesa.getData());
      stmt.setString(5, despesa.getTipo());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Despesa> listar() {
    String sql = "SELECT id_despesa, id_conta, vl_valor, dt_data, dsc_tipo_despesa FROM despesas";

    List<Despesa> despesas = new ArrayList<>();

    try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        int id = rs.getInt("id_despesa");
        int idConta = rs.getInt("id_conta");
        double valor = rs.getDouble("vl_valor");
        Date data = rs.getDate("dt_data");
        String tipo = rs.getString("dsc_tipo_despesa");

        ContaDAO contaDAO = new OracleContaDAO();
        Conta conta = contaDAO.buscarPorId(idConta);

        Despesa despesa = new Despesa(id, conta, valor, data, tipo);
        despesas.add(despesa);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return despesas;
  }

  @Override
  public void atualizar(Despesa despesa) {
    String sql = "UPDATE despesas SET id_conta = ?, vl_valor = ?, dt_data = ?, dsc_tipo_despesa = ? WHERE id_despesa = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, despesa.getConta().getIdConta());
      stmt.setDouble(2, despesa.getValor());
      stmt.setDate(3, despesa.getData());
      stmt.setString(4, despesa.getTipo());
      stmt.setInt(5, despesa.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void remover(int codigo) {
    String sql = "DELETE FROM despesas WHERE id_despesa = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, codigo);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Despesa buscarPorId(int id) {
    String sql = "SELECT id_despesa, vl_valor, dt_data, dsc_tipo_despesa, c.id_conta, c.id_cliente, c.vl_saldo " +
        "FROM despesas d INNER JOIN contas c ON d.id_conta = c.id_conta " +
        "WHERE id_despesa = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        int idDespesa = rs.getInt("id_despesa");
        double valor = rs.getDouble("vl_valor");
        Date data = rs.getDate("dt_data");
        String tipo = rs.getString("dsc_tipo_despesa");
        int idConta = rs.getInt("id_conta");
        int idCliente = rs.getInt("id_cliente");
        double saldo = rs.getDouble("vl_saldo");

        Conta conta = new Conta(idConta, idCliente, saldo);
        Despesa despesa = new Despesa(idDespesa, conta, valor, data, tipo);
        return despesa;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return null;
  }
}