package fiap.fintech.financas;

import java.sql.Date;

public class Investimento {
  private int idInvestimento;
  private Conta conta;
  private double valor;
  private Date data;
  private String tipoInvestimento;

  public Investimento(int idInvestimento, Conta conta, double valor, Date data, String tipoInvestimento) {
    this.idInvestimento = idInvestimento;
    this.conta = conta;
    this.valor = valor;
    this.data = data;
    this.tipoInvestimento = tipoInvestimento;
  }

  public int getId() {
    return idInvestimento;
  }

  public void setId(int idInvestimento) {
    this.idInvestimento = idInvestimento;
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
    return tipoInvestimento;
  }

  public void setTipo(String tipoInvestimento) {
    this.tipoInvestimento = tipoInvestimento;
  }
}