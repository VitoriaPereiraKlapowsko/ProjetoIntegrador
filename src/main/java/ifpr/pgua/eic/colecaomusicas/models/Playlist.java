package ifpr.pgua.eic.colecaomusicas.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private int id;
    private String nome;
    private List<Musica> musicas;

    public Playlist(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void adicionarMusicas(List<Musica> novasMusicas) {
        this.musicas.addAll(novasMusicas);
    }

    @Override
    public String toString() {
        return nome;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

}
