package fiap.fintech.financas.dao.factory;

import fiap.fintech.financas.dao.models.ContaDAO;
import fiap.fintech.financas.dao.OracleContaDAO;
import fiap.fintech.financas.dao.OracleDespesaDAO;
import fiap.fintech.financas.dao.OracleInvestimentoDAO;
import fiap.fintech.financas.dao.OracleReceitaDAO;
import fiap.fintech.financas.dao.models.DespesaDAO;
import fiap.fintech.financas.dao.models.InvestimentoDAO;
import fiap.fintech.financas.dao.models.ReceitaDAO;

public abstract class DAOFactory {
  public static ContaDAO getContaDAO() {
    return new OracleContaDAO();
  }

  public static ReceitaDAO getReceitaDAO() {
    return new OracleReceitaDAO();
  }

  public static DespesaDAO getDespesaDAO() {
    return new OracleDespesaDAO();
  }

  public static InvestimentoDAO getInvestimentoDAO() {
    return new OracleInvestimentoDAO();
  }
}
