package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Agendamento;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.models.Status;

public class JDBCAgendamentoDAO implements AgendamentoDAO {

    private FabricaConexoes fabrica;

    public JDBCAgendamentoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Agendamento agendamento) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO tb_agendamento(cliente_codigo, animal_codigo, tipo_servico, codigo_status, data_reserva_de_servico, horario_do_servico, valor_total_da_reserva, tosador_ou_banhista, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, agendamento.getClienteCodigo().getCodigo());
            pstm.setInt(2, agendamento.getAnimalCodigo().getCodigo());
            pstm.setInt(3, agendamento.getTipoServico().getCodigo());
            pstm.setInt(4, agendamento.getCodigoStatus().getCodigo());
            pstm.setDate(5, Date.valueOf(agendamento.getDataReserva()));
            pstm.setString(6, agendamento.getHorarioReserva());
            pstm.setFloat(7, agendamento.getValorTotal());
            pstm.setString(8, agendamento.getTosadorOuBanhista());
            pstm.setString(9, agendamento.getObservacao());
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
            PreparedStatement pstm = con.prepareStatement(
                    "SELECT tb_agendamento.*, tb_cliente.nome as nome_cliente, tb_cliente.sobrenome as sobrenome_cliente, tb_cliente.cpf_cnpj as cpf_cliente, tb_cliente.inscricao_estadual as inscricao_cliente, tb_cliente.endereco as endereco, tb_cliente.telefone as telefone, tb_cliente.email as email,tb_status.descricao as descricao_status, tb_servico.valor as valor_servico, tb_servico.descricao as descricao_servico,tb_animal.nome as nome_pet, tb_animal.sexo as sexo_pet, tb_animal.porte as porte_pet, tb_animal.especie as especie_pet, tb_animal.data_de_nascimento as data_de_nascimento_pet, tb_animal.tratamento_especiais as tratamento_especiais_pet, tb_animal.condicoes_fisicas as condicoes_fisicas_pet "
                            +
                            "FROM tb_agendamento " +
                            "INNER JOIN tb_cliente ON tb_agendamento.cliente_codigo = tb_cliente.codigo " +
                            "INNER JOIN tb_animal ON tb_agendamento.animal_codigo = tb_animal.codigo " +
                            "INNER JOIN tb_servico ON tb_agendamento.tipo_servico = tb_servico.codigo_do_servico " +
                            "INNER JOIN tb_status ON tb_agendamento.codigo_status = tb_status.codigo ");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Agendamento> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo_agendamento = rs.getInt("codigo");
                int cliente_codigo = rs.getInt("cliente_codigo");
                int animalCodigo = rs.getInt("animal_codigo");
                int tipoServico = rs.getInt("tipo_servico");
                int codigoStatus = rs.getInt("codigo_status");
                LocalDate dataReservaDoServico = rs.getObject("data_reserva_de_servico", LocalDate.class);
                String horarioDaReserva = rs.getString("horario_do_servico");
                float valorTotalDaReserva = rs.getFloat("valor_total_da_reserva");
                String tosadorOuBanhista = rs.getString("tosador_ou_banhista");
                String observacao = rs.getString("observacao");

                String nomeCliente = rs.getString("nome_cliente");
                String sobrenomeCliente = rs.getString("sobrenome_cliente");
                int cpfCnpj = rs.getInt("cpf_cliente");
                int inscricaoEstadual = rs.getInt("inscricao_cliente");
                String endereco = rs.getString("endereco");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                String nomePet = rs.getString("nome_pet");
                String sexoPet = rs.getString("sexo_pet");
                String portePet = rs.getString("porte_pet");
                String especiePet = rs.getString("especie_pet");
                LocalDate dataDeNascimentoPet = rs.getObject("data_de_nascimento_pet", LocalDate.class);
                String tratamentoEspeciaisPet = rs.getString("tratamento_especiais_pet");
                String condicoesFisicasPet = rs.getString("condicoes_fisicas_pet");

                float valorServico = rs.getFloat("valor_servico");
                String descricaoServico = rs.getString("descricao_servico");

                String descricaoStatus = rs.getString("descricao_status");

                Cliente cliente = new Cliente(cliente_codigo, nomeCliente, sobrenomeCliente, cpfCnpj, inscricaoEstadual,
                        endereco, telefone, email);

                // Raca raca = new Raca(raca_codigo, nomeRaca, null);

                Pet pet = new Pet(animalCodigo, cliente, null, nomePet, sexoPet, portePet, especiePet,
                        dataDeNascimentoPet, tratamentoEspeciaisPet, condicoesFisicasPet);

                Servico servico = new Servico(tipoServico, valorServico, descricaoServico);

                Status status = new Status(codigoStatus, descricaoStatus);

                Agendamento agendamento = new Agendamento(codigo_agendamento, cliente, pet, servico,
                        status, dataReservaDoServico, horarioDaReserva, valorTotalDaReserva,
                        tosadorOuBanhista, observacao);

                lista.add(agendamento);
            }

            return Resultado.sucesso("Lista de Agendamentos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado cancelar(int codigo) { // função do deletar
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

    @Override
    public Resultado atualizarStatus(int codigoAgendamento, int novoCodigo) {

        String sql = "UPDATE tb_agendamento SET codigo_status = ? WHERE codigo = ?";
        try (Connection con = fabrica.getConnection();
                PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, novoCodigo);
            pstm.setInt(2, codigoAgendamento);

            int rowsUpdated = pstm.executeUpdate();

            if (rowsUpdated > 0) {
                return Resultado.sucesso("Código do agendamento atualizado com sucesso!", con);
            } else {
                return Resultado.erro("Não foi possível atualizar o código do agendamento.");
            }

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    
}
