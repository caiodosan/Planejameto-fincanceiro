package fiap.fintech.financas.dao.models;

import java.util.List;
import fiap.fintech.financas.Conta;

public interface ContaDAO {

	public void cadastrar(Conta conta);

	public List<Conta> listar();

	public void atualizar(Conta conta);

	public void remover(int codigo);

	public Conta buscarPorId(int codigoBusca);
}