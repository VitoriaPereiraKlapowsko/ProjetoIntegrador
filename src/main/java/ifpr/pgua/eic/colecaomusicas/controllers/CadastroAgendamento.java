package ifpr.pgua.eic.colecaomusicas.controllers;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.models.Status;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioAgendamento;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPet;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CadastroAgendamento {

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
    private TextArea obsServico;

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

    public CadastroAgendamento(RepositorioAgendamento repositorioAgendamento, RepositorioCliente repositorioCliente, RepositorioPet repositorioPet,
            RepositorioServico repositorioServico, RepositorioStatus repositorioStatus) {
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
        Servico servico = comboServicos.getValue();
        Status status = comboStatus.getValue();
        String tosadorBanhista = tosadorOuBanhista.getText();
        String observacaoServico = obsServico.getText();
        String observacoesGerais = observacoes.getText();
        float valorTotalReserva = Float.parseFloat(valorTotal.getText());

        Resultado resultado = repositorioAgendamento.cadastrarAgendamento(cliente, pet, dataReserva, servico, status, tosadorBanhista, observacaoServico, observacoesGerais, valorTotalReserva);
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
}
