package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.ClienteDAO;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;

public class RepositorioCliente {
    private ArrayList<Cliente> clientes;

    private ClienteDAO dao;

    public RepositorioCliente(ClienteDAO dao){
        clientes = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarCliente(String nome, String sobrenome, int cpfCnpj, int inscricaoEstadual,String endereco, int telefone,String email){
        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }

        if(sobrenome.isEmpty() || sobrenome.isBlank()){
            return Resultado.erro("Sobrenome inválido!");
        }

        if (cpfCnpj<= 0) {
            return Resultado.erro("CPF ou CNPJ inválido!");
        }

        if (inscricaoEstadual<= 0) {
            return Resultado.erro("Inscrição Estadual inválida!");
        }

        if(email.isEmpty() || email.isBlank()){
            return Resultado.erro("Email inválido!");
        }

        if(endereco.isEmpty() || endereco.isBlank()){
            return Resultado.erro("Endereço inválido!");
        }

        if (telefone <= 0) {
            return Resultado.erro("Telefone inválido!");
        }
       
        Cliente cliente = new Cliente(nome, sobrenome, cpfCnpj, inscricaoEstadual, endereco, telefone, email);
        return dao.criar(cliente);
    }

    public Resultado listarClientes() {
        return dao.listar();
    }

    public Resultado deletarCliente(int codigo) {
        return dao.deletar(codigo);
    }

    public Resultado alterarCliente(int codigo,String nome, String sobrenome, int cpfCnpj, int inscricaoEstadual, String endereco, int telefone, String email){
        Cliente novo = new Cliente(codigo,nome, sobrenome, cpfCnpj, inscricaoEstadual, endereco, telefone, email);

        return dao.editar(codigo, novo);
    }
}
