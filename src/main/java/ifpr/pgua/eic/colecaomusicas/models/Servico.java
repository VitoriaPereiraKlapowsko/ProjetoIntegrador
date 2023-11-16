package ifpr.pgua.eic.colecaomusicas.models;

public class Servico {
    private int codigoServico;
    private String descricao;
    private int valor;

    public Servico(String descricao, int valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico(int codigoServico, String descricao, int valor) {
        this.codigoServico = codigoServico;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(int codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico: " + descricao + " Valor: "+ valor + "";
    }
}
