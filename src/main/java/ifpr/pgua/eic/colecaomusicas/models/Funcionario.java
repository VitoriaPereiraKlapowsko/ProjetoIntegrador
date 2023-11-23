package ifpr.pgua.eic.colecaomusicas.models;

import java.time.LocalDate;

public class Funcionario {
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String endereco;
    private String sexo;
    private int telefone;
    private String funcao;
    private int codigo;


    public Funcionario(String nome, String sobrenome, int telefone, String funcao, String cpf,
            String sexo, String endereco, LocalDate dataNascimento, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = endereco;
        this.sexo = sexo;
        this.telefone = telefone;
        this.funcao = funcao;
    }

    public Funcionario(int codigo,String nome, String sobrenome, int telefone, String funcao, String cpf,
            String sexo, String endereco, LocalDate dataNascimento, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.funcao = funcao;
        this.email = email;
        this.endereco = endereco;
        this.sexo = sexo;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNasc() {
        return dataNascimento;
    }

    public void setDataNasc(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Funcionário: " + nome + " "+ sobrenome + "";
    }
}
