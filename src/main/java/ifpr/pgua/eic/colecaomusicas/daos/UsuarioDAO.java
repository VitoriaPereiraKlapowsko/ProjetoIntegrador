package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Usuario;

public interface UsuarioDAO {
    Resultado criar(Usuario usuario);
    Resultado buscar(String usuario, String senha);
}
