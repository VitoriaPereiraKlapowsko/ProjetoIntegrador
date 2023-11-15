package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Cliente;

public class JDBCClienteDAO implements ClienteDAO{
    
    private FabricaConexoes fabrica;

    public JDBCClienteDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Cliente cliente) {
        try(Connection con = fabrica.getConnection()){
            
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO tb_cliente(nome, sobrenome, cpf_cnpj, inscricao_estadual, endereco, telefone, email) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
           
            pstm.setString(1,cliente.getNome());
            pstm.setString(2, cliente.getSobrenome());
            pstm.setInt(3, cliente.getCpfCnpj());
            pstm.setInt(4, cliente.getInscricaoEstadual());
            pstm.setString(5, cliente.getEndereco());
            pstm.setInt(6, cliente.getTelefone());
            pstm.setString(7, cliente.getEmail());
            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                cliente.setCodigo(codigo);

                return Resultado.sucesso("Cliente cadastrado com sucesso!!!", cliente);
            }
            return Resultado.erro("Ops.. deu um erro!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_clientes");

            ResultSet rs = pstm.executeQuery();
            ArrayList<Cliente> lista = new ArrayList<>();

            while(rs.next()){
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                int cpfCnpj = rs.getInt("cpf_cnpj");
                int inscricaoEstadual = rs.getInt("inscricao_estadual");
                String endereco = rs.getString("endereco");
                int telefone = rs.getInt("telefone");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(codigo, nome, sobrenome, cpfCnpj, inscricaoEstadual, email, endereco, telefone);
                lista.add(cliente);
            }
            
            return Resultado.sucesso("Lista de cliente", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    
    }
}
