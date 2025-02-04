package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Funcionario;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastroFuncionario implements Initializable {

    @FXML
    private TextField cpfFuncionario;

    @FXML
    private DatePicker dataDeNascimento;

    @FXML
    private TextField emailFuncionario;

    @FXML
    private TextField enderecoFuncionario;

    @FXML
    private TextField funcaoFuncionario;

    @FXML
    private TextField nomeFuncionario;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField sexoFuncionario;

    @FXML
    private TextField sobrenomeFuncionario;

    @FXML
    private Button btAcao;

    @FXML
    private Button aba;

    @FXML
    private TextField telefoneFuncionario;

    private RepositorioFuncionario repositorio;

    private Funcionario anterior;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    public CadastroFuncionario(RepositorioFuncionario repositorio) {
        this.repositorio = repositorio;
    }

    public CadastroFuncionario(RepositorioFuncionario repositorio, Funcionario anterior) {
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    @FXML
    void confirmar(ActionEvent event) {
        String nome = nomeFuncionario.getText();
        String sobrenome = sobrenomeFuncionario.getText();
        int telefone = Integer.parseInt(telefoneFuncionario.getText());
        String funcao = funcaoFuncionario.getText();
        String cpf = cpfFuncionario.getText();
        String sexo = sexoFuncionario.getText();
        String endereco = enderecoFuncionario.getText();
        LocalDate dataNascimento = dataDeNascimento.getValue();
        String email = emailFuncionario.getText();
        String codigo = tfCodigo.getText();

        Resultado resultado;

        if (anterior == null) {
            resultado = repositorio.cadastrarFuncionario(nome, sobrenome, telefone, funcao, cpf,
                    sexo, endereco, dataNascimento, email);
        } else {
            resultado = repositorio.alterarFuncionario(Integer.valueOf(codigo),nome, sobrenome, telefone,
                    funcao, cpf, sexo, endereco, dataNascimento, email);
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
    private void abaListar(ActionEvent event) {
        App.pushScreen("LISTARFUNCIONARIOS");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (anterior != null) {
            tfCodigo.setText(anterior.getCodigo() + "");
            nomeFuncionario.setText(anterior.getNome());
            sobrenomeFuncionario.setText(anterior.getSobrenome());
            telefoneFuncionario.setText(Integer.toString(anterior.getTelefone()));
            funcaoFuncionario.setText(anterior.getFuncao());
            cpfFuncionario.setText(anterior.getCpf());
            sexoFuncionario.setText(anterior.getSexo());
            enderecoFuncionario.setText(anterior.getEndereco());
            dataDeNascimento.setValue(anterior.getDataNasc());
            emailFuncionario.setText(anterior.getEmail());

            btAcao.setText("Atualizar");
        }
    }

}
