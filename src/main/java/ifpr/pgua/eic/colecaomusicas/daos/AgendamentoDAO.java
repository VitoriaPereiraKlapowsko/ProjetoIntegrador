package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Agendamento;

public interface AgendamentoDAO {
    Resultado criar(Agendamento agendamento);
    Resultado listar();

}
