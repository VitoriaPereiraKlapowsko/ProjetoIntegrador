package ifpr.pgua.eic.colecaomusicas;

import ifpr.pgua.eic.colecaomusicas.controllers.CadastrarAgendamento;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastrarPlaylist;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastrarServico;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroArtista;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroCliente;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroFuncionario;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroGenero;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroMusica;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroPet;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroRaca;
import ifpr.pgua.eic.colecaomusicas.controllers.CalendarioAgendamentos;
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
import ifpr.pgua.eic.colecaomusicas.daos.JDBCRacaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCServicoDAO;
import ifpr.pgua.eic.colecaomusicas.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PetDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.daos.RacaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.ServicoDAO;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioArtistas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioGeneros;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPlaylist;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
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

    private RacaDAO racaDAO = new JDBCRacaDAO(FabricaConexoes.getInstance());
    private RepositorioRaca repositorioRaca = new RepositorioRaca(racaDAO);

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

        registraTela("CADASTRORACA",
                  new ScreenRegistryFXML(App.class, 
                      "cadastro_raca.fxml", 
                      o->new CadastroRaca(repositorioRaca)
                  )
        );

        registraTela("CALENDARIOAGENDAMENTOS",
                  new ScreenRegistryFXML(App.class, 
                      "calendario_agendamentos.fxml", 
                      o->new CalendarioAgendamentos()
                  )
        );

          registraTela("FAZERAGENDAMENTO",
                  new ScreenRegistryFXML(App.class, 
                      "cadastro_agendamento.fxml", 
                      o->new CadastrarAgendamento()
                  )
        );
    

        registraTela("CADASTROFUNCIONARIO",
                  new ScreenRegistryFXML(App.class, 
                      "cadastro_funcionario.fxml", 
                      o->new CadastroFuncionario()
                  )
        );
}

    public static ListarPlaylist getController(String string) {
        return null;
    }

}