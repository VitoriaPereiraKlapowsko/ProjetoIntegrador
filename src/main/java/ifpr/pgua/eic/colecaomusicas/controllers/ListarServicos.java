package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListarServicos {

    @FXML
    private ListView<?> listaServicos;

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

}
