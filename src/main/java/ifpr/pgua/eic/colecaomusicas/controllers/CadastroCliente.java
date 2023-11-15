package ifpr.pgua.eic.colecaomusicas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastroCliente {

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
    private TextField sobrenomeCliente;

    @FXML
    private TextField telefoneCliente;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    private RepositorioCliente repositorio;

    public CadastroCliente(RepositorioCliente repositorio){
        this.repositorio = repositorio;
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

        Resultado resultado = repositorio.cadastrarCliente(nome, sobrenome, cpfCnpj, inscricaoEstadual, email, endereco, telefone);
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
}