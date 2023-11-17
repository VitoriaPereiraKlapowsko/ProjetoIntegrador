package  ifpr.pgua.eic.colecaomusicas.controllers;

import java.util.List;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Funcionario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ListarFuncionarios {

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btDeletar;

    @FXML
    private Button btEditar;

    

    @FXML
    private ListView<Funcionario> listaFuncionarios;

    private Funcionario selecionado;

    private RepositorioFuncionario repositorio;

      public ListarFuncionarios(RepositorioFuncionario repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void deletar(ActionEvent event) {

    }

    @FXML
    void mostrarSelecionados(){
        List<Funcionario> selecionados = listaFuncionarios
                                      .getSelectionModel()
                                      .getSelectedItems();
        
        String str = "";

        for(Funcionario genero:selecionados){
            str += genero.getNome()+";";
        }

        Alert alert = new Alert(AlertType.INFORMATION, str);
        alert.showAndWait();
    
    }

    @FXML
    private void selecionar(){
        selecionado = listaFuncionarios.getSelectionModel().getSelectedItem();
    }


    @FXML
    void editar(ActionEvent event) {
        if(selecionado != null ){
            CadastroFuncionario(event);
        }
    }

    @FXML
    private void CadastroFuncionario(ActionEvent event) {
        App.pushScreen("CADASTROFUNCIONARIO");
    }

    

}
