package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Playlist;

public class JDBCPlaylistDAO implements PlaylistDAO {

    private static final String INSERTSQL = "INSERT INTO playlist(nomePlaylist) VALUES (?)";
    private static final String INSERT_PLAYLISTSMUSICAS_SQL = "INSERT INTO playlistMusica(idPlaylist, idMusica) VALUES (?, ?)";
    private static final String SELECTSQL = "SELECT * FROM playlist";

    private FabricaConexoes fabrica;

    public JDBCPlaylistDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Playlist playlist) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, playlist.getNome());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    playlist.setId(id);
                }
                return Resultado.sucesso("Playlist cadastrada", playlist);
            }
            return Resultado.erro("Erro ao cadastrar a playlist");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado cadastrar(int idPlaylist, int idMusica) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstmPlaylistMusica = con.prepareStatement(INSERT_PLAYLISTSMUSICAS_SQL);
            pstmPlaylistMusica.setInt(1, idPlaylist);
            pstmPlaylistMusica.setInt(2, idMusica);
    
            int retPlaylistMusica = pstmPlaylistMusica.executeUpdate();
    
            if (retPlaylistMusica == 1) {
                return Resultado.sucesso("Música adicionada na playlist!!!", con);
            } else {
                return Resultado.erro("Erro em adicionar a música na playlist...");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Playlist> lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nomePlaylist");

                Playlist playlist = new Playlist(id, nome);
                lista.add(playlist);
            }

            return Resultado.sucesso("Playlists listadas", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

}
