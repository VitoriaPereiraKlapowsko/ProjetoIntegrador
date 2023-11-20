package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Agendamento;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioAgendamento;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ListarAgendamentos implements Initializable {

    @FXML
    private ListView<Agendamento> listaAgendamentos;

    private RepositorioAgendamento repositorio;

    public ListarAgendamentos(RepositorioAgendamento repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void cancelar(ActionEvent event) {
        Agendamento agendamentoSelecionado = listaAgendamentos.getSelectionModel().getSelectedItem();

        if (agendamentoSelecionado != null) {
            Resultado resultado = repositorio.deletarAgendamento(agendamentoSelecionado.getCodigo());

            if (resultado.foiSucesso()) {
                listaAgendamentos.getItems().remove(agendamentoSelecionado);
                Alert alert = new Alert(AlertType.INFORMATION, "Agendamento deletado com sucesso!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhum Agendamento selecionado!");
            alert.showAndWait();
        }
    }

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaAgendamentos.getItems().clear();

        listaAgendamentos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Resultado resultado = repositorio.listarAgendamentos();
        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista = (List) resultado.comoSucesso().getObj();
            listaAgendamentos.getItems().addAll(lista);
        }
    }

    @FXML
    void concluirAgendamento(ActionEvent event) {
        Agendamento agendamentoSelecionado = listaAgendamentos.getSelectionModel().getSelectedItem();

        if (agendamentoSelecionado != null) {
            Resultado resultado = repositorio.atualizarAgendamento(agendamentoSelecionado.getCodigo(), 5);

            if (resultado.foiSucesso()) {
                // Atualizar a lista após a atualização bem-sucedida
                Resultado resultadoLista = repositorio.listarAgendamentos();

                if (resultadoLista.foiSucesso()) {
                    List lista = (List) resultadoLista.comoSucesso().getObj();

                    // Use Platform.runLater para garantir que as operações sejam executadas na
                    // Thread da Interface do Usuário
                    Platform.runLater(() -> {
                        listaAgendamentos.getItems().clear();
                        listaAgendamentos.getItems().addAll(lista);
                    });
                } else {
                    Alert alert = new Alert(AlertType.ERROR, resultadoLista.getMsg());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhum Agendamento selecionado!");
            alert.showAndWait();
        }
    }

}
