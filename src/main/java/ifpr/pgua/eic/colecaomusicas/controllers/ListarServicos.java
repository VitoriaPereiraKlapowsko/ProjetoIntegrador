package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ListarServicos implements Initializable {

    @FXML
    private ListView<Servico> listaServicos;

    private Servico selecionado;

    private RepositorioServico repositorio;

    public ListarServicos(RepositorioServico repositorio) {
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
            App.pushScreen("CADASTROSERVICO", o -> new CadastroServico(repositorio, selecionado));
        }
    }

    @FXML
    private void selecionar() {
        selecionado = listaServicos.getSelectionModel().getSelectedItem();
    }

     @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaServicos.getItems().clear();

        listaServicos.getSelectionModel() .setSelectionMode(SelectionMode.MULTIPLE);

        Resultado resultado = repositorio.listarServicos();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista = (List) resultado.comoSucesso().getObj();
            listaServicos.getItems().addAll(lista);
        }

    }

    @FXML
    void deletar(ActionEvent event) {
        selecionado = listaServicos.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Resultado resultado = repositorio.deletarServico(selecionado.getCodigoServico());

            if (resultado.foiSucesso()) {
                listaServicos.getItems().remove(selecionado);
                Alert alert = new Alert(AlertType.INFORMATION, "Serviço deletado com sucesso!!!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhum serviço selecionado...");
            alert.showAndWait();
        }
    }

}
