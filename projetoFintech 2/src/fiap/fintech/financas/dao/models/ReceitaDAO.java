package fiap.fintech.financas.dao.models;

import java.util.List;
import fiap.fintech.financas.Receita;

public interface ReceitaDAO {

  public void cadastrar(Receita receita);

  public List<Receita> listar();

  public void atualizar(Receita receita);

  public void remover(int id);

  public Receita buscarPorId(int id);

}