package  ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListarClientes {

    @FXML
    private ListView<Cliente> listaClientes;

    @FXML
    void confirmar(ActionEvent event) {
         App.popScreen();
    }

    @FXML
    void deletar(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

}
