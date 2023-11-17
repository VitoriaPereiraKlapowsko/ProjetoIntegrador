package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLogin {

    @FXML
    private Button botaoEntrar;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    void entrar(ActionEvent event) {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText() != null ? campoSenha.getText() : "";


        if (usuario.isEmpty() || senha.isEmpty()) {
            // Exibir mensagem se usuário ou senha estiverem em branco
            exibirAlerta("ERRO","Por favor, preencha usuário e senha!");
        } else if (autenticacaoBemSucedida(usuario, senha)) {
            Principal(event);
        } else {
            exibirAlerta("ERRO","Usuário ou Senha estão incorretos!");
        }
    }

    // Função de exemplo para simular autenticação
    private boolean autenticacaoBemSucedida(String usuario, String senha) {
        // Substitua isso pela lógica real de autenticação
        return "usuario".equals(usuario) && "senha".equals(senha);
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
    
}
