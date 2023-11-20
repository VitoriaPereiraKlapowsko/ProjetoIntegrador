package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.models.Status;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioAgendamento;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroAgendamento implements Initializable {

    @FXML
    private ComboBox<Cliente> comboCliente;

    @FXML
    private ComboBox<?> comboHorario;

    @FXML
    private ComboBox<Pet> comboPet;

    @FXML
    private ComboBox<Servico> comboServicos;

    @FXML
    private ComboBox<Status> comboStatus;

    @FXML
    private DatePicker dataDaReserva;

    @FXML
    private TextField horarioReserva;

    @FXML
    private TextArea observacoes;

    @FXML
    private TextField tosadorOuBanhista;

    @FXML
    private TextField valorTotal;

    private RepositorioAgendamento repositorioAgendamento;
    private RepositorioCliente repositorioCliente;
    private RepositorioPet repositorioPet;
    private RepositorioServico repositorioServico;
    private RepositorioStatus repositorioStatus;

    public CadastroAgendamento(RepositorioAgendamento repositorioAgendamento, RepositorioCliente repositorioCliente,
            RepositorioPet repositorioPet, RepositorioServico repositorioServico, RepositorioStatus repositorioStatus) {
        this.repositorioAgendamento = repositorioAgendamento;
        this.repositorioCliente = repositorioCliente;
        this.repositorioPet = repositorioPet;
        this.repositorioServico = repositorioServico;
        this.repositorioStatus = repositorioStatus;
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void confirmar(ActionEvent event) {
        Cliente cliente = comboCliente.getValue();
        Pet pet = comboPet.getValue();
        LocalDate dataReserva = dataDaReserva.getValue();
        String horarioDaReserva = horarioReserva.getText();
        Servico servico = comboServicos.getValue();
        Status status = comboStatus.getValue();
        String tosadorBanhista = tosadorOuBanhista.getText();
        String observacoesGerais = observacoes.getText();
        float valorTotalReserva = Float.parseFloat(valorTotal.getText());

        Resultado resultado = repositorioAgendamento.cadastrarAgendamento(cliente, pet, servico,
                status, dataReserva,horarioDaReserva, valorTotalReserva,tosadorBanhista,
                observacoesGerais);
        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }
        alert.showAndWait();
    }

    @FXML
    void listaDeAgedamentos() {
        App.pushScreen("LISTARAGENDAMENTOS");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado resultadoAgendamento = repositorioAgendamento.listarAgendamentos();

        if (resultadoAgendamento.foiSucesso()) {
            List<Status> statusList = (List<Status>) resultadoAgendamento.comoSucesso().getObj();
            comboStatus.getItems().addAll(statusList);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoAgendamento.getMsg());
            alert.showAndWait();
        }

        Resultado resultadoClientes = repositorioCliente.listarClientes();
        if (resultadoClientes.foiSucesso()) {
            List<Cliente> clientes = (List<Cliente>) resultadoClientes.comoSucesso().getObj();
            comboCliente.getItems().addAll(clientes);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoClientes.getMsg());
            alert.showAndWait();
        }

        Resultado resultadoPets = repositorioPet.listarPet();
        if (resultadoPets.foiSucesso()) {
            List<Pet> pets = (List<Pet>) resultadoPets.comoSucesso().getObj();
            comboPet.getItems().addAll(pets);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoPets.getMsg());
            alert.showAndWait();
        }

        Resultado resultadoServicos = repositorioServico.listarServicos();
        if (resultadoServicos.foiSucesso()) {
            List<Servico> servicos = (List<Servico>) resultadoServicos.comoSucesso().getObj();
            comboServicos.getItems().addAll(servicos);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoServicos.getMsg());
            alert.showAndWait();
        }

        Resultado resultadoStatus = repositorioStatus.listarStatus();
        if (resultadoStatus.foiSucesso()) {
            List<Status> Status = (List<Status>) resultadoStatus.comoSucesso().getObj();
            comboStatus.getItems().addAll(Status);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoStatus.getMsg());
            alert.showAndWait();
        }
    }
}
