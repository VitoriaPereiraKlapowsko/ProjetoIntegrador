package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Funcionario;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;

public class ListarPet implements Initializable{

    @FXML
    private ListView<Pet> listaPets;

    private RepositorioPet repositorio;

    private Pet selecionado;

    public ListarPet(RepositorioPet repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void deletar(ActionEvent event) {
        if (selecionado != null) {
            Resultado resultado = repositorio.deletarPet(selecionado.getCodigo());

            if (resultado.foiSucesso()) {
                listaPets.getItems().remove(selecionado);
                Alert alert = new Alert(AlertType.INFORMATION, "Raça deletada com sucesso!!!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Nenhuma raça selecionada...");
            alert.showAndWait();
        }
    }

    @FXML
    void alterar(ActionEvent event) {
        if (selecionado != null) {
            App.pushScreen("CADASTROPET",o-> new CadastroPet(repositorio, selecionado));
        }
    }
    
    @FXML
    void selecionar(MouseEvent event) {
        selecionado = listaPets.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaPets.getItems().clear();
        Resultado resultado = repositorio.listarPet();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            List lista = (List)resultado.comoSucesso().getObj();
            listaPets.getItems().addAll(lista);
        }
    }

    @FXML
    void atualizar(ActionEvent event) {
         listaPets.getItems().clear();

        // Busque novamente os dados do repositório
        Resultado resultadoLista = repositorio.listarPet();

        if (resultadoLista.foiSucesso()) {
            List<Pet> listaAtualizada = (List<Pet>) resultadoLista.comoSucesso().getObj();
            listaPets.getItems().addAll(listaAtualizada);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoLista.getMsg());
            alert.showAndWait();
        }
    }

}
