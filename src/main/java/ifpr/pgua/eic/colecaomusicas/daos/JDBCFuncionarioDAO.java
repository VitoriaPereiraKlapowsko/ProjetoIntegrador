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
                    "INSERT INTO tb_funcionario(login,senha,nome,sobrenome,telefone,funcao,cpf,sexo,endereco,data_de_nascimento,email) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, funcionario.getLogin());
            pstm.setString(2, funcionario.getSenha());
            pstm.setString(3, funcionario.getNome());
            pstm.setString(4, funcionario.getSobrenome());
            pstm.setInt(5, funcionario.getTelefone());
            pstm.setString(6, funcionario.getFuncao());
            pstm.setString(7, funcionario.getCpf());
            pstm.setString(8, funcionario.getSexo());
            pstm.setString(9, funcionario.getEndereco());
            pstm.setObject(10, funcionario.getDataNasc());
            pstm.setString(11, funcionario.getEmail());
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
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                int telefone = rs.getInt("telefone");
                String funcao = rs.getString("funcao");
                String cpf = rs.getString("cpf");
                String sexo = rs.getString("sexo");
                String endereco = rs.getString("endereco");
                LocalDate dataNascimento = rs.getObject("data_de_nascimento", LocalDate.class);
                String email = rs.getString("email");

                Funcionario funcionario = new Funcionario(codigo, login, senha, nome, sobrenome, telefone, funcao, cpf,
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
                    "UPDATE tb_funcionario SET login=?,senha=?,nome=?, sobrenome=?, telefone=?,funcao=?,cpf=?,sexo=?,endereco=?,data_de_nascimento=?,email=? WHERE codigo=?");
            // Ajustar os parâmetros
            pstm.setString(1, novo.getLogin());
            pstm.setString(2, novo.getSenha());
            pstm.setString(3, novo.getNome());
            pstm.setString(4, novo.getSobrenome());
            pstm.setInt(5, novo.getTelefone());
            pstm.setString(6, novo.getFuncao());
            pstm.setString(7, novo.getCpf());
            pstm.setString(8, novo.getSexo());
            pstm.setString(9, novo.getEndereco());
            pstm.setObject(10, novo.getDataNasc());
            pstm.setString(11, novo.getEmail());

            pstm.setInt(12, codigo);

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
