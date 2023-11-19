package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Status;

public class JDBCStatusDAO implements StatusDAO {
    private FabricaConexoes fabrica;

    public JDBCStatusDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Status status) {
        try(Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO tb_status(descricao) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
           
            pstm.setString(1,status.getDescricao());
            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                status.setCodigo(codigo);

                return Resultado.sucesso("Status cadastrado com sucesso!!!", status);
            }
            return Resultado.erro("Ops.. deu um erro!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_status");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Status> lista = new ArrayList<>();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String descricao = rs.getString("descricao");
                
                Status status = new Status(codigo,descricao);
                lista.add(status);
            }
            
            return Resultado.sucesso("Lista de serviços", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado editar(int codigo, Status novo) {
        try (Connection con = fabrica.getConnection();) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "UPDATE tb_status SET descricao=? WHERE codigo=?");
            // Ajustar os parâmetros
            pstm.setString(1, novo.getDescricao());
            
            pstm.setInt(2, codigo);

            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Status Atualizado!", novo);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int codigo) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_status WHERE codigo = ?");
            pstm.setInt(1, codigo);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Status deletado com sucesso!", con);
            }
            return Resultado.erro("Status não encontrado...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
