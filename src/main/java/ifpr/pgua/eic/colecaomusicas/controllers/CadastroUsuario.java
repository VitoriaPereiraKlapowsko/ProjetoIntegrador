package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
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

        Resultado resultadoCadastro = repositorio.cadastrarUsuario(usuario, senha);

        Alert alert;
        if (resultadoCadastro != null && resultadoCadastro.foiSucesso()) {
            Resultado resultadoAutenticacao = repositorio.buscarUsuario(usuario, senha);

            if (resultadoAutenticacao != null && resultadoAutenticacao.foiSucesso()) {
                alert = new Alert(AlertType.INFORMATION, "Usuário cadastrado com sucesso!");
            } else {
                alert = new Alert(AlertType.ERROR, "Erro após o cadastro!");
            }
        } else {
            String mensagemErro = resultadoCadastro != null ? resultadoCadastro.getMsg() : "Erro ao cadastrar usuário!";
            alert = new Alert(AlertType.ERROR, mensagemErro);
        }

        App.popScreen();
        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}
