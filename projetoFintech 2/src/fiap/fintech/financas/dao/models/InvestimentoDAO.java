package fiap.fintech.financas.dao.models;

import java.util.List;
import fiap.fintech.financas.Investimento;

public interface InvestimentoDAO {

  public void cadastrar(Investimento investimento);

  public List<Investimento> listar();

  public void atualizar(Investimento investimento);

  public void remover(int id);

  public Investimento buscarPorId(int id);
}
