package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;

public interface PlaylistDAO {
    Resultado criar(Playlist playlist);
    Resultado cadastrar(int idPlaylist, int idMusica);
    Resultado listar();
}
