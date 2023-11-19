package ifpr.pgua.eic.colecaomusicas.models;

import java.time.LocalDate;

public class Agendamento {
    private int codigo;
    private Cliente clienteCodigo;
    private Pet animalCodigo;
    private Servico tipoServico;
    private Status codigoStatus;
    private LocalDate dataReserva;
    private String horarioReserva;
    private float valorTotal;
    private String tosadorOuBanhista;
    private String observacao;

    public Agendamento(Cliente clienteCodigo, Pet animalCodigo, Servico tipoServico, Status codigoStatus, LocalDate dataReserva,String horarioReserva, float valorTotal,
            String tosadorOuBanhista, String observacao) {
        this.clienteCodigo = clienteCodigo;
        this.animalCodigo = animalCodigo;
        this.tipoServico = tipoServico;
        this.codigoStatus = codigoStatus;
        this.dataReserva = dataReserva;
        this.valorTotal = valorTotal;
        this.tosadorOuBanhista = tosadorOuBanhista;
        this.horarioReserva = horarioReserva;
                this.observacao = observacao;
    }

    public Agendamento(int codigo, Cliente clienteCodigo, Pet animalCodigo, Servico tipoServico, Status codigoStatus, LocalDate dataReserva,String horarioReserva, float valorTotal,
            String tosadorOuBanhista, String observacao) {
        this.codigo = codigo;
        this.clienteCodigo = clienteCodigo;
        this.animalCodigo = animalCodigo;
        this.tipoServico = tipoServico;
        this.codigoStatus = codigoStatus;
        this.dataReserva = dataReserva;
        this.horarioReserva = horarioReserva;
        this.valorTotal = valorTotal;
        this.tosadorOuBanhista = tosadorOuBanhista;
        this.observacao = observacao;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cliente getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(Cliente clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public Pet getAnimalCodigo() {
        return animalCodigo;
    }

    public void setAnimalCodigo(Pet animalCodigo) {
        this.animalCodigo = animalCodigo;
    }

    public Servico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Servico tipoServico) {
        this.tipoServico = tipoServico;
    }


    public Status getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(Status codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getHorarioReserva() {
        return horarioReserva;
    }

    public void setHorarioReserva(String horarioReserva) {
        this.horarioReserva = horarioReserva;
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
                "Data da Reserva: " + dataReserva + "\n" +
                "Horario: " + horarioReserva + "\n" +
                "Valor: " + valorTotal + "\n" +
                "Tosador Ou Banhista: " + tosadorOuBanhista + "\n" +
                "Observacões: " + observacao + "\n" +
                "";
    }
}
