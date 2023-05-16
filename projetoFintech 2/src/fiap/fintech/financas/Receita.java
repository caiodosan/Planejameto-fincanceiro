package fiap.fintech.financas;

import java.sql.Date;

public class Receita {
  private int id;
  private Conta conta;
  private double valor;
  private Date data;
  private String tipoReceita;

  public Receita(int id, Conta conta, double valor, Date data, String tipoReceita) {
	this.id = id;
    this.conta = conta;
    this.valor = valor;
    this.data = data;
    this.tipoReceita = tipoReceita;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Conta getConta() {
    return conta;
  }

  public void setConta(Conta conta) {
    this.conta = conta;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getTipo() {
    return tipoReceita;
  }

  public void setTipo(String tipoReceita) {
    this.tipoReceita = tipoReceita;
  }

//  public void atualizarSaldo() {
//    double novoSaldo = conta.getSaldo() + valor;
//    conta.setSaldo(novoSaldo);
//  }
}