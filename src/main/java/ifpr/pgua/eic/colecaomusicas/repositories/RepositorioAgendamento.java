package ifpr.pgua.eic.colecaomusicas.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.AgendamentoDAO;
import ifpr.pgua.eic.colecaomusicas.models.Agendamento;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.models.Status;

public class RepositorioAgendamento {
    private ArrayList<Agendamento> agendamentos;

    private AgendamentoDAO dao;

    public RepositorioAgendamento(AgendamentoDAO dao) {
        agendamentos = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarAgendamento(Cliente cliente, Pet pet, Servico servico,
            Status status,LocalDate dataReserva, String horarioReserva, float valorTotalReserva, String tosadorBanhista,
            String observacoesGerais) {

        if (cliente == null) {
            return Resultado.erro("Cliente não selecionado!");
        }

        if (pet == null) {
            return Resultado.erro("Pet não selecionado!");
        }

        if (dataReserva == null) {
            return Resultado.erro("Data de reserva não selecionada!");
        }

        if (servico == null) {
            return Resultado.erro("Serviço não selecionado!");
        }

        if (status == null) {
            return Resultado.erro("Status não selecionado!");
        }

        if (tosadorBanhista == null || tosadorBanhista.isEmpty() || tosadorBanhista.isBlank()) {
            return Resultado.erro("Informe o tosador ou banhista!");
        }

        if (observacoesGerais == null || observacoesGerais.isEmpty() || observacoesGerais.isBlank()) {
            return Resultado.erro("Informe as observações gerais!");
        }

        if (valorTotalReserva <= 0) {
            return Resultado.erro("Informe um valor válido para a reserva!");
        }

        Agendamento novoAgendamento = new Agendamento(cliente, pet, servico,
         status, dataReserva,horarioReserva , valorTotalReserva, tosadorBanhista,observacoesGerais);

        return dao.criar(novoAgendamento);
    }

    public Resultado listarAgendamentos() {
        return dao.listar();
    }

    public Resultado deletarAgendamento(int codigo) {
        return dao.cancelar(codigo);
    }

    public Resultado atualizarAgendamento(int codigoAgendamento, int novoCodigo) {
        return dao.atualizarStatus(codigoAgendamento, novoCodigo);
    }

}
