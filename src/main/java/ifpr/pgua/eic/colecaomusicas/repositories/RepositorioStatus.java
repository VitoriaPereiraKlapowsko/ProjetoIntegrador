package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.StatusDAO;
import ifpr.pgua.eic.colecaomusicas.models.Status;

public class RepositorioStatus {
    private ArrayList<Status> status;

    private StatusDAO dao;

    public RepositorioStatus(StatusDAO dao){
        status = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarStatus(String descricao){

        if(descricao.isEmpty() || descricao.isBlank()){

            return Resultado.erro("Descrição inválida!");
        }
  
        Status status = new Status(descricao);
        return dao.criar(status);
    }

    public Resultado alterarStatus(int codigo, String descricao){
        Status novo = new Status(codigo,descricao);

        return dao.editar(codigo, novo);
    }

    public Resultado listarStatus(){
        return dao.listar();
    }

    public Resultado deletarStatus(int codigo) {
        return dao.deletar(codigo);
    }
}
