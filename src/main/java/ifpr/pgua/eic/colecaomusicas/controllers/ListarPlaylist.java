package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPlaylist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ListarPlaylist implements Initializable {

    @FXML
    private ListView<Playlist> lstListaPlaylists;

    @FXML
    private TextArea txMusicas;

    private RepositorioPlaylist repositorio;

    public ListarPlaylist(RepositorioPlaylist repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    void exibirDetalhes(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Playlist selectedPlaylist = lstListaPlaylists.getSelectionModel().getSelectedItem();

            if (selectedPlaylist != null){
                List<Musica> musicasDaPlaylist = selectedPlaylist.getMusicas();

                StringBuilder musicasInfo = new StringBuilder();
                for(Musica musica : musicasDaPlaylist){
                    musicasInfo.append("Nome: ").append(musica.getNome()).append("\n");
                    musicasInfo.append("Artista: ").append(musica.getArtista().getNome()).append("\n");
                    musicasInfo.append("Gênero: ").append(musica.getGenero().getNome()).append("\n\n");
                }
                txMusicas.setText(musicasInfo.toString());
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstListaPlaylists.getItems().clear();
        Resultado resultado = repositorio.listarPlaylists();

        if (resultado.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        } else {
            List<Playlist> lista = (List) resultado.comoSucesso().getObj();
            lstListaPlaylists.getItems().addAll(lista);
        }

        // Inicialize o TextArea com uma mensagem padrão
        txMusicas.setText("Selecione uma playlist para ver as músicas.");
    }
    
}
