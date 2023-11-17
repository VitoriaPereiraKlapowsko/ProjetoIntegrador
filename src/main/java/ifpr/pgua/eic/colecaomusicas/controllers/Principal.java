package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Principal  {

    @FXML
    private ImageView myImageView;

    Image myImage = new Image(getClass().getResourceAsStream("patinha.png"));

    public void displayImage() {
        myImageView.setImage(myImage);
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

    @FXML
    void sair(ActionEvent event) {
        Platform.exit();
    }
}
