package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

public class ListarRacas implements Initializable {

    @FXML
    private ListView<Raca> listaRacas;

    private RepositorioRaca repositorio;
    private Raca selecionado;

    public ListarRacas(RepositorioRaca repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void alterar(ActionEvent event) {
        if (selecionado != null) {
            App.pushScreen("CADASTRORACA", o -> new CadastroRaca(repositorio, selecionado));
        }
    }

    @FXML
    void deletar(ActionEvent event) {
        selecionado = listaRacas.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Resultado resultado = repositorio.deletarRaca(selecionado.getCodigo());

            if (resultado.foiSucesso()) {
                listaRacas.getItems().remove(selecionado);
                Alert alert = new Alert(AlertType.INFORMATION, "Raça deletada com sucesso!!!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhuma raça selecionada...");
            alert.showAndWait();
        }
    }


    @FXML
    void mostrarSelecionados(ActionEvent event) {

    }

    @FXML
    void selecionar(MouseEvent event) {
        selecionado = listaRacas.getSelectionModel().getSelectedItem();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaRacas.getItems().clear();

        listaRacas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Resultado resultado = repositorio.listarRaca();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista = (List) resultado.comoSucesso().getObj();
            listaRacas.getItems().addAll(lista);
        }
    }
}
