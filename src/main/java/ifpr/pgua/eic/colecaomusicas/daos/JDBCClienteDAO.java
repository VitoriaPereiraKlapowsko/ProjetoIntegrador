package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JDBCClienteDAO implements ClienteDAO {

    private FabricaConexoes fabrica;

    public JDBCClienteDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Cliente cliente) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO tb_cliente(nome, sobrenome, cpf_cnpj, inscricao_estadual, endereco, telefone, email) VALUES (?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getSobrenome());
            pstm.setInt(3, cliente.getCpfCnpj());
            pstm.setInt(4, cliente.getInscricaoEstadual());
            pstm.setString(5, cliente.getEndereco());
            pstm.setInt(6, cliente.getTelefone());
            pstm.setString(7, cliente.getEmail());
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                cliente.setCodigo(codigo);

                return Resultado.sucesso("Cliente cadastrado com sucesso!!!", cliente);
            }
            return Resultado.erro("Ops.. deu um erro!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_cliente");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Cliente> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                int cpfCnpj = rs.getInt("cpf_cnpj");
                int inscricaoEstadual = rs.getInt("inscricao_estadual");
                String endereco = rs.getString("endereco");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(codigo, nome, sobrenome, cpfCnpj, inscricaoEstadual, endereco, telefone,
                        email);
                lista.add(cliente);
            }

            return Resultado.sucesso("Lista de cliente", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_cliente WHERE codigo = ?");
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Cliente deletado com sucesso!", con);
            }
            return Resultado.erro("Nenhum Cliente foi encontrado...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado editar(int codigo, Cliente novo) {
        try (Connection con = fabrica.getConnection();) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "UPDATE tb_ciente SET nome=?, sobrebome=?, cpfCnpj=?, inscricaoEstadual=?, endereco=?,telefone=?,email=? WHERE codigo=?");
            // Ajustar os parâmetros
            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getSobrenome());
            pstm.setInt(3, novo.getCpfCnpj());
            pstm.setInt(4, novo.getInscricaoEstadual());
            pstm.setString(5, novo.getEndereco());
            pstm.setInt(6, novo.getTelefone());
            pstm.setString(7, novo.getEmail());
            
            pstm.setInt(8, codigo);

            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Serviço Atualizado!", novo);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
