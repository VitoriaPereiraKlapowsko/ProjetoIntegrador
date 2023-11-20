package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Raca;


public class JDBCRacaDAO implements RacaDAO {

    private FabricaConexoes fabrica;

    public JDBCRacaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Raca raca) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("INSERT INTO tb_raca(nome, descricao) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, raca.getNome());
            pstm.setString(2, raca.getDescricao());
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                raca.setCodigo(codigo);

                return Resultado.sucesso("Raça cadastrada com sucesso!!!", raca);
            }
            return Resultado.erro("Ops.. deu um erro!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_raca");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Raca> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                Raca raca = new Raca(codigo, nome, descricao);
                lista.add(raca);
            }

            return Resultado.sucesso("Lista de Raças", lista);
            
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
        public Resultado editar(int codigo, Raca novo) {
        try (Connection con = fabrica.getConnection();) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "UPDATE tb_raca SET nome=?, descricao=? WHERE codigo=?");
            // Ajustar os parâmetros
            pstm.setString(1, novo.getNome());
            pstm.setString(2, novo.getDescricao());
            
            pstm.setInt(3, codigo);

            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Raça Atualizada!", novo);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        } 
    }
    

    @Override
    public Resultado deletar(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_raca WHERE codigo = ?");
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Raça deletada com sucesso!", con);
            }
            return Resultado.erro("Raça não encontrada...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
