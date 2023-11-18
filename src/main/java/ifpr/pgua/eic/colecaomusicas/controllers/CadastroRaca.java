package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastroRaca {

    @FXML
    private TextField descricaoRaca;

    @FXML
    private TextField nomeRaca;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    private RepositorioRaca repositorio;

    public CadastroRaca(RepositorioRaca repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        String nome = nomeRaca.getText();
        String descricao = descricaoRaca.getText();

        Resultado resultado = repositorio.cadastrarRaca(nome, descricao);
        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }
        alert.showAndWait();
    }

    
}
