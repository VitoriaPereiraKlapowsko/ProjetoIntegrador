package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Principal {

    @FXML
    private void cadastrarCliente() {
        App.pushScreen("CADASTROCLIENTE");
    }

    @FXML
    void cadastrarServico(ActionEvent event) {
        App.pushScreen("CADASTROSERVICO");  
    }

    @FXML
    void listarServicos(ActionEvent event) {
        App.pushScreen("LISTARSERVICO");  
    }
    
    @FXML
    void cadastrarRaca(ActionEvent event) {
        App.pushScreen("CADASTRORACA");  
    }

    @FXML
    void calendarioAgendamentos(ActionEvent event) {
        App.pushScreen("CALENDARIOAGENDAMENTOS");  
    }

    @FXML
    void fazerAgendamento(ActionEvent event) {
        App.pushScreen("FAZERAGENDAMENTO");  
    }
    
    @FXML
    void cadastroFuncionario(ActionEvent event) {
        App.pushScreen("CADASTROFUNCIONARIO");  
    }
     
    @FXML
    void listaDeAgedamentos(ActionEvent event) {
        App.pushScreen("LISTARAGENDAMENTOS");  
    }
}
