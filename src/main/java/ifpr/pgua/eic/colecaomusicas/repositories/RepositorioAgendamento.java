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

    public Resultado cadastrarAgendamento(Cliente cliente, Pet pet, LocalDate dataReserva, Servico servico,
            Status status, String tosadorBanhista, String observacaoServico,
            String observacoesGerais, float valorTotalReserva) {
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

        if (observacaoServico == null || observacaoServico.isEmpty() || observacaoServico.isBlank()) {
            return Resultado.erro("Informe as observações do serviço!");
        }

        if (observacoesGerais == null || observacoesGerais.isEmpty() || observacoesGerais.isBlank()) {
            return Resultado.erro("Informe as observações gerais!");
        }

        if (valorTotalReserva <= 0) {
            return Resultado.erro("Informe um valor válido para a reserva!");
        }

        Agendamento novoAgendamento = new Agendamento(cliente, pet, dataReserva, servico, status,
                tosadorBanhista, observacaoServico, observacoesGerais, valorTotalReserva);
        return dao.criar(novoAgendamento);
    }

    public Resultado listarAgendamentos() {
        return dao.listar();
    }
}
