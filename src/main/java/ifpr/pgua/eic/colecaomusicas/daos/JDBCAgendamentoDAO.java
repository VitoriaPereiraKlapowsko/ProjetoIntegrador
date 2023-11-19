package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Time;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Agendamento;

public class JDBCAgendamentoDAO implements AgendamentoDAO {

    private FabricaConexoes fabrica;

    public JDBCAgendamentoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Agendamento agendamento) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO tb_agendamento(cliente_codigo, animal_codigo, tipo_servico, funcionario_login, codigo_status, data_reserva_de_servico, horario_do_servico, valor_total_da_reserva, tosador_ou_banhista, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, agendamento.getClienteCodigo());
            pstm.setInt(2, agendamento.getAnimalCodigo());
            pstm.setInt(3, agendamento.getTipoServico());
            pstm.setInt(4, agendamento.getFuncionarioCodigo());
            pstm.setInt(5, agendamento.getCodigoStatus());
            pstm.setDate(6, Date.valueOf(agendamento.getDataReserva()));
            pstm.setTime(7, Time.valueOf(agendamento.getHorarioServico().toLocalTime()));
            pstm.setFloat(8, agendamento.getValorTotal());
            pstm.setString(9, agendamento.getTosadorOuBanhista());
            pstm.setString(10, agendamento.getObservacao());
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                agendamento.setCodigo(codigo);

                return Resultado.sucesso("Agendamento cadastrado com sucesso!!!", agendamento);
            }
            return Resultado.erro("Ops.. deu um erro!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_agendamento");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Agendamento> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                int clienteCodigo = rs.getInt("cliente_codigo");
                int animalCodigo = rs.getInt("animal_codigo");
                int tipoServico = rs.getInt("tipo_servico");
                int funcionarioCodigo = rs.getInt("funcionario_login");
                int codigoStatus = rs.getInt("codigo_status");
                LocalDate dataReservaDoServico = rs.getObject("data_reserva_de_servico", LocalDate.class);
                Time horarioDoServico = rs.getTime("horario_do_servico");
                float valorTotalDaReserva = rs.getFloat("valor_total_da_reserva");
                String tosadorOuBanhista = rs.getString("tosador_ou_banhista");
                String observacao = rs.getString("observacao");

                Agendamento agendamento = new Agendamento(codigo, clienteCodigo, animalCodigo, tipoServico,
                        funcionarioCodigo, codigoStatus, dataReservaDoServico, horarioDoServico, valorTotalDaReserva,
                        tosadorOuBanhista, observacao);

                lista.add(agendamento);
            }

            return Resultado.sucesso("Lista de Agendamentos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado cancelar(int codigo) { //função do deletar
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_agendamento WHERE codigo = ?");
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Agendamento deletado com sucesso!", con);
            }
            return Resultado.erro("Nenhum Agendamento foi encontrado...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
