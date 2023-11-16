package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastrarAgendamento {

    @FXML
    private ChoiceBox<?> cliente;

    @FXML
    private DatePicker dataDaReserva;

    @FXML
    private ChoiceBox<?> horarioAgendamento;

    @FXML
    private TextArea obsServico;

    @FXML
    private TextArea observacoes;

    @FXML
    private ChoiceBox<?> pet;

    @FXML
    private ChoiceBox<?> servico;

    @FXML
    private ChoiceBox<?> status;

    @FXML
    private TextField tosadorOuBanhista;

    @FXML
    private TextField valorTotal;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void confirmar(ActionEvent event) {

    }

    @FXML
    void listaDeAgedamentos() {
        App.pushScreen("LISTARAGENDAMENTOS");
    }
}
