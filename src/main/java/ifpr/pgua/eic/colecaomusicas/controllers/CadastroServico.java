package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastroServico {

    @FXML
    private TextField descricaoServico;

    @FXML
    private TextField valorServico;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    private RepositorioServico repositorio;

    public CadastroServico(RepositorioServico repositorio){
        this.repositorio = repositorio;
    }
   
    @FXML
    void confirmar(ActionEvent event) {
        String descricao = descricaoServico.getText();
        String svalor = valorServico.getText();

        String msg = "";

        int valor = 0;
        try {
            valor = Integer.valueOf(svalor);
        } catch (NumberFormatException e) {
            msg = "Valor inválido!";
        }

        Resultado resultado = repositorio.cadastrarServico(descricao, valor);
        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }
        alert.showAndWait();
    }

    @FXML
    void abaListarServicos(ActionEvent event) {
        App.pushScreen("LISTARSERVICO");
    }
}
