package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Funcionario;

public class JDBCFuncionarioDAO implements FuncionarioDAO {

    private FabricaConexoes fabrica;

    public JDBCFuncionarioDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Funcionario funcionario) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO tb_funcionario(nome,sobrenome,telefone,funcao,cpf,sexo,endereco,data_de_nascimento,email) VALUES (?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getSobrenome());
            pstm.setInt(3, funcionario.getTelefone());
            pstm.setString(4, funcionario.getFuncao());
            pstm.setString(5, funcionario.getCpf());
            pstm.setString(6, funcionario.getSexo());
            pstm.setString(7, funcionario.getEndereco());
            pstm.setObject(8, funcionario.getDataNasc());
            pstm.setString(9, funcionario.getEmail());
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                funcionario.setCodigo(codigo);

                return Resultado.sucesso("Funcionário cadastrado com sucesso!!!", funcionario);
            }
            return Resultado.erro("Ops.. deu um erro!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_funcionario");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Funcionario> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                int telefone = rs.getInt("telefone");
                String funcao = rs.getString("funcao");
                String cpf = rs.getString("cpf");
                String sexo = rs.getString("sexo");
                String endereco = rs.getString("endereco");
                LocalDate dataNascimento = rs.getObject("data_de_nascimento", LocalDate.class);
                String email = rs.getString("email");

                Funcionario funcionario = new Funcionario(codigo,nome, sobrenome, telefone, funcao, cpf,
                        sexo, endereco, dataNascimento, email);
                lista.add(funcionario);
            }

            return Resultado.sucesso("Lista de Funcionários", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado editar(int codigo, Funcionario novo) {
        try (Connection con = fabrica.getConnection();) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "UPDATE tb_funcionario SET nome=?, sobrenome=?, telefone=?,funcao=?,cpf=?,sexo=?,endereco=?,data_de_nascimento=?,email=? WHERE codigo=?");
            // Ajustar os parâmetros
            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getSobrenome());
            pstm.setInt(3, novo.getTelefone());
            pstm.setString(4, novo.getFuncao());
            pstm.setString(5, novo.getCpf());
            pstm.setString(6, novo.getSexo());
            pstm.setString(7, novo.getEndereco());
            pstm.setObject(8, novo.getDataNasc());
            pstm.setString(9, novo.getEmail());

            pstm.setInt(10, codigo);

            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Funcionário Atualizado!", novo);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado deletar(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_funcionario WHERE codigo = ?");
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Funcionário deletado com sucesso!", con);
            }
            return Resultado.erro("Funcionário não encontrado...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
