package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Usuario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin {

    @FXML
    private Button botaoEntrar;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    private RepositorioUsuario repositorio;
    

    public TelaLogin(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void entrar(ActionEvent event) {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText() != null ? campoSenha.getText() : "";

        if (usuario.isEmpty() || usuario.isBlank() && senha.isEmpty() || senha.isBlank()) {
            exibirAlerta("ERRO", "Por favor, preencha usuário e senha!");
        } else {
            Resultado resultadoBusca = repositorio.buscarUsuario(usuario, senha);

            if (resultadoBusca != null) {
                Usuario usuarioAutenticado = (Usuario) resultadoBusca.comoSucesso().getObj();
                Principal(event);
            } else {
                exibirAlerta("ERRO", resultadoBusca.getMsg());
            }
        }
    }

    @FXML
    private void Principal(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    void cadastrar(ActionEvent event) {
        App.pushScreen("CADASTROUSUARIO");
    }

}
