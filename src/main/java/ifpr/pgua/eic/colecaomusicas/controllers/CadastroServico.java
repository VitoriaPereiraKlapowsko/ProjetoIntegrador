package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Servico;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroServico implements Initializable{

    @FXML
    private TextField descricaoServico;

    @FXML
    private TextField valorServico;

    @FXML
    private TextField tfCodigo;

    @FXML
    private Button btAcao;

    private Servico anterior;

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    private RepositorioServico repositorio;

    public CadastroServico(RepositorioServico repositorio){
        this.repositorio = repositorio;
    }

    public CadastroServico(RepositorioServico repositorio, Servico anterior) {
        this.repositorio = repositorio;
        this.anterior = anterior;
    }
   
    @FXML
    void confirmar(ActionEvent event) {
        String descricao = descricaoServico.getText();
        String svalor = valorServico.getText();
        String codigo = tfCodigo.getText();

        String msg = "";

        int valor = 0;
        try {
            valor = Integer.valueOf(svalor);
        } catch (NumberFormatException e) {
            msg = "Valor inválido!";
        }

        Resultado resultado; 

        if (anterior == null) {
            resultado = repositorio.cadastrarServico(descricao, valor);
        } else {
            resultado = repositorio.alterarServico(Integer.valueOf(codigo), descricao, valor);
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
    void abaListarServicos(ActionEvent event) {
        App.pushScreen("LISTARSERVICO");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (anterior != null) {
            tfCodigo.setText(anterior.getCodigo() + "");
            valorServico.setText(Float.toString(anterior.getValor()));
            descricaoServico.setText(anterior.getDescricao());

            btAcao.setText("Atualizar");
        }
    }
}
