package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroPet {

    @FXML
    private TextArea condicoesFisicas;

    @FXML
    private ChoiceBox<?> especie;

    @FXML
    private TextField nomePet;

    @FXML
    private ChoiceBox<?> porte;

    @FXML
    private ChoiceBox<?> raca;

    @FXML
    private ChoiceBox<?> sexo;

    @FXML
    private TextArea tratamentosEObs;


 
    @FXML
    private void abaCliente() {
       App.pushScreen("CADASTROCLIENTE");
    }
}
