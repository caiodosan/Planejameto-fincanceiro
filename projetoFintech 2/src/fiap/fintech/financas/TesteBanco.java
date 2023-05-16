package fiap.fintech.financas;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;
import fiap.fintech.financas.dao.factory.DAOFactory;
import fiap.fintech.financas.dao.models.ContaDAO;
import fiap.fintech.financas.dao.models.DespesaDAO;
import fiap.fintech.financas.dao.models.InvestimentoDAO;
import fiap.fintech.financas.dao.models.ReceitaDAO;

public class TesteBanco {

	private final static Connection conexao = ConnectionManager.obterConexao();

	public static void main(String[] args) {
		try {
			//manipulaConta();
			manipulaReceita();
			manipulaDespesa();
			manipulaInvestimento();
			
			
			
			conexao.close();

			// Tratamento de erro de conexão
		} catch (SQLException e) {
			// conexao.rollback();
			System.err.println("Nao foi possível conectar no ORACLE FIAP");
			e.printStackTrace();
			// Tratamento de erro quando ndo
		}

	}

	public static void manipulaReceita() {
		ReceitaDAO dao = DAOFactory.getReceitaDAO();

		Date data = new Date(Calendar.getInstance().getTimeInMillis());
		Conta r = new Conta(2,1,100.3);
		
		Receita rec = new Receita(3,r,100.2,data,"caio");
		dao.cadastrar(rec);
		
		rec = new Receita(3,r,100.2,data,"pedro");
		dao.atualizar(rec);
		
		List<Receita> receitas = dao.listar();
		System.out.println(receitas.size());
		
		Receita receitaPorId = dao.buscarPorId(2);
		System.out.println(receitaPorId.getTipo());
		
		dao.remover(1);



	}
	public static void manipulaConta() {
		ContaDAO dao = DAOFactory.getContaDAO();
		
		Conta rec = new Conta(6,1,100.3);
		dao.cadastrar(rec);
		
		rec = new Conta(6,1,200.3);
		dao.atualizar(rec);
		
		List<Conta> contas = dao.listar();
		System.out.println(contas);
		
		Conta contaPorId = dao.buscarPorId(1);
		System.out.println(contaPorId);
		
		dao.remover(1);



	}
	public static void manipulaDespesa() {
		DespesaDAO dao = DAOFactory.getDespesaDAO();

		Date data = new Date(Calendar.getInstance().getTimeInMillis());
		
		Conta c = new Conta(2,1,100.3);
		
		Despesa des = new Despesa(5,c,100,data,"caio");
		dao.cadastrar(des);
		
		des = new Despesa(5,c,100.2,data,"goiaba");
		dao.atualizar(des);
		
		List<Despesa> despesas = dao.listar();
		System.out.println(despesas.size());
		
		Despesa despesaId = dao.buscarPorId(5);
		System.out.println(despesaId.getTipo());
		
		dao.remover(1);



	}
	public static void manipulaInvestimento() {
		InvestimentoDAO dao = DAOFactory.getInvestimentoDAO();

		Date data = new Date(Calendar.getInstance().getTimeInMillis());
		Conta rec = new Conta(2,1,100.3);
		
		Investimento in = new Investimento(2,rec,100.2,data,"caio");
		dao.cadastrar(in);
		
		in = new Investimento(2,rec,100.2,data,"pedro");
		dao.atualizar(in);
		
		List<Investimento> investimentoLista = dao.listar();
		System.out.println(investimentoLista.size());
		
		Investimento investimentoId = dao.buscarPorId(2);
		System.out.println(investimentoId);
		
		dao.remover(1);



	}

}
