package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Genero;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroPet implements Initializable {

    @FXML
    private TextArea condicoesFisicasPet;

    @FXML
    private DatePicker dataNascimento;

    @FXML
    private TextField especiePet;

    @FXML
    private TextField nomePet;

    @FXML
    private TextField portePet;

    @FXML
    private ChoiceBox<Raca> racaPet;

    @FXML
    private TextField sexoPet;

    @FXML
    private TextArea tratamentosEObs;

    private RepositorioPet repositorio;

    public CadastroPet(RepositorioPet repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    private void cancelar() {
        App.pushScreen("CADASTROCLIENTE");
    }

    @FXML
    void confirmar(ActionEvent event) {
        String nome = nomePet.getText();
        String raca = racaPet.getValue().toString();
        String sexo = sexoPet.getText();
        String porte = portePet.getText();
        String especie = especiePet.getText();
        LocalDate dataDeNascimento = dataNascimento.getValue();
        String tratamentosEspeciais = tratamentosEObs.getText();
        String condicoesFisicas = condicoesFisicasPet.getText();

        Resultado resultado = repositorio.cadastrarPet(nome, raca, sexo, porte, especie, dataDeNascimento,
                tratamentosEspeciais, condicoesFisicas);
        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }
        alert.showAndWait();
    }

    @FXML
    private void abaCliente() {
        App.pushScreen("CADASTROCLIENTE");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultado = repositorio.listarRaca();

        if (resultado.foiSucesso()) {
            List<Raca> list = (List<Raca>) resultado.comoSucesso().getObj();
            racaPet.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }
    }

}
