package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.RacaDAO;
import ifpr.pgua.eic.colecaomusicas.models.Raca;

public class RepositorioRaca {
    private ArrayList<Raca> racas;

    private RacaDAO dao;

    public RepositorioRaca(RacaDAO dao){
        racas = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarRaca(String nome, String descricao){

        if(nome.isEmpty() || nome.isBlank()){
            return Resultado.erro("Nome inválido!");
        }

        if(descricao.isEmpty() || descricao.isBlank()){
            return Resultado.erro("Descrição inválida!");
        }
       
        Raca raca = new Raca(nome, descricao);
        return dao.criar(raca);
    }

    public Resultado listarRaca(){
        return dao.listar();
    }
}
