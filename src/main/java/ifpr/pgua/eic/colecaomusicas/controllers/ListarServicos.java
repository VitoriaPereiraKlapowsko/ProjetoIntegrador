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

public class ListarServicos implements Initializable {

    @FXML
    private ListView<Servico> listaServicos;

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

    private RepositorioServico repositorio;

    public ListarServicos(RepositorioServico repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaServicos.getItems().clear();
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
        Servico servicoSelecionado = listaServicos.getSelectionModel().getSelectedItem();

        if (servicoSelecionado != null) {
            Resultado resultado = repositorio.deletarServico(servicoSelecionado.getCodigoServico());

            if (resultado.foiSucesso()) {
                listaServicos.getItems().remove(servicoSelecionado);
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

    @FXML
    void editar(ActionEvent event) {

    }

}
