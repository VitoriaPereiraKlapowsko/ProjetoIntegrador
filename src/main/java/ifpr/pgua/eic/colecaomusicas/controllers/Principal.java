package ifpr.pgua.eic.colecaomusicas.controllers;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioFuncionario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class Principal  {

    private RepositorioServico repositorioServico;
    private RepositorioFuncionario repositorioFuncionario;

    public Principal(RepositorioServico repositorioServico, RepositorioFuncionario repositorioFuncionario){
        this.repositorioServico = repositorioServico;
        this.repositorioFuncionario = repositorioFuncionario;
    }

    @FXML
    private void cadastrarCliente() {
        App.pushScreen("CADASTROCLIENTE");
    }

    @FXML
    void cadastrarServico(ActionEvent event) {
       // App.pushScreen("CADASTROSERVICO"); 
        App.pushScreen("CADASTROSERVICO", o -> new CadastroServico(repositorioServico));  
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
    void cadastrarAgendamento(ActionEvent event) {
        App.pushScreen("CADASTRARGENDAMENTO");  
    }
    
    @FXML
    void cadastroFuncionario(ActionEvent event) {
        //App.pushScreen("CADASTROFUNCIONARIO"); 
        App.pushScreen("CADASTROFUNCIONARIO", o -> new CadastroFuncionario(repositorioFuncionario)); 
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
