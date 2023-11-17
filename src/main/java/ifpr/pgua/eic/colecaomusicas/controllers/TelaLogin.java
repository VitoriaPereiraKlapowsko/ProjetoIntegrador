package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaLogin {

    @FXML
    private Button botaoEntrar;

    @FXML
    private TextField campoUsuario;

    @FXML
    private TextField campoSenha;

    @FXML
    void entrar(ActionEvent event) {
        String usuario = campoUsuario.getText();
        String senha = campoSenha.getText();


        if (autenticacaoBemSucedida(usuario, senha)) {
            Principal(event);
        } else {
            System.out.println("Autenticação falhou! Exiba uma mensagem de erro ou tome a ação apropriada.");
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
}
