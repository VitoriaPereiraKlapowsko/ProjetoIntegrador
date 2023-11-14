package ifpr.pgua.eic.colecaomusicas.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;

public class RepositorioPlaylist {

    private List<Playlist> playlists = new ArrayList<>();
    private PlaylistDAO playlistDAO;
    private MusicaDAO musicaDAO;

    public RepositorioPlaylist(PlaylistDAO playlistDAO, MusicaDAO musicaDAO) {
        this.playlistDAO = playlistDAO;
        this.musicaDAO = musicaDAO;
        carregarPlaylistsDoBanco();
    }

    public PlaylistDAO getPlaylistDAO() {
        return playlistDAO;
    }

    private void carregarPlaylistsDoBanco() {
        Resultado resultado = playlistDAO.listar();

        if (resultado.foiSucesso()) {
            playlists.addAll((List<Playlist>) resultado.comoSucesso().getObj());
        }
    }

    public Resultado cadastrarPlaylist(String nome, List<Musica> musicas) {
        if (nome == null || nome.isEmpty()) {
            return Resultado.erro("Nome da playlist não pode estar vazio.");
        }
        if (musicas == null || musicas.isEmpty()) {
            return Resultado.erro("A playlist deve conter pelo menos uma música.");
        }

        Playlist novaPlaylist = new Playlist(0, nome);
        Resultado resultadoCadastro = playlistDAO.criar(novaPlaylist);

        if (resultadoCadastro.foiSucesso()) {
            novaPlaylist = (Playlist) resultadoCadastro.comoSucesso().getObj();

            for (Musica musica : musicas) {
                Resultado resultadoAdicao = playlistDAO.cadastrar(novaPlaylist.getId(), musica.getId());

                if (!resultadoAdicao.foiSucesso()) {
                    return Resultado.erro("Erro ao adicionar músicas à playlist: " + resultadoAdicao.getMsg());
                }
            }
            playlists.add(novaPlaylist);

            return Resultado.sucesso("Playlist cadastrada com sucesso.", novaPlaylist);
        } else {
            return Resultado.erro("Erro ao cadastrar a playlist: " + resultadoCadastro.getMsg());
        }
    }

    public Resultado listar() {
        Resultado resultadoPlaylists = playlistDAO.listar();

        if (resultadoPlaylists.foiSucesso()) {
            List<Playlist> playlists = (List<Playlist>) resultadoPlaylists.comoSucesso().getObj();

            for (Playlist playlist : playlists) {
                Resultado resultadoMusicas = musicaDAO.buscarMusicasPorPlaylistId(playlist.getId());

                if (resultadoMusicas.foiSucesso()) {
                    List<Musica> musicas = (List<Musica>) resultadoMusicas.comoSucesso().getObj();
                    playlist.setMusicas(musicas);
                } else {
                    return Resultado.erro("Erro ao buscar músicas da playlist: " + resultadoMusicas.getMsg());
                }
            }

            return Resultado.sucesso("Playlists listadas", playlists);
        } else {
            return Resultado.erro("Erro ao buscar playlists: " + resultadoPlaylists.getMsg());
        }
    }

    public Resultado listarPlaylists() {
        if (playlists.isEmpty()) {
            return Resultado.erro("Não há playlists cadastradas.");
        }
        return Resultado.sucesso("Playlists listadas", new ArrayList<>(playlists));
    }
}
