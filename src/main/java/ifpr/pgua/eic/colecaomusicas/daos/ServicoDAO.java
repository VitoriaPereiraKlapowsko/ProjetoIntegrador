package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Servico;

public interface ServicoDAO {
    Resultado criar(Servico servico);
    Resultado listar();
    Resultado editar(int codigo, Servico novo);
    Resultado deletar(int codigoServico);
}
