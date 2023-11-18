package  ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

public class ListarClientes implements Initializable{

    @FXML
    private ListView<Cliente> listaClientes;

    private RepositorioCliente repositorio;

    public ListarClientes(RepositorioCliente repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
         App.popScreen();
    }

    @FXML
    void deletar(ActionEvent event) {
        Cliente clienteSelecionado = listaClientes.getSelectionModel().getSelectedItem();
    
        if (clienteSelecionado != null) {
            Resultado resultado = repositorio.deletarCliente(clienteSelecionado.getCodigo());
    
            if (resultado.foiSucesso()) {
                listaClientes.getItems().remove(clienteSelecionado);
                Alert alert = new Alert(AlertType.INFORMATION, "Cliente deletado com sucesso!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhum cliente selecionado!");
            alert.showAndWait();
        }
    }
    

    @FXML
    void editar(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaClientes.getItems().clear();
        Resultado resultado = repositorio.listarClientes();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            List lista = (List)resultado.comoSucesso().getObj();
            listaClientes.getItems().addAll(lista);
        }
    }
}
