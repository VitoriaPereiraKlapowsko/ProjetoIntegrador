package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Raca;


public interface RacaDAO {
    Resultado criar(Raca raca);
    Resultado listar();
}
