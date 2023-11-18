package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Funcionario;

public interface ClienteDAO {
    Resultado criar(Cliente cliente);
    Resultado listar();
    Resultado deletar(int codigo);
    Resultado editar(int codigo, Cliente novo);

}
