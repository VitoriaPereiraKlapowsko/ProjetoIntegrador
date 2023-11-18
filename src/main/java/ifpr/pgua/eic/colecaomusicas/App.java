package ifpr.pgua.eic.colecaomusicas;

import ifpr.pgua.eic.colecaomusicas.controllers.CadastroAgendamento;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroServico;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroCliente;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroFuncionario;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroPet;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroRaca;
import ifpr.pgua.eic.colecaomusicas.controllers.CalendarioAgendamentos;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarAgendamentos;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarFuncionarios;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarServicos;
import ifpr.pgua.eic.colecaomusicas.controllers.Principal;
import ifpr.pgua.eic.colecaomusicas.controllers.TelaLogin;
import ifpr.pgua.eic.colecaomusicas.daos.ClienteDAO;
import ifpr.pgua.eic.colecaomusicas.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.daos.FuncionarioDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCClienteDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCFuncionarioDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCPetDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCRacaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.JDBCServicoDAO;
import ifpr.pgua.eic.colecaomusicas.daos.PetDAO;
import ifpr.pgua.eic.colecaomusicas.daos.RacaDAO;
import ifpr.pgua.eic.colecaomusicas.daos.ServicoDAO;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioFuncionario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends BaseAppNavigator {

        private ClienteDAO clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance());
        private RepositorioCliente repositorioCliente = new RepositorioCliente(clienteDAO);

        private PetDAO petDAO = new JDBCPetDAO(FabricaConexoes.getInstance());
        private RepositorioPet repositorioPet = new RepositorioPet(petDAO);

        private ServicoDAO servicoDAO = new JDBCServicoDAO(FabricaConexoes.getInstance());
        private RepositorioServico repositorioServico = new RepositorioServico(servicoDAO);

        private RacaDAO racaDAO = new JDBCRacaDAO(FabricaConexoes.getInstance());
        private RepositorioRaca repositorioRaca = new RepositorioRaca(racaDAO);

        private FuncionarioDAO funcionarioDAO = new JDBCFuncionarioDAO(FabricaConexoes.getInstance());
        private RepositorioFuncionario repositorioFuncionario = new RepositorioFuncionario(funcionarioDAO);

        public static void main(String[] args) {
                launch();
        }

        @Override
        public String getHome() {
                return "LOGIN";
        }

        @Override
        public String getAppTitle() {
                return "Agendamento PetShop";
        }

        @Override
        public void registrarTelas() {
                registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o -> new Principal()));
                registraTela("LOGIN", new ScreenRegistryFXML(App.class, "login.fxml", o -> new TelaLogin()));

                registraTela("CADASTROCLIENTE",
                                new ScreenRegistryFXML(App.class,
                                                "cadastro_cliente.fxml",
                                                o -> new CadastroCliente(repositorioCliente)));

                registraTela("CADASTROPET",
                                new ScreenRegistryFXML(App.class,
                                                "cadastro_pet.fxml",
                                                o -> new CadastroPet(repositorioPet, repositorioRaca,
                                                                repositorioCliente)));

                registraTela("CADASTROSERVICO",
                                new ScreenRegistryFXML(App.class,
                                                "cadastro_servico.fxml",
                                                o -> new CadastroServico(repositorioServico)));

                registraTela("LISTARSERVICO",
                                new ScreenRegistryFXML(App.class,
                                                "lista_servicos_cadastrados.fxml",
                                                o -> new ListarServicos(repositorioServico)));

                registraTela("CADASTRORACA",
                                new ScreenRegistryFXML(App.class,
                                                "cadastro_raca.fxml",
                                                o -> new CadastroRaca(repositorioRaca)));

                registraTela("CALENDARIOAGENDAMENTOS",
                                new ScreenRegistryFXML(App.class,
                                                "calendario_agendamentos.fxml",
                                                o -> new CalendarioAgendamentos()));

                registraTela("CADASTRARGENDAMENTO",
                                new ScreenRegistryFXML(App.class,
                                                "cadastro_agendamento.fxml",
                                                o -> new CadastroAgendamento()));

                registraTela("CADASTROFUNCIONARIO",
                                new ScreenRegistryFXML(App.class,
                                                "cadastro_funcionario.fxml",
                                                o -> new CadastroFuncionario(repositorioFuncionario)));

                registraTela("LISTARAGENDAMENTOS",
                                new ScreenRegistryFXML(App.class,
                                                "consulta_agendamento.fxml",
                                                o -> new ListarAgendamentos()));

                registraTela("LISTARFUNCIONARIOS",
                                new ScreenRegistryFXML(App.class,
                                                "lista_funcionarios.fxml",
                                                o -> new ListarFuncionarios(repositorioFuncionario)));
        }
}