package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastroFuncionario {

    @FXML
    private TextField cpfFuncionario;

    @FXML
    private DatePicker dataDeNascimento;

    @FXML
    private TextField email;

    @FXML
    private TextField endereco;

    @FXML
    private TextField funcao;

    @FXML
    private TextField nomeFuncionario;

    @FXML
    private TextField numero;

    @FXML
    private TextField senha;

    @FXML
    private ChoiceBox<?> sexo;

    @FXML
    private TextField sobrenome;

    @FXML
    private TextField telefone;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void confirmar(ActionEvent event) {

    }

}
