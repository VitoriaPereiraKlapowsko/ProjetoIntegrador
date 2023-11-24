package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Raca;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioRaca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroRaca implements Initializable {

    @FXML
    private TextField descricaoRaca;

    @FXML
    private TextField nomeRaca;

    @FXML
    private TextField tfCodigo;

    @FXML
    private Button btAcao;

    private RepositorioRaca repositorio;

    private Raca anterior;



    public CadastroRaca(RepositorioRaca repositorio) {
        this.repositorio = repositorio;
    }

     public CadastroRaca(RepositorioRaca repositorio, Raca anterior) {
        this.repositorio = repositorio;
        this.anterior = anterior;
    }

    @FXML
    void abaListarRaca(ActionEvent event) {
        App.pushScreen("LISTARRACAS");
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }
   

    @FXML
    void confirmar(ActionEvent event) {
        String nome = nomeRaca.getText();
        String descricao = descricaoRaca.getText();
        String codigo = tfCodigo.getText();

        Resultado resultado;

        if (anterior == null) {
            resultado = repositorio.cadastrarRaca(nome, descricao);
        } else {
            resultado = repositorio.alterarRaca(Integer.valueOf(codigo), nome, descricao);
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
            nomeRaca.setText(anterior.getNome());
            descricaoRaca.setText(anterior.getDescricao());

            btAcao.setText("Atualizar");
        }
    }

}

    

