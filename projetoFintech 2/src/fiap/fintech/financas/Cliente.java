package fiap.fintech.financas;

public class Cliente {

  private int id;
  private String nome;
  private String email;
  private String cpf;
  private String telefone;
  private String senha;

  public Cliente(
      int id,
      String nome,
      String email,
      String cpf,
      String telefone,
      String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.telefone = telefone;
    this.senha = senha;
  }

  public String getInformacoes() {
    return "O usuário de nome " + this.nome + ", e seu email é " + this.email;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}