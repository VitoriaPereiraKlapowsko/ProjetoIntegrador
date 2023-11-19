package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Status;

public interface StatusDAO {
    Resultado criar(Status status);
    Resultado listar();
    Resultado editar(int codigo, Status novo);
    Resultado deletar(int codigo);    
}
