package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Principal {
    

    @FXML
    private void cadastrarGenero(){
        App.pushScreen("CADASTROGENERO");
    }

    @FXML
    private void cadastrarArtista(){
        App.pushScreen("CADASTROARTISTA");
    }

    @FXML
    private void listarGeneros(){
        App.pushScreen("LISTARGENEROS");
    }

    @FXML
    private void listarArtistas(){
        App.pushScreen("LISTARARTISTAS");
    }

    @FXML
    private void cadastrarMusica(){
        App.pushScreen("CADASTRARMUSICA");
    }

    @FXML
    private void listarMusicas(){
        App.pushScreen("LISTARMUSICAS");
    }
    
    @FXML
    private void cadastrarPlaylist() {
        App.pushScreen("CADASTRARPLAYLIST");
    }
    
    @FXML
    private void listarPlaylist() {
        App.pushScreen("LISTARPLAYLIST");
    }

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
}
