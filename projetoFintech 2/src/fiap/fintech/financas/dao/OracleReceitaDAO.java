package fiap.fintech.financas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fiap.fintech.financas.Conta;
import fiap.fintech.financas.Receita;
import fiap.fintech.financas.dao.models.ContaDAO;
import fiap.fintech.financas.dao.models.ReceitaDAO;
import fiap.fintech.financas.ConnectionManager;

public class OracleReceitaDAO implements ReceitaDAO {

  private Connection connection;

  public OracleReceitaDAO() {
    this.connection = ConnectionManager.obterConexao();
  }

  @Override
  public void cadastrar(Receita receita) {
    String sql = "INSERT INTO receitas (id_receita, id_conta, vl_valor, dt_data, dsc_tipo_receita) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, receita.getId());
      stmt.setInt(2, receita.getConta().getIdConta());
      stmt.setDouble(3, receita.getValor());
      stmt.setDate(4, receita.getData());
      stmt.setString(5, receita.getTipo());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Receita> listar() {
    String sql = "SELECT id_receita, id_conta, vl_valor, dt_data, dsc_tipo_receita FROM receitas";

    List<Receita> receitas = new ArrayList<>();

    try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
      while (rs.next()) {
        int id = rs.getInt("id_receita");
        int contaId = rs.getInt("id_conta");
        double valor = rs.getDouble("vl_valor");
        Date data = rs.getDate("dt_data");
        String tipo = rs.getString("dsc_tipo_receita");

        ContaDAO contaDAO = new OracleContaDAO();
        Conta conta = contaDAO.buscarPorId(contaId);
        
        Receita receita = new Receita(id, conta, valor, data, tipo);
        receitas.add(receita);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return receitas;
  }

  @Override
  public void atualizar(Receita receita) {
    String sql = "UPDATE receitas SET id_conta = ?, vl_valor = ?, dt_data = ?, dsc_tipo_receita  = ? WHERE id_receita = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, receita.getConta().getIdConta());
      stmt.setDouble(2, receita.getValor());
      stmt.setDate(3, receita.getData());
      stmt.setString(4, receita.getTipo());
      stmt.setInt(5, receita.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void remover(int codigo) {
    String sql = "DELETE FROM receitas WHERE id_receita = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, codigo);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Receita buscarPorId(int id) {
    String sql = "SELECT id_receita, vl_valor, dt_data, dsc_tipo_receita, c.id_conta, c.id_cliente, c.vl_saldo " +
        "FROM receitas d INNER JOIN contas c ON d.id_conta = c.id_conta " +
        "WHERE id_receita = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        int idReceita = rs.getInt("id_receita");
        double valor = rs.getDouble("vl_valor");
        Date data = rs.getDate("dt_data");
        String tipo = rs.getString("dsc_tipo_receita");
        int idconta = rs.getInt("id_conta");
        int idCliente = rs.getInt("id_cliente");
        double saldo = rs.getDouble("vl_saldo");
        
        Conta conta = new Conta(idconta, idCliente, saldo);
        
        Receita receita = new Receita(idReceita,conta, valor, data, tipo);
        return receita;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return null;
  }
}