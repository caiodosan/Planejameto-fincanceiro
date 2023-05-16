package fiap.fintech.financas.dao.models;

import java.util.List;
import fiap.fintech.financas.Despesa;

public interface DespesaDAO {

  public void cadastrar(Despesa despesa);

  public List<Despesa> listar();

  public void atualizar(Despesa despesa);

  public void remover(int id);

  public Despesa buscarPorId(int id);
}
