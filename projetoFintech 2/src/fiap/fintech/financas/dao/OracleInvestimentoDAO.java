package fiap.fintech.financas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.fintech.financas.Conta;
import fiap.fintech.financas.Investimento;
import fiap.fintech.financas.dao.models.ContaDAO;
import fiap.fintech.financas.dao.models.InvestimentoDAO;
import fiap.fintech.financas.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO {

  private Connection connection;

  public OracleInvestimentoDAO() {
    this.connection = ConnectionManager.obterConexao();
  }

  @Override
  public void cadastrar(Investimento investimento) {
    String sql = "INSERT INTO investimentos (id_investimento, id_conta, vl_valor, dt_data, dsc_tipo_investimento) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, investimento.getId());
      stmt.setInt(2, investimento.getConta().getIdConta());
      stmt.setDouble(3, investimento.getValor());
      stmt.setDate(4, investimento.getData());
      stmt.setString(5, investimento.getTipo());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Investimento> listar() {
    String sql = "SELECT id_investimento, id_conta, vl_valor, dt_data, dsc_tipo_investimento FROM investimentos";

    List<Investimento> investimentos = new ArrayList<>();

    try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        int id = rs.getInt("id_investimento");
        int idConta = rs.getInt("id_conta");
        double valor = rs.getDouble("vl_valor");
        Date data = rs.getDate("dt_data");
        String tipo = rs.getString("dsc_tipo_investimento");

        ContaDAO contaDAO = new OracleContaDAO();
        Conta conta = contaDAO.buscarPorId(idConta);

        Investimento investimento = new Investimento(id, conta, valor, data, tipo);
        investimentos.add(investimento);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return investimentos;
  }

  @Override
  public void atualizar(Investimento investimento) {
    String sql = "UPDATE investimentos SET id_conta = ?, vl_valor = ?, dt_data = ?, dsc_tipo_investimento = ? WHERE id_investimento = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, investimento.getConta().getIdConta());
      stmt.setDouble(2, investimento.getValor());
      stmt.setDate(3, investimento.getData());
      stmt.setString(4, investimento.getTipo());
      stmt.setInt(5, investimento.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void remover(int codigo) {
    String sql = "DELETE FROM investimentos WHERE id_investimento = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, codigo);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Investimento buscarPorId(int id) {
    String sql = "SELECT d.id_investimento, d.vl_valor, d.dt_data, d.dsc_tipo_investimento, c.id_conta, c.id_cliente, c.vl_saldo "
        +
        "FROM investimentos d INNER JOIN contas c ON d.id_conta = c.id_conta " +
        "WHERE id_investimento = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        int idInvestimento = rs.getInt("id_investimento");
        double valor = rs.getDouble("vl_valor");
        Date data = rs.getDate("dt_data");
        String tipo = rs.getString("dsc_tipo_investimento");
        int idConta = rs.getInt("id_conta");
        int idCliente = rs.getInt("id_cliente");
        double saldo = rs.getDouble("vl_saldo");

        Conta conta = new Conta(idConta, idCliente, saldo);
        Investimento investimento = new Investimento(idInvestimento, conta, valor, data, tipo);
        return investimento;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return null;
  }
}