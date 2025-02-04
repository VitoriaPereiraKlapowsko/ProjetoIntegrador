package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Raca;

public class JDBCPetDAO implements PetDAO {

    private FabricaConexoes fabrica;

    public JDBCPetDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Pet pet) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO tb_animal(cliente_codigo, raca_codigo, nome, sexo, porte, especie, data_de_nascimento, tratamento_especiais, condicoes_fisicas) VALUES (?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, pet.getCliente().getCodigo());
            pstm.setInt(2, pet.getRaca().getCodigo());
            pstm.setString(3, pet.getNome());
            pstm.setString(4, pet.getSexo());
            pstm.setString(5, pet.getPorte());
            pstm.setString(6, pet.getEspecie());
            pstm.setObject(7, pet.getDataDeNascimento());
            pstm.setString(8, pet.getTratamentosEspeciais());
            pstm.setString(9, pet.getCondicoesFisicas());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                pet.setCodigo(codigo);

                return Resultado.sucesso("Pet cadastrado com sucesso!!!", pet);
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
                    "SELECT tb_animal.*, tb_raca.nome AS nome_raca, tb_cliente.nome as nome_cliente, tb_cliente.sobrenome as sobrenome_cliente, tb_cliente.cpf_cnpj as cpf_cliente, tb_cliente.inscricao_estadual as inscricao_cliente, tb_cliente.endereco as endereco, tb_cliente.telefone as telefone, tb_cliente.email as email "
                            +
                            "FROM tb_animal " +
                            "INNER JOIN tb_raca ON tb_animal.raca_codigo = tb_raca.codigo " +
                            "INNER JOIN tb_cliente ON tb_animal.cliente_codigo = tb_cliente.codigo");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Pet> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                int cliente_codigo = rs.getInt("cliente_codigo");
                int raca_codigo = rs.getInt("raca_codigo");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                String porte = rs.getString("porte");
                String especie = rs.getString("especie");
                LocalDate dataDeNascimento = rs.getObject("data_de_nascimento", LocalDate.class);
                String tratamentosEspeciais = rs.getString("tratamento_especiais");
                String condicoesFisicas = rs.getString("condicoes_fisicas");
                String nomeRaca = rs.getString("nome_raca");
                
                String nomeCliente = rs.getString("nome_cliente");
                String sobrenomeCliente = rs.getString("sobrenome_cliente");
                int cpfCnpj = rs.getInt("cpf_cliente");
                int inscricaoEstadual = rs.getInt("inscricao_cliente");
                String endereco = rs.getString("endereco");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(cliente_codigo, nomeCliente, sobrenomeCliente, cpfCnpj, inscricaoEstadual,
                        endereco, telefone, email);
                Raca raca = new Raca(raca_codigo, nomeRaca, null);

                Pet pet = new Pet(codigo, cliente, raca, nome, sexo, porte, especie,
                        dataDeNascimento,
                        tratamentosEspeciais, condicoesFisicas);
                lista.add(pet);
            }

            return Resultado.sucesso("Lista de Pets", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado editar(int codigo, Pet novo) {
        try (Connection con = fabrica.getConnection();) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "UPDATE tb_animal SET cliente_codigo=?, raca_codigo=?, nome=?, sexo=?, porte=?, especie=?, data_de_nascimento=?, tratamento_especiais=?, condicoes_fisicas=? WHERE codigo=?");
            // Ajustar os parâmetros
            pstm.setInt(1, novo.getCliente().getCodigo());
            pstm.setInt(2, novo.getRaca().getCodigo());
            pstm.setString(3, novo.getNome());
            pstm.setString(4, novo.getSexo());
            pstm.setString(5, novo.getPorte());
            pstm.setString(6, novo.getEspecie());
            pstm.setObject(7, novo.getDataDeNascimento());
            pstm.setString(8, novo.getTratamentosEspeciais());
            pstm.setString(9, novo.getCondicoesFisicas());

            pstm.setInt(10, codigo);

            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Pet Atualizado!", novo);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_animal WHERE codigo = ?");
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Pet deletado com sucesso!", con);
            }
            return Resultado.erro("Pet não encontrado...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

}
