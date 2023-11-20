package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioFuncionario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioStatus;
import javafx.application.Platform;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Principal {

    private RepositorioServico repositorioServico;
    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioRaca repositorioRaca;
    private RepositorioCliente repositorioCliente;
    private RepositorioStatus repositorioStatus;

    public Principal(RepositorioServico repositorioServico, RepositorioFuncionario repositorioFuncionario,
            RepositorioRaca repositorioRaca, RepositorioCliente repositorioCliente,
            RepositorioStatus repositorioStatus) {

        this.repositorioServico = repositorioServico;
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioRaca = repositorioRaca;
        this.repositorioCliente = repositorioCliente;
        this.repositorioStatus = repositorioStatus;
    }

    @FXML
    private void cadastrarCliente() {
        App.pushScreen("CADASTROCLIENTE", o -> new CadastroCliente(repositorioCliente));
    }

    @FXML
    void cadastrarServico(ActionEvent event) {
        // App.pushScreen("CADASTROSERVICO");
        App.pushScreen("CADASTROSERVICO", o -> new CadastroServico(repositorioServico));
    }

    @FXML
    void listarServicos(ActionEvent event) {
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void cadastrarRaca(ActionEvent event) {
        App.pushScreen("CADASTRORACA", o -> new CadastroRaca(repositorioRaca));
    }

    @FXML
    void calendarioAgendamentos(ActionEvent event) {
        App.pushScreen("CALENDARIOAGENDAMENTOS");
    }

    @FXML
    void cadastrarAgendamento(ActionEvent event) {
        App.pushScreen("CADASTRARGENDAMENTO");
    }

    @FXML
    void cadastroFuncionario(ActionEvent event) {
        // App.pushScreen("CADASTROFUNCIONARIO");
        App.pushScreen("CADASTROFUNCIONARIO", o -> new CadastroFuncionario(repositorioFuncionario));
    }

    @FXML
    void listaDeAgedamentos(ActionEvent event) {
        App.pushScreen("LISTARAGENDAMENTOS");
    }

    @FXML
    void cadastrarStatus(ActionEvent event) {
        App.pushScreen("CADASTROSTATUS", o -> new CadastroStatus(repositorioStatus));

    }

    @FXML
    void configuracao(ActionEvent event) {
        exibirAlerta("ATENÇÃO", "Tela em construção...");
    }

    @FXML
    void sair(ActionEvent event) {
        Platform.exit();
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
