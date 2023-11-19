package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CadastroCliente implements Initializable {

    @FXML
    private TextField cpfCnpjCliente;

    @FXML
    private TextField emailCliente;

    @FXML
    private TextField enderecoCliente;

    @FXML
    private TextField inscricaoEstadualCliente;

    @FXML
    private TextField nomeCliente;

    @FXML
    private ComboBox<Raca> comboRaca;

    @FXML
    private TextField sobrenomeCliente;

    @FXML
    private TextField telefoneCliente;

    @FXML
    private TextField tfCodigo;

    @FXML
    private Button btAcao;

    private Cliente anterior;

    @FXML
    void cancelar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    private RepositorioCliente repositorio;

    public CadastroCliente(RepositorioCliente repositorio) {
        this.repositorio = repositorio;
    }

    public CadastroCliente(RepositorioCliente repositorio, Cliente anterior) {
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    @FXML
    void confirmar(ActionEvent event) {
        String nome = nomeCliente.getText();
        String sobrenome = sobrenomeCliente.getText();
        String sCpfCnpjCliente = cpfCnpjCliente.getText();
        String sInscricaoEstadualCliente = inscricaoEstadualCliente.getText();
        String email = emailCliente.getText();
        String endereco = enderecoCliente.getText();
        String stelefoneCliente = telefoneCliente.getText();
        String codigo = tfCodigo.getText();

        String msg = "";

        int cpfCnpj = 0;
        try {
            cpfCnpj = Integer.valueOf(sCpfCnpjCliente);
        } catch (NumberFormatException e) {
            msg = "CPF ou CNPJ inválido!";
        }

        int inscricaoEstadual = 0;
        try {
            inscricaoEstadual = Integer.valueOf(sInscricaoEstadualCliente);
        } catch (NumberFormatException e) {
            msg = "Inscrição Estadual inválida!";
        }

        int telefone = 0;
        try {
            telefone = Integer.valueOf(stelefoneCliente);
        } catch (NumberFormatException e) {
            msg = "Telefone inválido!";
        }

        Resultado resultado;

        if (anterior == null) {
            resultado = repositorio.cadastrarCliente(nome, sobrenome, cpfCnpj, inscricaoEstadual, endereco, telefone,
                    email);
        } else {
            resultado = repositorio.alterarCliente(Integer.valueOf(codigo), nome, sobrenome, cpfCnpj, inscricaoEstadual,
                    endereco, telefone, email);
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
    void cadastrarPet() {
        App.pushScreen("CADASTROPET");
    }

    @FXML
    void listarClientes(ActionEvent event) {
        App.pushScreen("LISTARCLIENTES");
    }

    @FXML
    void listarPets(ActionEvent event) {
        App.pushScreen("LISTARPET");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (anterior != null) {
            tfCodigo.setText(anterior.getCodigo() + "");
            nomeCliente.setText(anterior.getNome());
            sobrenomeCliente.setText(anterior.getSobrenome());
            cpfCnpjCliente.setText(Integer.toString(anterior.getCpfCnpj()));
            inscricaoEstadualCliente.setText(Integer.toString(anterior.getInscricaoEstadual()));
            enderecoCliente.setText(anterior.getEndereco());
            telefoneCliente.setText(Integer.toString(anterior.getTelefone()));
            emailCliente.setText(anterior.getEmail());

            btAcao.setText("Atualizar");
        }
    }
}
