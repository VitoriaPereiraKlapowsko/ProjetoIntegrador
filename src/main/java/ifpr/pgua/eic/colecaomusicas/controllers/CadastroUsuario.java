package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Usuario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroUsuario {

    @FXML
    private Button botaoEntrar;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoUsuario;

    private RepositorioUsuario repositorio;

    public CadastroUsuario(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();

        String msg;

        Resultado rs = repositorio.cadastrarUsuario(usuario, senha);

        App.popScreen();
        Alert alert;
        msg = rs.getMsg();
        if (rs.foiErro()) {
            alert = new Alert(AlertType.ERROR, msg);
        } else {
            alert = new Alert(AlertType.INFORMATION, msg);

        }

        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}
