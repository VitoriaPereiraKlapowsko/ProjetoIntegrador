package ifpr.pgua.eic.colecaomusicas.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioFuncionario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastroFuncionario {

    @FXML
    private TextField cpfFuncionario;

    @FXML
    private DatePicker dataDeNascimento;

    @FXML
    private TextField emailFuncionario;

    @FXML
    private TextField enderecoFuncionario;

    @FXML
    private TextField funcaoFuncionario;

    @FXML
    private TextField nomeFuncionario;

    @FXML
    private TextField senhaFuncionario;

    @FXML
    private TextField sexoFuncionario;

    @FXML
    private TextField sobrenomeFuncionario;

    @FXML
    private TextField loginFuncionario;

    @FXML
    private Button aba;

    @FXML
    private TextField telefoneFuncionario;

    private RepositorioFuncionario repositorio;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    public CadastroFuncionario(RepositorioFuncionario repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        String login = loginFuncionario.getText();
        String senha = senhaFuncionario.getText();
        String nome = nomeFuncionario.getText();
        String sobrenome = sobrenomeFuncionario.getText();
        int telefone = Integer.parseInt(telefoneFuncionario.getText());
        String funcao = funcaoFuncionario.getText();
        String cpf = cpfFuncionario.getText(); 
        String sexo = sexoFuncionario.getText();
        String endereco = enderecoFuncionario.getText();
        LocalDate dataNascimento = dataDeNascimento.getValue();
        String email = emailFuncionario.getText();

        String msg = "";

        int sCpf = 0;
        try {
            sCpf = Integer.valueOf(cpf);
        } catch (NumberFormatException e) {
            msg = "CPF inválido!";
        }

        Resultado resultado = repositorio.cadastrarFuncionario(login,senha,nome,sobrenome,telefone,funcao,cpf,sexo,endereco,dataNascimento,email);
        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }
        alert.showAndWait();
    }

    @FXML
    private void abaListar() {
        ListarFuncionario(null);
    }

    @FXML
    void ListarFuncionario(ActionEvent event) {
        App.pushScreen("LISTARFUNCIONARIOS");  
    }

}
