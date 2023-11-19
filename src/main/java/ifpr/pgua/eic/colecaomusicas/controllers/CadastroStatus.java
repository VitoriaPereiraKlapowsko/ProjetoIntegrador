package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.models.Status;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroStatus implements Initializable{

    private RepositorioStatus repositorio;

    private Status anterior;

    @FXML
    private Button btAcao;

    @FXML
    private TextField descricaoStatus;

    @FXML
    private TextField tfCodigo;

    @FXML
    void abaListarStatus(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    public CadastroStatus(RepositorioStatus repositorio) {
        this.repositorio = repositorio;
    }

    public CadastroStatus(RepositorioStatus repositorio, Status anterior) {
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    @FXML
    void confirmar(ActionEvent event) {
        String descricao = descricaoStatus.getText();
        String codigo = tfCodigo.getText();

        Resultado resultado;

        if (anterior == null) {
            resultado = repositorio.cadastrarStatus(descricao);
        } else {
            resultado = repositorio.alterarStatus(Integer.valueOf(codigo), descricao);
        }

        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (anterior != null) {
            tfCodigo.setText(anterior.getCodigo() + "");
            descricaoStatus.setText(anterior.getDescricao());

            btAcao.setText("Atualizar");
        }
    }
}
