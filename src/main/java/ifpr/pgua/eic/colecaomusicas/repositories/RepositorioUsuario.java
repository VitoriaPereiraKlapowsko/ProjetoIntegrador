package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.UsuarioDAO;
import ifpr.pgua.eic.colecaomusicas.models.Usuario;

public class RepositorioUsuario {
    private UsuarioDAO dao;
    private ArrayList<Usuario> usuarios;

    public RepositorioUsuario(UsuarioDAO dao) {
        usuarios = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado cadastrarUsuario(String nomeUsuario, String senha) {
        if (nomeUsuario.isEmpty() || nomeUsuario.isBlank()) {
            return Resultado.erro("Nome de usuário inválido!");
        }

        if (senha.isEmpty() || senha.isBlank()) {
            return Resultado.erro("Senha inválida!");
        }

        Usuario novoUsuario = new Usuario(nomeUsuario, senha);
        return dao.criar(novoUsuario);
    }

    public Resultado buscarUsuario(String usuario, String senha) {
        return dao.buscar(usuario, senha);
    }
}
