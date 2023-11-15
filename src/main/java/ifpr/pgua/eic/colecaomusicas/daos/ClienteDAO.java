package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Cliente;

public interface ClienteDAO {
    Resultado criar(Cliente cliente);
    Resultado listar();
}
