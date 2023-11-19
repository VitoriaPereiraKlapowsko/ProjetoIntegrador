package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Agendamento;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioAgendamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ListarAgendamentos implements Initializable{

    @FXML
    private Button buscar;

    @FXML
    private DatePicker dataReserva;

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
}
