package ifpr.pgua.eic.colecaomusicas.models;

import java.sql.Time;
import java.time.LocalDate;

public class Agendamento {
    private int codigo;
    private int clienteCodigo;
    private int animalCodigo;
    private int tipoServico;
    private int funcionarioCodigo;
    private int codigoStatus;
    private LocalDate dataReserva;
    private Time horarioServico;
    private float valorTotal;
    private String tosadorOuBanhista;
    private String observacao;

    public Agendamento(int clienteCodigo, int animalCodigo, LocalDate dataReserva,
            int tipoServico, int codigoStatus, String tosadorOuBanhista,
            String observacao, float valorTotal) {
        this.clienteCodigo = clienteCodigo;
        this.animalCodigo = animalCodigo;
        this.tipoServico = tipoServico;
        this.codigoStatus = codigoStatus;
        this.dataReserva = dataReserva;
        this.valorTotal = valorTotal;
        this.tosadorOuBanhista = tosadorOuBanhista;
        this.observacao = observacao;
    }

    public Agendamento(int codigo, int clienteCodigo, int animalCodigo, int tipoServico,
            int funcionarioCodigo, int codigoStatus, LocalDate dataReserva,
            Time horarioServico, float valorTotal, String tosadorOuBanhista,
            String observacao) {
        this.codigo = codigo;
        this.clienteCodigo = clienteCodigo;
        this.animalCodigo = animalCodigo;
        this.tipoServico = tipoServico;
        this.funcionarioCodigo = funcionarioCodigo;
        this.codigoStatus = codigoStatus;
        this.dataReserva = dataReserva;
        this.horarioServico = horarioServico;
        this.valorTotal = valorTotal;
        this.tosadorOuBanhista = tosadorOuBanhista;
        this.observacao = observacao;
    }

    public Agendamento(Cliente cliente, Pet pet, LocalDate dataReserva, Servico servico, Status status,
            String tosadorBanhista, String observacaoServico, float valorTotalReserva) {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public int getAnimalCodigo() {
        return animalCodigo;
    }

    public void setAnimalCodigo(int animalCodigo) {
        this.animalCodigo = animalCodigo;
    }

    public int getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(int tipoServico) {
        this.tipoServico = tipoServico;
    }

    public int getFuncionarioCodigo() {
        return funcionarioCodigo;
    }

    public void setFuncionarioCodigo(int funcionarioCodigo) {
        this.funcionarioCodigo = funcionarioCodigo;
    }

    public int getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(int codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Time getHorarioServico() {
        return horarioServico;
    }

    public void setHorarioServico(Time horarioServico) {
        this.horarioServico = horarioServico;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTosadorOuBanhista() {
        return tosadorOuBanhista;
    }

    public void setTosadorOuBanhista(String tosadorOuBanhista) {
        this.tosadorOuBanhista = tosadorOuBanhista;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Agendamento: " +
                "Cliente: " + clienteCodigo + "\n" +
                "Animal: " + animalCodigo + "\n" +
                "Serviço: " + tipoServico + "\n" +
                "Funcionario: " + funcionarioCodigo + "\n" +
                "Status: " + codigoStatus + "\n" +
                "Data da Reserva: " + dataReserva + "\n" +
                "Horario: " + horarioServico + "\n" +
                "Valor: " + valorTotal + "\n" +
                "Tosador Ou Banhista: " + tosadorOuBanhista + "\n" +
                "Observacões: " + observacao + "\n" +
                "";
    }
}
