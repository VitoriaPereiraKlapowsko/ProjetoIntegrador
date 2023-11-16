package ifpr.pgua.eic.colecaomusicas;

import ifpr.pgua.eic.colecaomusicas.controllers.CadastrarPlaylist;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastrarServico;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroArtista;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroCliente;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroGenero;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroMusica;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroPet;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarArtistas;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarGeneros;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarMusicas;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarPlaylist;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarServicos;
import ifpr.pgua.eic.colecaomusicas.controllers.Principal;
import ifpr.pgua.eic.colecaomusicas.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.ClienteDAO;
import ifpr.pgua.eic.colecaomusicas.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCClienteDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCGeneroDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCMusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCPetDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCPlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCServicoDAO;
import ifpr.pgua.eic.colecaomusicas.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PetDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.daos.ServicoDAO;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioArtistas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioGeneros;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPlaylist;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private ClienteDAO clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance());
    private RepositorioCliente repositorioCliente = new RepositorioCliente(clienteDAO);

    private PetDAO petDAO = new JDBCPetDAO(FabricaConexoes.getInstance());
    private RepositorioPet repositorioPet = new RepositorioPet(petDAO);

    private ServicoDAO servicoDAO = new JDBCServicoDAO(FabricaConexoes.getInstance());
    private RepositorioServico repositorioServico = new RepositorioServico(servicoDAO);

    private ArtistaDAO artistaDAO = new JDBCArtistaDAO(FabricaConexoes.getInstance());
    private RepositorioArtistas repositorioArtistas = new RepositorioArtistas(artistaDAO);
    
    private GeneroDAO generoDAO = new JDBCGeneroDAO(FabricaConexoes.getInstance());
    private RepositorioGeneros repositorioGeneros = new RepositorioGeneros(generoDAO);

    private MusicaDAO musicaDAO = new JDBCMusicaDAO(FabricaConexoes.getInstance());
    private RepositorioMusicas repositorioMusicas = new RepositorioMusicas(musicaDAO, artistaDAO, generoDAO);

    private PlaylistDAO playlistDAO = new JDBCPlaylistDAO(FabricaConexoes.getInstance()); 
    private RepositorioPlaylist repositorioPlaylists = new RepositorioPlaylist(playlistDAO, musicaDAO); 

    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }


    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Agendamento PetShop";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));
        registraTela("CADASTROGENERO",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_genero.fxml", 
                      o->new CadastroGenero(repositorioGeneros)
                  )
        );
        registraTela("LISTARGENEROS",
                  new ScreenRegistryFXML(App.class, 
                      "listar_generos.fxml", 
                      o->new ListarGeneros(repositorioGeneros)
                  )
        );
        registraTela("CADASTROARTISTA",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_artista.fxml", 
                      o->new CadastroArtista(repositorioArtistas)
                  )
        );
        registraTela("LISTARARTISTAS",
                  new ScreenRegistryFXML(App.class, 
                      "listar_artistas.fxml", 
                      o->new ListarArtistas(repositorioArtistas)
                  )
        );
        registraTela("CADASTRARMUSICA",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_musica.fxml", 
                      o->new CadastroMusica(repositorioMusicas,repositorioGeneros,repositorioArtistas)
                  )
        );
        registraTela("LISTARMUSICAS",
                  new ScreenRegistryFXML(App.class, 
                      "listar_musicas.fxml", 
                      o->new ListarMusicas(repositorioMusicas)
                  )
        );

          registraTela("CADASTRARPLAYLIST",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_playlist.fxml", 
                      o->new CadastrarPlaylist(repositorioMusicas, repositorioPlaylists)
                  )
        );

         registraTela("LISTARPLAYLIST",
                  new ScreenRegistryFXML(App.class, 
                      "listar_playlist.fxml", 
                      o->new ListarPlaylist(repositorioPlaylists)
                  )
        );

         registraTela("CADASTROCLIENTE",
                  new ScreenRegistryFXML(App.class, 
                      "cadastro_cliente.fxml", 
                      o->new CadastroCliente(repositorioCliente)
                  )
        );

         registraTela("CADASTROPET",
                  new ScreenRegistryFXML(App.class, 
                      "cadastro_pet.fxml", 
                      o->new CadastroPet(repositorioPet)
                  )
        );

        registraTela("CADASTROSERVICO",
                  new ScreenRegistryFXML(App.class, 
                      "cadastro_servico.fxml", 
                      o->new CadastrarServico(repositorioServico)
                  )
        );

         registraTela("LISTARSERVICO",
                  new ScreenRegistryFXML(App.class, 
                      "lista_servicos_cadastrados.fxml", 
                      o->new ListarServicos(repositorioServico)
                  )
        );
    }

    public static ListarPlaylist getController(String string) {
        return null;
    }

}