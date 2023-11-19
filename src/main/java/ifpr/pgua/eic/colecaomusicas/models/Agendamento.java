package ifpr.pgua.eic.colecaomusicas.models;

import java.sql.Time;
import java.time.LocalDate;

public class Agendamento {
    private int codigo;
    private Cliente cliente_codigo;
    private Pet animal_codigo;
    private Servico tipo_servico;
    private Funcionario funcionario_codigo;
    private Status codigo_status;
    private LocalDate data_reserva_do_servico;
    private Time horario_do_servico;
    private float valor_total_da_reserva;
    private String tosador_ou_banhista;
    private String observacao;

    public Agendamento(int codigo, Cliente cliente_codigo, Pet animal_codigo, Servico tipo_servico,
            Funcionario funcionario_codigo, Status codigo_status, LocalDate data_reserva_do_servico,
            Time horario_do_servico, float valor_total_da_reserva, String tosador_ou_banhista, String observacao) {
                
    }

}
