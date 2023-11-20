package ifpr.pgua.eic.colecaomusicas.models;

public class Raca {
    private int codigo;
    private String nome;
    private String descricao;

    public Raca(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Raca(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Raça: " + nome + "    Descrição: " + descricao;
    }
}
