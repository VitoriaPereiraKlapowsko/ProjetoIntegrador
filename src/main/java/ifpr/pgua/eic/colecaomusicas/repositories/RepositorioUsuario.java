package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.UsuarioDAO;
import ifpr.pgua.eic.colecaomusicas.models.Usuario;

public class RepositorioUsuario {
    private UsuarioDAO dao;
    private ArrayList<Usuario> usuarios;

    public RepositorioUsuario(UsuarioDAO dao){
        usuarios = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarUsuario(String usuario, String senha){

        if(usuario.isEmpty() || usuario.isBlank()){

            return Resultado.erro("Descrição inválida!");
        }
  
        Usuario usuarios = new Usuario(usuario,senha);
        return dao.criar(usuarios);
    }

    public Resultado buscarUsuario(String usuario, String senha) {
        return dao.buscar(usuario,senha);
    }
}
