package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ListarFuncionarios implements Initializable {

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btDeletar;

    @FXML
    private Button btEditar;

    @FXML
    private ListView<Funcionario> listaFuncionarios;

    private Funcionario selecionado;

    private RepositorioFuncionario repositorio;

    public ListarFuncionarios(RepositorioFuncionario repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void confirmar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void deletar(ActionEvent event) {
        Funcionario funcionarioSelecionado = listaFuncionarios.getSelectionModel().getSelectedItem();

        if (funcionarioSelecionado != null) {
            Resultado resultado = repositorio.deletarFuncionario(funcionarioSelecionado.getCodigo());

            if (resultado.foiErro()) {
                Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION, "Funcionário deletado com sucesso!!!");
                alert.showAndWait();

                listaFuncionarios.getItems().remove(funcionarioSelecionado);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Selecione um funcionário para deletar, por favor...");
            alert.showAndWait();
        }
    }

    @FXML
    void mostrarSelecionados() {
        List<Funcionario> selecionados = listaFuncionarios
                .getSelectionModel()
                .getSelectedItems();

        String str = "";

        for (Funcionario funcionario : selecionados) {
            str += funcionario.getNome() + ";";
            str += funcionario.getCodigo() + ";";
        }

        Alert alert = new Alert(AlertType.INFORMATION, str);
        alert.showAndWait();

    }

    @FXML
    private void selecionar() {
        selecionado = listaFuncionarios.getSelectionModel().getSelectedItem();
    }

    @FXML
    void alterar(ActionEvent event) {
        if (selecionado != null) {
            App.pushScreen("CADASTROFUNCIONARIO", o -> new CadastroFuncionario(repositorio, selecionado));
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaFuncionarios.getItems().clear();

        listaFuncionarios.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        Resultado resultado = repositorio.listarFuncionarios();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List lista = (List) resultado.comoSucesso().getObj();
            listaFuncionarios.getItems().addAll(lista);
        }

    }

    @FXML
    void atualizar(ActionEvent event) {
         listaFuncionarios.getItems().clear();

        // Busque novamente os dados do repositório
        Resultado resultadoLista = repositorio.listarFuncionarios();

        if (resultadoLista.foiSucesso()) {
            List<Funcionario> listaAtualizada = (List<Funcionario>) resultadoLista.comoSucesso().getObj();
            listaFuncionarios.getItems().addAll(listaAtualizada);
        } else {
            Alert alert = new Alert(AlertType.ERROR, resultadoLista.getMsg());
            alert.showAndWait();
        }
    }

}
