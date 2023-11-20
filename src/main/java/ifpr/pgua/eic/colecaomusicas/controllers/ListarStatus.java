package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Status;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ListarStatus implements Initializable {

    @FXML
    private ListView<Status> listaStatus;

    private Status selecionado;

    private RepositorioStatus repositorio;

    public ListarStatus(RepositorioStatus repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void editar(ActionEvent event) {
        if (selecionado != null) {
            App.pushScreen("CADASTROSTATUS", o-> new CadastroStatus(repositorio, selecionado));
        }
    }

    @FXML
    private void selecionar() {
        selecionado = listaStatus.getSelectionModel().getSelectedItem();
    }

     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaStatus.getItems().clear();

        listaStatus.getSelectionModel() .setSelectionMode(SelectionMode.MULTIPLE);

        Resultado resultado = repositorio.listarStatus();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista = (List) resultado.comoSucesso().getObj();
            listaStatus.getItems().addAll(lista);
        }

    }

    @FXML
    void deletar(ActionEvent event) {
        selecionado = listaStatus.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Resultado resultado = repositorio.deletarStatus(selecionado.getCodigo());

            if (resultado.foiSucesso()) {
                listaStatus.getItems().remove(selecionado);
                Alert alert = new Alert(AlertType.INFORMATION, "Status deletado com sucesso!!!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhum Status selecionado...");
            alert.showAndWait();
        }
    }

    @FXML
    void atualizar(ActionEvent event) {
         listaStatus.getItems().clear();

        // Busque novamente os dados do repositório
        Resultado resultadoLista = repositorio.listarStatus();

        if (resultadoLista.foiSucesso()) {
            List<Status> listaAtualizada = (List<Status>) resultadoLista.comoSucesso().getObj();
            listaStatus.getItems().addAll(listaAtualizada);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoLista.getMsg());
            alert.showAndWait();
        }
    }

}
