package ifpr.pgua.eic.colecaomusicas.controllers;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Musica;
import ifpr.pgua.eic.colecaomusicas.models.Playlist;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioPlaylist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class CadastrarPlaylist {

    @FXML
    private ListView<Musica> lstMusicas;

    @FXML
    private TextField txNomeDaPlaylist;

    private RepositorioMusicas repositorioMusicas;
    private Playlist adicionandoMusicasNaPlaylist;
    private RepositorioPlaylist repositorio;

    public CadastrarPlaylist(RepositorioMusicas repositorioMusicas, RepositorioPlaylist repositorio) {
        this.repositorioMusicas = repositorioMusicas;
        this.repositorio = repositorio;
    }

    public void initialize() {
        lstMusicas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listarMusicas();
    }

    @FXML
    void cadastrarNomePlaylist(ActionEvent event) {
        String nomePlaylist = txNomeDaPlaylist.getText();

        if (nomePlaylist.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("O nome da playlist não pode estar vazio... Tente Novamente");
            alert.showAndWait();
            return;
        }

        adicionandoMusicasNaPlaylist = new Playlist(0, nomePlaylist);

        Resultado resultado = repositorio.getPlaylistDAO().criar(adicionandoMusicasNaPlaylist);

        if (resultado.foiSucesso()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Playlist cadastrada");
            alert.setContentText("A playlist '" + nomePlaylist + "' foi cadastrada com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Erro ao cadastrar a playlist: " + resultado.getMsg());
            alert.showAndWait();
        }
    }

    @FXML
    void adicionarMusica(ActionEvent event) {
        if (adicionandoMusicasNaPlaylist == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Primeiro cadastre o nome da playlist!");
            alert.showAndWait();
            return;
        }

        List<Musica> musicasSelecionadas = lstMusicas.getSelectionModel().getSelectedItems();

        if (musicasSelecionadas.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Selecione uma música para adicionar à playlist!");
            alert.showAndWait();
            return;
        }

        for (Musica musica : musicasSelecionadas) {
            adicionandoMusicasNaPlaylist.addMusica(musica);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Música adicionada");
        alert.setContentText("As músicas selecionadas foram adicionadas à playlist "
                + adicionandoMusicasNaPlaylist.getNome());
        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    private void listarMusicas() {
        Resultado resultado = repositorioMusicas.listar();

        if (resultado.foiSucesso()) {
            List<Musica> musicas = (List<Musica>) resultado.comoSucesso().getObj();
            lstMusicas.getItems().addAll(musicas);
        } else {

        }
    }

    
}
