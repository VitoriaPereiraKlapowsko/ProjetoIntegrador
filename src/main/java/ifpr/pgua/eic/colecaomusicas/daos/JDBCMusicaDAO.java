package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Artista;
import ifpr.pgua.eic.colecaomusicas.models.Genero;
import ifpr.pgua.eic.colecaomusicas.models.Musica;

public class JDBCMusicaDAO implements MusicaDAO {
    private static final String INSERTSQL = "INSERT INTO musicas(nome,duracao,anoLancamento,artistaId,generoId) VALUES (?,?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM musicas";
    private static final String SELECT_MUSICAS_POR_PLAYLIST_SQL = "SELECT m.id, m.nome, m.duracao, m.anoLancamento, m.artistaId, m.generoId "
            + "FROM musicas m " + "INNER JOIN playlistMusica pm ON m.id = pm.idMusica " +
            "WHERE pm.idPlaylist = ?";

    private FabricaConexoes fabrica;

    public JDBCMusicaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Musica musica) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, musica.getNome());
            pstm.setInt(2, musica.getDuracao());
            pstm.setInt(3, musica.getAnoLancamento());
            pstm.setInt(4, musica.getArtista().getId());
            pstm.setInt(5, musica.getGenero().getId());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                musica.setId(id);

                return Resultado.sucesso("Música cadastrada", musica);
            }
            return Resultado.erro("Erro desconhecido!");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Musica> lista = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int duracao = rs.getInt("duracao");
                int anoLancamento = rs.getInt("anoLancamento");

                // iremos buscar artista e genero através do repositório
                Musica musica = new Musica(id, nome, anoLancamento, duracao, null, null);

                lista.add(musica);
            }

            return Resultado.sucesso("Musicas listadas", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }
    public Artista buscarArtistaPorId(int artistaId) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM artistas WHERE id=?");
            pstm.setInt(1, artistaId);
    
            ResultSet rs = pstm.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
    
                return new Artista(id, nome, contato);
            } else {
                return null; 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    public Genero buscarGeneroPorId(int generoId) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM generos WHERE id=?");
            pstm.setInt(1, generoId);
    
            ResultSet rs = pstm.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
    
                return new Genero(id, nome);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            return null;
        }
    }
    

    @Override
    public Resultado buscarMusicasPorPlaylistId(int playlistId) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECT_MUSICAS_POR_PLAYLIST_SQL);
            pstm.setInt(1, playlistId);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Musica> musicas = new ArrayList<>();
            while (rs.next()) {
                int idMusica = rs.getInt("id");
                String nome = rs.getString("nome");
                int duracao = rs.getInt("duracao");
                int anoLancamento = rs.getInt("anoLancamento");
                int artistaId = rs.getInt("artistaId");
                int generoId = rs.getInt("generoId");
            
                Artista artista = buscarArtistaPorId(artistaId);
                Genero genero = buscarGeneroPorId(generoId);
            
                Musica musica = new Musica(idMusica, nome, anoLancamento, duracao, artista, genero);
                musicas.add(musica);
            }

            return Resultado.sucesso("Músicas da playlist listadas", musicas);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Musica nova) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

}