package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
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
    private ComboBox<Cliente> comboTutor;

    @FXML
    private TextField portePet;

    @FXML
    private ComboBox<Raca> comboRaca;

    @FXML
    private TextField sexoPet;

    @FXML
    private TextArea tratamentosEObs;

    private RepositorioPet repositorioPet;
    private RepositorioCliente repositorioCliente;
    private RepositorioRaca repositorioRaca;

    public CadastroPet(RepositorioPet repositorioPet, RepositorioRaca repositorioRaca, RepositorioCliente repositorioCliente) {
        this.repositorioPet = repositorioPet;
        this.repositorioRaca = repositorioRaca;
        this.repositorioCliente = repositorioCliente;
    }

    @FXML
    private void cancelar() {
        App.pushScreen("CADASTROCLIENTE");
    }

    @FXML
    void confirmar(ActionEvent event) {
        Cliente cliente = comboTutor.getValue();
        String nome = nomePet.getText();
        Raca raca = comboRaca.getValue();
        String sexo = sexoPet.getText();
        String porte = portePet.getText();
        String especie = especiePet.getText();
        LocalDate dataDeNascimento = dataNascimento.getValue();
        String tratamentosEspeciais = tratamentosEObs.getText();
        String condicoesFisicas = condicoesFisicasPet.getText();

        Resultado resultado = repositorioPet.cadastrarPet(cliente,nome, raca, sexo, porte, especie, dataDeNascimento,
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
        Resultado resultadoRaca = repositorioRaca.listarRaca();

        if (resultadoRaca.foiSucesso()) {
            List<Raca> raca = (List<Raca>) resultadoRaca.comoSucesso().getObj();
            comboRaca.getItems().addAll(raca);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoRaca.getMsg());
            alert.showAndWait();
        }

        Resultado resultadoClientes = repositorioCliente.listarClientes();
        if (resultadoClientes.foiSucesso()) {
            List<Cliente> clientes = (List<Cliente>) resultadoClientes.comoSucesso().getObj();
            comboTutor.getItems().addAll(clientes);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoClientes.getMsg());
            alert.showAndWait();
        }
    }
    

}
