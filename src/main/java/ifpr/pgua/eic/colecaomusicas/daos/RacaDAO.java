package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.models.Servico;


public interface RacaDAO {
    Resultado criar(Raca raca);
    Resultado listar();
    Resultado editar(int codigo, Raca novo);
    Resultado deletar(int codigoo);
}
