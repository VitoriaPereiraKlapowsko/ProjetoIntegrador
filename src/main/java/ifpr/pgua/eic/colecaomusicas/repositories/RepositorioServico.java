package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.ServicoDAO;
import ifpr.pgua.eic.colecaomusicas.models.Servico;

public class RepositorioServico {
    private ArrayList<Servico> servicos;

    private ServicoDAO dao;

    public RepositorioServico(ServicoDAO dao){
        servicos = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarServico(String descricao, int valor){
        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }

        if (valor <= 0) {
            return Resultado.erro("Valor inválido!");
        }
       
        Servico servico = new Servico(valor, descricao);
        return dao.criar(servico);
    }

    public Resultado listarServicos(){
        return dao.listar();
    }

    public Resultado deletarServico(int codigoServico) {
        return dao.deletar(codigoServico);
    }

    public Resultado alterarServico(int codigo,String descricao, float valor){
        Servico novo = new Servico(codigo,valor,descricao);

        return dao.editar(codigo, novo);
    }
}
