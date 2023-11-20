package ifpr.pgua.eic.colecaomusicas.models;

public class Servico {
    private int codigoServico;
    private String descricao;
    private float valor;

    public Servico(float valor, String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico(int codigoServico, float valor, String descricao) {
        this.codigoServico = codigoServico;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getCodigo() {
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

    public float getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico: " + descricao + "   Valor: "+ valor + "" ;
    }
}
