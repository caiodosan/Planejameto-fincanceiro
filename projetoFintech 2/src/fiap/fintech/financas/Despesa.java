package fiap.fintech.financas;

import java.sql.Date;

public class Despesa {

  private int id;
  private Conta conta;
  private double valor;
  private Date data;
  private String tipo;

  public Despesa(int id, Conta conta, double valor, Date data, String tipo) {
    this.id = id;
    this.conta = conta;
    this.valor = valor;
    this.data = data;
    this.tipo = tipo;
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
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void atualizarSaldo() {
    double novoSaldo = conta.getSaldo() - valor;
    conta.setSaldo(novoSaldo);
  }
}
