package ifpr.pgua.eic.colecaomusicas.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.FuncionarioDAO;
import ifpr.pgua.eic.colecaomusicas.models.Funcionario;

public class RepositorioFuncionario {
    private ArrayList<Funcionario> funcionarios;

    private FuncionarioDAO dao;

    public RepositorioFuncionario(FuncionarioDAO dao){
        funcionarios = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarFuncionario(String login,String senha, String nome, String sobrenome, int telefone, String funcao, String cpf,
            String sexo, String endereco, LocalDate dataNascimento, String email){

        if(login.isEmpty() || login.isBlank()){
            return Resultado.erro("Login inválido!");
        }

        if(senha.isEmpty() || senha.isBlank()){
            return Resultado.erro("Senha inválida!");
        }

        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }

        if(sobrenome.isEmpty() || sobrenome.isBlank()){
            return Resultado.erro("Sobrenome inválido!");
        }

        if (telefone < 0) {
            return Resultado.erro("Telefone inválido!");
        }

        if(funcao.isEmpty() || funcao.isBlank()){
            return Resultado.erro("Função inválida!");
        }

        if (cpf.isEmpty() || cpf.isBlank()) {
            return Resultado.erro("CPF ou CNPJ inválido!");
        }

        if(sexo.isEmpty() || sexo.isBlank()){
            return Resultado.erro("Sexo inválido!");
        }

        if(endereco.isEmpty() || endereco.isBlank()){
            return Resultado.erro("Endereço inválido!");
        }

        if (dataNascimento == null) {
            return Resultado.erro("Data de Nascimento inválida!");
        }

        if(email.isEmpty() || email.isBlank()){
            return Resultado.erro("Email inválido!");
        }
      
       
        Funcionario funcionario = new Funcionario(login,senha,nome,sobrenome,telefone,funcao,cpf,sexo,endereco,dataNascimento,email);
        return dao.criar(funcionario);
    }

    public Resultado listarFuncionarios() {
        return dao.listar();
    }

    public Resultado alterarFuncionario(int codigo,String login,String senha, String nome, String sobrenome, int telefone, String funcao, String cpf,
    String sexo, String endereco, LocalDate dataNascimento, String email){


        Funcionario novo = new Funcionario(codigo,login,senha,nome,sobrenome,telefone,funcao,cpf,sexo,endereco,dataNascimento,email);

        return dao.editar(codigo, novo);
    }

    public Resultado deletarFuncionario(int codigo) {
        return dao.deletar(codigo);
    }  
}
