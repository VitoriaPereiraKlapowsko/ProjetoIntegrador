package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CalendarioAgendamentos {

    @FXML
    private ListView<?> domingo;

    @FXML
    private ListView<?> quarta;

    @FXML
    private ListView<?> quinta;

    @FXML
    private ListView<?> sabado;

    @FXML
    private ListView<?> segunda;

    @FXML
    private ListView<?> sexta;

    @FXML
    private ListView<?> terca;

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

}
