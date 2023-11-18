package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Servico;

public class JDBCServicoDAO implements ServicoDAO{
     private FabricaConexoes fabrica;

    public JDBCServicoDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Servico servico) {
        try(Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO tb_servico(descricao, valor) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
           
            pstm.setString(1,servico.getDescricao());
            pstm.setInt(2, servico.getValor());
            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                servico.setCodigoServico(codigo);

                return Resultado.sucesso("Serviço cadastrado com sucesso!!!", servico);
            }
            return Resultado.erro("Ops.. deu um erro!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_servico");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Servico> lista = new ArrayList<>();

            while(rs.next()){
                int codigoServico = rs.getInt("codigo_do_servico");
                String descricao = rs.getString("descricao");
                int valor = rs.getInt("valor");
                
                Servico servico = new Servico(codigoServico, descricao, valor);
                lista.add(servico);
            }
            
            return Resultado.sucesso("Lista de serviços", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int codigoServico) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM tb_servico WHERE codigo_do_servico = ?");
            pstm.setInt(1, codigoServico);

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                return Resultado.sucesso("Serviço deletado com sucesso!", con);
            }
            return Resultado.erro("Serviço não encontrado...");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
