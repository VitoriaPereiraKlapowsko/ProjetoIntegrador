package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Usuario;

public class JDBCUsuarioDAO implements UsuarioDAO {
    private FabricaConexoes fabrica;

    public JDBCUsuarioDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Usuario usuario) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("INSERT INTO tb_usuarios(usuario, senha) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, usuario.getUsuario());
            pstm.setString(2, usuario.getSenha());
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                usuario.setCodigo(codigo);

                return Resultado.sucesso("Usuário cadastrado com sucesso!", usuario);
            }
            return Resultado.erro("Falha ao cadastrar usuário.");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscar(String usuario, String senha) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tb_usuarios WHERE usuario=? AND senha=?");
            pstm.setString(1, usuario);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                String usuarioEncontrado = rs.getString("usuario");
                String senhaEncontrada = rs.getString("senha");
                int codigo = rs.getInt("codigo");

                Usuario usuarioEncontradoObj = new Usuario(usuarioEncontrado, senhaEncontrada);
                usuarioEncontradoObj.setCodigo(codigo);

                return Resultado.sucesso("Usuário encontrado", usuarioEncontradoObj);
            } else {
                return Resultado.erro("Usuário não encontrado!");
            }

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

}
