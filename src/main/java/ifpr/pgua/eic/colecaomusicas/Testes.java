package ifpr.pgua.eic.colecaomusicas;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCGeneroDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCMusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCPlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPlaylist;

public class Testes {

    public static void main(String[] args) {

        ArtistaDAO artistaDAO = new JDBCArtistaDAO(FabricaConexoes.getInstance());
        GeneroDAO generoDAO = new JDBCGeneroDAO(FabricaConexoes.getInstance());
        MusicaDAO musicaoDAO = new JDBCMusicaDAO(FabricaConexoes.getInstance());
        PlaylistDAO playlistDAO = new JDBCPlaylistDAO(FabricaConexoes.getInstance());

        RepositorioMusicas repositorio = new RepositorioMusicas(musicaoDAO, artistaDAO, generoDAO);
        RepositorioPlaylist repositorioPlaylists = new RepositorioPlaylist(playlistDAO, musicaoDAO);


        Resultado resultado = repositorio.listar();
        System.out.println(resultado.getMsg());
        List<Musica> lista = (List) resultado.comoSucesso().getObj();

        for (Musica musica : lista) {
            System.out.println(musica.getNome());
            System.out.println(musica.getArtista().getNome());
            System.out.println(musica.getGenero().getNome());
        }

        Resultado resultadoPlaylists = repositorioPlaylists.listarPlaylists();
        System.out.println(resultadoPlaylists.getMsg());
        List<Playlist> listaPlaylists = (List) resultadoPlaylists.comoSucesso().getObj();

        for (Playlist playlist : listaPlaylists) {
            System.out.println(playlist.getNome());

        }

    }
}
