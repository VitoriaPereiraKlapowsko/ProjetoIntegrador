package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

public class ListarAgendamentos {

    @FXML
    private Button buscar;

    @FXML
    private DatePicker dataReserva;

    @FXML
    private ListView<?> listaClientes;

    @FXML
    private ListView<?> listaDatasDeReserva;

    @FXML
    private ListView<?> listaHorarios;

    @FXML
    private ListView<?> listaPets;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void confirmar(ActionEvent event) {

    }

}
