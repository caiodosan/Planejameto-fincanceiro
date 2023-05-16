package fiap.fintech.financas;

public class Conta {

  private int idConta;
  private int idCliente;
  private double saldo;

  public Conta(int idConta, int idCliente, double saldo) {
    this.idConta = idConta;
    this.idCliente = idCliente;
    this.saldo = saldo;
  }

  public int getIdConta() {
    return idConta;
  }

  public void setIdConta(int idConta) {
    this.idConta = idConta;
  }

  public int getIdCliente() {
    return idCliente;
  }

  public void setIDCliente(int idCliente) {
    this.idCliente = idCliente;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}