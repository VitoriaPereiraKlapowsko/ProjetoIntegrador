package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
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
import javafx.scene.control.Button;
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
    private Button btAcao;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextArea tratamentosEObs;

    private RepositorioPet repositorioPet;
    private RepositorioCliente repositorioCliente;
    private RepositorioRaca repositorioRaca;

    private RepositorioPet repositorio;
    private Pet anterior;

    private RepositorioPet repositorioPet2;

    public CadastroPet(RepositorioPet repositorioPet, RepositorioRaca repositorioRaca,
            RepositorioCliente repositorioCliente) {
        this.repositorioPet = repositorioPet;
        this.repositorioRaca = repositorioRaca;
        this.repositorioCliente = repositorioCliente;
    }

    public CadastroPet(RepositorioPet repositorio, Pet anterior) {
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    public CadastroPet(RepositorioPet repositorioPet2) {
        this.repositorioPet2 = repositorioPet2;
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
        String codigo = tfCodigo.getText();

        Resultado resultado;
        if (anterior == null) {
            resultado = repositorioPet.cadastrarPet(cliente, raca, nome, sexo, porte, especie, dataDeNascimento,
                    tratamentosEspeciais, condicoesFisicas);
        } else {
            resultado = repositorio.alterarPet(Integer.valueOf(codigo), cliente, raca, nome, sexo, porte, especie,
                    dataDeNascimento,
                    tratamentosEspeciais, condicoesFisicas);
        }

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
        if (repositorioRaca != null) {
            Resultado resultadoRaca = repositorioRaca.listarRaca();
            if (resultadoRaca != null && resultadoRaca.foiSucesso()) {
                List<Raca> racas = (List<Raca>) resultadoRaca.comoSucesso().getObj();
                comboRaca.getItems().addAll(racas);
            }
        }

        if (repositorioCliente != null) {
            Resultado resultadoClientes = repositorioCliente.listarClientes();
            if (resultadoClientes != null && resultadoClientes.foiSucesso()) {
                List<Cliente> clientes = (List<Cliente>) resultadoClientes.comoSucesso().getObj();
                comboTutor.getItems().addAll(clientes);
            }
        }

        if (anterior != null) {
            tfCodigo.setText(anterior.getCodigo() + "");
            comboTutor.getSelectionModel().select(anterior.getCliente());
            nomePet.setText(anterior.getNome());
            comboRaca.getSelectionModel().select(anterior.getRaca());
            sexoPet.setText(anterior.getSexo());
            portePet.setText(anterior.getPorte());
            especiePet.setText(anterior.getEspecie());
            dataNascimento.setValue(anterior.getDataDeNascimento());
            tratamentosEObs.setText(anterior.getTratamentosEspeciais());
            condicoesFisicasPet.setText(anterior.getCondicoesFisicas());

            btAcao.setText("Atualizar");
        }
    }
}
