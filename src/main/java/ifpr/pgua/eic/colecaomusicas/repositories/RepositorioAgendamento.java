package ifpr.pgua.eic.colecaomusicas.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.AgendamentoDAO;
import ifpr.pgua.eic.colecaomusicas.models.Agendamento;

public class RepositorioAgendamento {
    private ArrayList<Agendamento> agendamentos;

    private AgendamentoDAO dao;

    public RepositorioAgendamento(AgendamentoDAO dao) {
        agendamentos = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarAgendamento(int cliente, int pet, LocalDate dataReserva, int servico,
            int status, String tosadorBanhista, String observacaoServico,
            String observacoesGerais, float valorTotalReserva) {
        if (cliente <= 0) {
            return Resultado.erro("Cliente não selecionado!");
        }

        if (pet <= 0) {
            return Resultado.erro("Pet não selecionado!");
        }

        if (dataReserva == null) {
            return Resultado.erro("Data de reserva não selecionada!");
        }

        if (servico <= 0) {
            return Resultado.erro("Serviço não selecionado!");
        }

        if (status <= 0) {
            return Resultado.erro("Status não selecionado!");
        }

        if (tosadorBanhista == null || tosadorBanhista.isEmpty() || tosadorBanhista.isBlank()) {
            return Resultado.erro("Informe o tosador ou banhista!");
        }

        if (observacaoServico == null || observacaoServico.isEmpty() || observacaoServico.isBlank()) {
            return Resultado.erro("Informe as observações do serviço!");
        }

        if (observacoesGerais == null || observacoesGerais.isEmpty() || observacoesGerais.isBlank()) {
            return Resultado.erro("Informe as observações gerais!");
        }

        if (valorTotalReserva <= 0) {
            return Resultado.erro("Informe um valor válido para a reserva!");
        }

        Agendamento novoAgendamento = new Agendamento(cliente, pet, dataReserva,
                servico, status, tosadorBanhista, observacaoServico, valorTotalReserva);

        return dao.criar(novoAgendamento);
    }

    public Resultado listarAgendamentos() {
        return dao.listar();
    }
}