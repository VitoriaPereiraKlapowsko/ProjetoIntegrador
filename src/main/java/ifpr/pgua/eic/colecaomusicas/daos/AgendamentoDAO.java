package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Agendamento;

public interface AgendamentoDAO {
    Resultado criar(Agendamento agendamento);
    Resultado listar();
    Resultado cancelar(int codigo); //função do deletar
    Resultado atualizarStatus(int codigoAgendamento, int novoCodigo);
}
