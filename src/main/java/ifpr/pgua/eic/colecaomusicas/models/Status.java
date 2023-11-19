package ifpr.pgua.eic.colecaomusicas.models;

public class Status {
    private int codigo;
    private String descricao;

    public Status (String descricao){
        this.descricao = descricao;
    }

    public Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

     public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Status: " + descricao + "";
    }


}
